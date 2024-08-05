package com.smartera.customerservice.serviceview;

import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.dto.CustomerWriteDto;

import java.util.List;

public interface CustomerServiceView {
    String save(CustomerWriteDto customerWriteDto);
    CustomerReadDto findById(String customerId);
    List<CustomerReadDto> findAll();
    List<CustomerReadDto> findByKeyword(String keyword);
    void update(CustomerUpdateDto customerUpdateDto,String customerId);
    void deleteById(String customerId);
    void deleteAll();
    void authorize(String customerId);
}
