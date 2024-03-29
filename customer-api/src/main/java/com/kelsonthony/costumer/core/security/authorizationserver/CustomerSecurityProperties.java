package com.kelsonthony.costumer.core.security.authorizationserver;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

@Component
@Getter
@Setter
@Validated
@ConfigurationProperties("customer.auth")
public class CustomerSecurityProperties {

    @NotBlank
    private String providerUrl;

}
