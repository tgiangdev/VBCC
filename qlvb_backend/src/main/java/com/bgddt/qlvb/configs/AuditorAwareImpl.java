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

        if(authentication != null && authentication.getPrincipal() instanceof String) {
            return Optional.of((String) authentication.getPrincipal());
        }
        AccountDetail account = (AccountDetail) authentication.getPrincipal();
        return Optional.of(account.getUsername());
//        return Optional.of("System");
    }
}
