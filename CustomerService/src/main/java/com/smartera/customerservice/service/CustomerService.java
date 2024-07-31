package com.smartera.customerservice.service;

import com.smartera.customerservice.dto.order.OrderDto;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.Order;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

public interface CustomerService {
    void save(Customer customer);
    Customer findById(UUID customerId);
    List<Customer> findAll();
    List<Customer> findByKeyword(String keyword);
    void update(Customer customer);
    void deleteById(UUID customerId);
    void deleteAll();
    void authorize(UUID customerId);
    void checkAuthorization(UUID customerId);

    void saveOrder(UUID customerId, Order order);
    List<OrderDto> findAllOrders();
    List<OrderDto> findOrdersByCustomerId(UUID customerId);
    List<OrderDto> findOrdersByKeyword(String keyword);
    List<OrderDto> findOrdersByCustomerIdAndKeyword(UUID customerId, String keyword);
    OrderDto findOrderById(UUID orderId);
    void updateOrder(UUID orderId, Order updatedOrder);
    void deleteOrder(UUID orderId);
    void deleteOrderByCustomerId(UUID customerId);
    void deleteAllOrders();
}
