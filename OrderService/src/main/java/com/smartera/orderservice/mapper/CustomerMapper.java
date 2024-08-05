package com.smartera.orderservice.mapper;

import com.smartera.orderservice.dto.CustomerReadDto;
import com.smartera.orderservice.dto.CustomerUpdateDto;

public class CustomerMapper {
    public static CustomerUpdateDto toCustomerUpdateDto(CustomerReadDto customerReadDto) {
        CustomerUpdateDto customerUpdateDto = new CustomerUpdateDto();
        customerUpdateDto.setCustomerName(customerReadDto.getCustomerName());
        customerUpdateDto.setCustomerDescription(customerReadDto.getCustomerDescription());
        customerUpdateDto.setCustomerOrdersIds(customerReadDto.getCustomerOrdersIds());
        customerUpdateDto.setCustomerAuthorization(customerReadDto.isCustomerAuthorization());
        return customerUpdateDto;
    }
}
