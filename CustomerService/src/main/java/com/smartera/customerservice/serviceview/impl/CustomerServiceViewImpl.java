package com.smartera.customerservice.serviceview.impl;

import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.mapper.CustomerMapper;
import com.smartera.customerservice.service.CustomerService;
import com.smartera.customerservice.serviceview.CustomerServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceViewImpl implements CustomerServiceView {

    @Autowired
    CustomerService customerService;

    public String save(CustomerWriteDto customerWriteDto) {
        Customer customer = CustomerMapper.toCustomer(customerWriteDto);
        customerService.save(customer);
        return customer.getCustomerId();
    }

    public CustomerReadDto findById(String customerId) {
        return CustomerMapper.toCustomerReadDto(customerService.findById(customerId));
    }

    public List<CustomerReadDto> findAll() {
        return customerService.findAll()
                .stream().map(CustomerMapper::toCustomerReadDto).toList();
    }

    public List<CustomerReadDto> findByKeyword(String keyword) {
        return customerService.findByKeyword(keyword)
                .stream().map(CustomerMapper::toCustomerReadDto).toList();
    }

    public void update(CustomerUpdateDto customerUpdateDto, String customerId) {
        Customer customer = CustomerMapper.toCustomer(customerUpdateDto);
        customer.setCustomerId(customerId);
        customerService.update(customer);
    }

    public void deleteById(String customerId) {
        customerService.deleteById(customerId);
    }

    public void deleteAll() {
        customerService.deleteAll();
    }

    public void authorize(String customerId) {
        customerService.authorize(customerId);
    }
}
