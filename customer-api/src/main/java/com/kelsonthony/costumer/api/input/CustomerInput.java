package com.kelsonthony.costumer.api.input;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
public class CustomerInput {

    @NotBlank
    private String name;

    @NotBlank
    private BigDecimal payment;
}
