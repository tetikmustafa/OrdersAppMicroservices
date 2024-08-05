package com.smartera.orderservice.exception;



public class CustomerNotAuthorizedException extends RuntimeException{
    public CustomerNotAuthorizedException(String customerId) {
        super("Customer not authorized : "+customerId);
    }
}
