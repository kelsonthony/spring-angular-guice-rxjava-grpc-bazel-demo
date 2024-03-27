package com.kelsonthony.costumer.core.security;

import org.springframework.security.access.prepost.PreAuthorize;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

public @interface CheckSecurity {

    public @interface Customers {

        @PreAuthorize("hasAuthority('SCOPE_WRITE') and hasAuthority('EDIT_CUSTOMERS')")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canEdit { }

        @PreAuthorize("@customerSecurity.canReadCustomers")
        @Retention(RUNTIME)
        @Target(METHOD)
        public @interface canRead { }

    }
}
