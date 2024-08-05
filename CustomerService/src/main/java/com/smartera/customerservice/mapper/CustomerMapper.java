package com.smartera.customerservice.mapper;

import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.entity.Customer;

public class CustomerMapper {
    public static CustomerReadDto toCustomerReadDto(Customer customer) {
        CustomerReadDto customerReadDto = new CustomerReadDto();
        customerReadDto.setCustomerId(customer.getCustomerId());
        customerReadDto.setCustomerName(customer.getCustomerName());
        customerReadDto.setCustomerDescription(customer.getCustomerDescription());
        customerReadDto.setCustomerAuthorization(customer.isCustomerAuthorization());
        customerReadDto.setCustomerOrdersIds(customer.getCustomerOrdersIds());
        return customerReadDto;
    }

    public static Customer toCustomer(CustomerWriteDto customerWriteDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerWriteDto.getCustomerName());
        customer.setCustomerDescription(customerWriteDto.getCustomerDescription());
        customer.setCustomerAuthorization(false);
        return customer;
    }

    public static Customer toCustomer(CustomerUpdateDto customerUpdateDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerUpdateDto.getCustomerName());
        customer.setCustomerDescription(customerUpdateDto.getCustomerDescription());
        customer.setCustomerOrdersIds(customerUpdateDto.getCustomerOrdersIds());
        customer.setCustomerAuthorization(customerUpdateDto.isCustomerAuthorization());
        return customer;
    }
}
