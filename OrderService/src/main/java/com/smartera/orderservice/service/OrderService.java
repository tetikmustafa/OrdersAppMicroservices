package com.smartera.orderservice.service;

import com.smartera.orderservice.entity.Order;

import java.util.List;


public interface OrderService {
    void save(Order order,String customerId);
    Order findById(String orderId);
    List<Order> findAll();
    List<Order> findByKeyword(String keyword);
    void update(Order order, String orderId);
    void deleteById(String orderId);
    void deleteAll();
    List<Order> findByCustomerId(String customerId);
    List<Order> findByCustomerIdKeyword(String customerId, String keyword);
    void deleteByCustomerId(String customerId);
}
