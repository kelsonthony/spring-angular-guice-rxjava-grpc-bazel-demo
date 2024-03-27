package com.kelsonthony.costumer.core.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.stereotype.Component;

@Component
public class CustomerSecurity {

    public Authentication getAuthentication() {
        return SecurityContextHolder.getContext().getAuthentication();
    }

    public boolean isAuthenticated() {
        return getAuthentication().isAuthenticated();
    }

    public Long gerCustomerId() {
        Jwt jwt = (Jwt) getAuthentication().getPrincipal();

        Object customerId = jwt.getClaim("customer_id");

        if (customerId == null) {
            return null;
        }

        return Long.valueOf(customerId.toString());
    }

    public boolean hasAuthority(String authorityName) {
        return getAuthentication().getAuthorities().stream()
                .anyMatch(authority -> authority.getAuthority().equals(authorityName));
    }

    public boolean hasScopeRead() {
        return hasAuthority("SCOPE_READ");
    }

    public boolean hasScopeWrite() {
        return hasAuthority("SCOPE_WRITE");
    }
    public boolean canEditCustomersGroupsPermissions() {
        return hasScopeWrite() && hasAuthority("EDITAR_USUARIOS_GRUPOS_PERMISSOES");
    }

    public boolean canReadCustomers() {
        return isAuthenticated() && hasScopeRead();
    }

}
