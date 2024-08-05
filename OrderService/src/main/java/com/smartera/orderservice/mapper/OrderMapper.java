package com.smartera.orderservice.mapper;

import com.smartera.orderservice.dto.OrderDto;
import com.smartera.orderservice.dto.OrderIdDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.entity.Order;

public class OrderMapper {
    public static OrderDto toOrderDto(Order order) {
        OrderDto orderDto = new OrderDto();
        orderDto.setOrderId(order.getOrderId());
        orderDto.setOrderCustomerId(order.getOrderCustomerId());
        orderDto.setOrderName(order.getOrderName());
        orderDto.setOrderDescription(order.getOrderDescription());
        orderDto.setOrderProductsIds(order.getOrderProductsIds());
        return orderDto;
    }

    public static Order toOrder(OrderWriteDto orderWriteDto) {
        Order order = new Order();
        order.setOrderName(orderWriteDto.getOrderName());
        order.setOrderDescription(orderWriteDto.getOrderDescription());
        order.setOrderProductsIds(orderWriteDto.getOrderProductsIds());
        return order;
    }

    public static OrderIdDto toOrderIdDto(Order order) {
        OrderIdDto orderIdDto = new OrderIdDto();
        orderIdDto.setOrderId(order.getOrderId());
        orderIdDto.setOrderCustomerId(order.getOrderCustomerId());
        return orderIdDto;
    }
}
