package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.configs.JwtTokenProvider;
import com.bgddt.qlvb.dtos.AccountDTO;
import com.bgddt.qlvb.models.AccountDetail;
import com.bgddt.qlvb.models.JwtAuthenticationResponse;
import com.bgddt.qlvb.services.AccountService;
import com.bgddt.qlvb.common.exceptions.BusinessException;
import com.bgddt.qlvb.entities.Account;
import com.bgddt.qlvb.repositories.AccountRepository;
import com.bgddt.qlvb.models.LoginPayload;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl extends BaseServiceImpl<AccountDTO, Account> implements UserDetailsService, AccountService<AccountDTO, Account> {
    private AccountRepository accountRepository;
    private AuthenticationManager authenticationManager;
    private JwtTokenProvider tokenProvider;
    private PasswordEncoder passwordEncoder;

    @Autowired
    protected AccountServiceImpl(
            AccountRepository accountRepository,
            AuthenticationManager authenticationManager,
            JwtTokenProvider tokenProvider,
            PasswordEncoder passwordEncoder
    ) {
        super(accountRepository, AccountDTO.class, Account.class);
        this.accountRepository = accountRepository;
        this.authenticationManager = authenticationManager;
        this.tokenProvider = tokenProvider;
        this.passwordEncoder = passwordEncoder;
    }

    // LOGIN
    @Override
    public AccountDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AccountDetail(account);
    }

    public UserDetails loadUserById(Long userId) throws BusinessException {
        return new AccountDetail(accountRepository.findById(userId).orElseThrow(() -> new BusinessException("User không tồn tại")));
    }

    @Override
    public JwtAuthenticationResponse login(LoginPayload payload) throws BusinessException {
        // Xác thực từ username và password.
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            payload.getUsername(),
                            payload.getPassword()
                    )
            );
        }catch (AuthenticationException e) {
            throw new BusinessException("Tài khoản hoặc mật khẩu không đúng");
        }

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Trả về jwt cho người dùng.
        AccountDetail account = (AccountDetail) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(account);
        return new JwtAuthenticationResponse(jwt, account.getAccount());
    }

    // END LOGIN

    // validate create


    @Override
    protected AccountDTO validateCreate(AccountDTO dto) {
        String defaultPassword = passwordEncoder.encode("123456");
        dto.setPassword(defaultPassword);
        return dto;
    }

    // validate update
    @Override
    protected Object validateUpdate(Long id, Object entity) throws BusinessException {
        AccountDTO account = (AccountDTO) entity;
        Account oldAccount = accountRepository.findById(id).orElseThrow(() -> new BusinessException("Tài khoản không tồn tại"));
        account.setPassword(oldAccount.getPassword());
        return account;
    }
}
