package com.smartera.orderservice.serviceview.impl;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.mapper.OrderMapper;
import com.smartera.orderservice.service.OrderService;
import com.smartera.orderservice.serviceview.OrderServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceViewImpl implements OrderServiceView {

    @Autowired
    OrderService orderService;

    public String save(OrderWriteDto orderWriteDto, String customerId) {
        Order order = OrderMapper.toOrder(orderWriteDto);
        orderService.save(order,customerId);
        return order.getOrderId();
    }

    public OrderReadDto findById(String orderId) {
        return OrderMapper.toOrderReadDto(orderService.findById(orderId));
    }

    public List<OrderReadDto> findAll() {
        return orderService.findAll()
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    public List<OrderReadDto> findByKeyword(String keyword) {
        return orderService.findByKeyword(keyword)
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    public List<OrderReadDto> findByCustomerId(String customerId) {
        return orderService.findByCustomerId(customerId)
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    public List<OrderReadDto> findByCustomerIdKeyword(String customerId, String keyword) {
        return orderService.findByCustomerIdKeyword(customerId, keyword)
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    public void update(OrderWriteDto orderWriteDto, String orderId) {
        Order order = OrderMapper.toOrder(orderWriteDto);
        order.setOrderId(orderId);
        orderService.update(order,orderId);
    }

    public void deleteById(String orderId) {
        orderService.deleteById(orderId);
    }

    public void deleteAll() {
        orderService.deleteAll();
    }

    public void deleteByCustomerId(String customerId) {
        orderService.deleteByCustomerId(customerId);
    }
}
