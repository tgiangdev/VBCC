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
        return new AccountDetail(entityToDto(account));
    }

    public UserDetails loadUserById(Long userId) throws BusinessException {
        Account account = accountRepository.findById(userId).orElseThrow(() -> new BusinessException("User kh??ng t???n t???i"));
        return new AccountDetail(entityToDto(account));
    }

    @Override
    public JwtAuthenticationResponse login(LoginPayload payload) throws BusinessException {
        // X??c th???c t??? username v?? password.
        Authentication authentication = null;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            payload.getUsername(),
                            payload.getPassword()
                    )
            );
        }catch (AuthenticationException e) {
            throw new BusinessException("T??i kho???n ho???c m???t kh???u kh??ng ????ng");
        }

        // N???u kh??ng x???y ra exception t???c l?? th??ng tin h???p l???
        // Set th??ng tin authentication v??o Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Tr??? v??? jwt cho ng?????i d??ng.
        AccountDetail account = (AccountDetail) authentication.getPrincipal();
        String jwt = tokenProvider.generateToken(account);
        return new JwtAuthenticationResponse(jwt, account.getAccount());
    }

    @Override
    public AccountDTO getCurrentAccount() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetail account = (AccountDetail) authentication.getPrincipal();
        return account.getAccount();
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
        Account oldAccount = accountRepository.findById(id).orElseThrow(() -> new BusinessException("T??i kho???n kh??ng t???n t???i"));
        account.setPassword(oldAccount.getPassword());
        return account;
    }
}
