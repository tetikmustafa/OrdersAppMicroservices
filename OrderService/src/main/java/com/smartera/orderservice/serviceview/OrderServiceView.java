package com.smartera.orderservice.serviceview;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;

import java.util.List;

public interface OrderServiceView {
    String save(OrderWriteDto orderWriteDto, String customerId);
    OrderReadDto findById(String orderId);
    List<OrderReadDto> findAll();
    List<OrderReadDto> findByKeyword(String keyword);
    List<OrderReadDto> findByCustomerId(String customerId);
    List<OrderReadDto> findByCustomerIdKeyword(String customerId, String keyword);
    void update(OrderWriteDto order, String orderId);
    void deleteById(String orderId);
    void deleteAll();
    void deleteByCustomerId(String customerId);
}
