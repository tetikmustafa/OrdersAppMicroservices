package com.smartera.orderservice.exception;



public class OrderNotFoundException extends RuntimeException{
    public OrderNotFoundException(String orderId) {
        super("Order not found : "+orderId);
    }
}
