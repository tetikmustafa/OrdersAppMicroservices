package com.smartera.orderservice.service.impl;

import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.exception.OrderNotFoundException;
import com.smartera.orderservice.repository.OrderRepository;
import com.smartera.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    public Order findById(@PathVariable UUID orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByKeyword(String keyword) {
        return orderRepository.findByOrderNameContainingOrOrderDescriptionContaining(keyword,keyword);
    }

    public void update(Order order) {
        orderRepository.save(order);
    }

    public void deleteById(@PathVariable UUID orderId) {
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        orderRepository.deleteById(orderId);
    }

    public void deleteAll() {
        orderRepository.deleteAll();
    }

    public void save(Order order) {
        orderRepository.save(order);
    }

    public List<Order> findByCustomerId(UUID customerId) {
        return orderRepository.findByOrderCustomerId(customerId);
    }


    public List<Order> findByCustomerIdKeyword(UUID customerId, String keyword) {
        return orderRepository.findByOrderCustomerIdAndOrderDescriptionContaining(customerId, keyword);
    }

    public void deleteByCustomerId(UUID customerId) {
        List<Order> orders = orderRepository.findByOrderCustomerId(customerId);
        for (Order order : orders) {
            orderRepository.deleteById(order.getOrderId());
        }
    }
}
