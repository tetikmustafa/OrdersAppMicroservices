package com.smartera.customerservice.service;

import com.smartera.customerservice.entity.Customer;

import java.util.List;


public interface CustomerService {
    void save(Customer customer);
    Customer findById(String customerId);
    List<Customer> findAll();
    List<Customer> findByKeyword(String keyword);
    void update(Customer customer);
    void deleteById(String customerId);
    void deleteAll();
    void authorize(String customerId);
}
