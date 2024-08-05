package com.smartera.orderservice.mapper;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.entity.Order;

public class OrderMapper {
    public static OrderReadDto toOrderReadDto(Order order) {
        OrderReadDto orderReadDto = new OrderReadDto();
        orderReadDto.setOrderId(order.getOrderId());
        orderReadDto.setOrderCustomerId(order.getOrderCustomerId());
        orderReadDto.setOrderName(order.getOrderName());
        orderReadDto.setOrderDescription(order.getOrderDescription());
        orderReadDto.setOrderProductsIds(order.getOrderProductsIds());
        return orderReadDto;
    }

    public static Order toOrder(OrderWriteDto orderWriteDto) {
        Order order = new Order();
        order.setOrderName(orderWriteDto.getOrderName());
        order.setOrderDescription(orderWriteDto.getOrderDescription());
        order.setOrderProductsIds(orderWriteDto.getOrderProductsIds());
        return order;
    }
}
