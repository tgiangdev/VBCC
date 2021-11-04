package com.bgddt.qlvb.services.impl;

import com.bgddt.qlvb.configs.JwtTokenProvider;
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
import org.springframework.stereotype.Service;


@Service
public class AccountServiceImpl implements UserDetailsService, AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Override
    public AccountDetail loadUserByUsername(String username) throws UsernameNotFoundException {
        Account account = accountRepository.findByUsername(username);
        if (account == null) {
            throw new UsernameNotFoundException(username);
        }
        return new AccountDetail(account);
    }

    public UserDetails loadUserById(Long userId) {
        return new AccountDetail(accountRepository.findById(userId).get());
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
}
