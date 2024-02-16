package com.kelsonthony.costumer.domain.exception;

public class CustomerNotFoundException extends EntityNotFoundException {

    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(Long clientId) {
        this(String.format("There is no client record with the code %d", clientId));
    }
}
