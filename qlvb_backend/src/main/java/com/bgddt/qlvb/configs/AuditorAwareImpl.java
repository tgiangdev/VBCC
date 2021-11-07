package com.bgddt.qlvb.configs;

import com.bgddt.qlvb.models.AccountDetail;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

public class AuditorAwareImpl implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        AccountDetail account = (AccountDetail) authentication.getPrincipal();
        return Optional.of(account.getUsername());
    }
}