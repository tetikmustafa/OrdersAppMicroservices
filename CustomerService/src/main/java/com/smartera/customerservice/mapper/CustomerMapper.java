package com.smartera.customerservice.mapper;

import com.smartera.customerservice.dto.customer.CustomerCreateDto;
import com.smartera.customerservice.dto.customer.CustomerDto;
import com.smartera.customerservice.dto.customer.CustomerIdDto;
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

    public static CustomerIdDto toCustomerIdDto(Customer customer) {
        CustomerIdDto customerIdDto = new CustomerIdDto();
        customerIdDto.setCustomerId(customer.getCustomerId());
        return customerIdDto;
    }
}
