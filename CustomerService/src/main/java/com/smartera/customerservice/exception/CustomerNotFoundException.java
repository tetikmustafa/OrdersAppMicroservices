package com.smartera.customerservice.exception;

import java.util.UUID;

public class CustomerNotFoundException extends RuntimeException{
    public CustomerNotFoundException(UUID customerId) {
        super("Customer not found : "+customerId);
    }
}
