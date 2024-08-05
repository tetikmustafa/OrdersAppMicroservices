package com.smartera.customerservice.mapper;

import com.smartera.customerservice.dto.CustomerCreateDto;
import com.smartera.customerservice.dto.CustomerDto;
import com.smartera.customerservice.dto.CustomerIdDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.entity.Customer;

public class CustomerMapper {
    public static CustomerDto toCustomerDto(Customer customer) {
        CustomerDto customerDto = new CustomerDto();
        customerDto.setCustomerId(customer.getCustomerId());
        customerDto.setCustomerName(customer.getCustomerName());
        customerDto.setCustomerDescription(customer.getCustomerDescription());
        customerDto.setCustomerAuthorization(customer.isCustomerAuthorization());
        customerDto.setCustomerOrdersIds(customer.getCustomerOrdersIds());
        return customerDto;
    }

    public static Customer toCustomer(CustomerCreateDto customerCreateDto) {
        Customer customer = new Customer();
        customer.setCustomerName(customerCreateDto.getCustomerName());
        customer.setCustomerDescription(customerCreateDto.getCustomerDescription());
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

    public static CustomerIdDto toCustomerIdDto(Customer customer) {
        CustomerIdDto customerIdDto = new CustomerIdDto();
        customerIdDto.setCustomerId(customer.getCustomerId());
        return customerIdDto;
    }
}
