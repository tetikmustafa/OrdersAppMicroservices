package com.smartera.orderservice.service.impl;

import com.smartera.orderservice.client.CustomerControllerClient;
import com.smartera.orderservice.dto.CustomerReadDto;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.exception.CustomerNotAuthorizedException;
import com.smartera.orderservice.exception.OrderNotFoundException;
import com.smartera.orderservice.mapper.CustomerMapper;
import com.smartera.orderservice.repository.OrderRepository;
import com.smartera.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;


@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    CustomerControllerClient customerControllerClient;

    public void save(Order order, String customerId) {
        CustomerReadDto customerDto = customerControllerClient.findById(customerId);
        if(!customerDto.isCustomerAuthorization()){
            throw new CustomerNotAuthorizedException(customerId);
        }
        order.setOrderCustomerId(customerId);
        orderRepository.save(order);
        customerDto.getCustomerOrdersIds().add(order.getOrderId());
        customerControllerClient.update(CustomerMapper.toCustomerUpdateDto(customerDto),customerId);
    }

    public Order findById(@PathVariable String orderId) {
        return orderRepository.findById(orderId).orElseThrow(() -> new OrderNotFoundException(orderId));
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public List<Order> findByKeyword(String keyword) {
        return orderRepository.findByOrderNameContainingOrOrderDescriptionContaining(keyword,keyword);
    }

    public List<Order> findByCustomerId(String customerId) {
        return orderRepository.findByOrderCustomerId(customerId);
    }


    public List<Order> findByCustomerIdKeyword(String customerId, String keyword) {
        return orderRepository.findByOrderCustomerIdAndOrderDescriptionContaining(customerId, keyword);
    }

    public void update(Order order,String orderId) {
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        order.setOrderId(orderId);
        order.setOrderCustomerId(o.get().getOrderCustomerId());
        orderRepository.save(order);
    }

    public void deleteById(@PathVariable String orderId) {
        Optional<Order> o = orderRepository.findById(orderId);
        if (o.isEmpty()) {
            throw new OrderNotFoundException(orderId);
        }
        CustomerReadDto customerDto = customerControllerClient.findById(o.get().getOrderCustomerId());
        customerDto.getCustomerOrdersIds().remove(orderId);
        customerControllerClient.update(CustomerMapper.toCustomerUpdateDto(customerDto),o.get().getOrderCustomerId());
        orderRepository.deleteById(orderId);
    }

    public void deleteByCustomerId(String customerId) {
        List<Order> orders = orderRepository.findByOrderCustomerId(customerId);
        CustomerReadDto customerDto = customerControllerClient.findById(customerId);
        for (Order order : orders) {
            customerDto.getCustomerOrdersIds().remove(order.getOrderId());
            orderRepository.deleteById(order.getOrderId());
        }
    }

    public void deleteAll() {
        List<CustomerReadDto> customers = customerControllerClient.findAll();
        for (CustomerReadDto customer : customers) {
            customer.setCustomerOrdersIds(null);
            customerControllerClient.update(CustomerMapper.toCustomerUpdateDto(customer),customer.getCustomerId());
        }
        orderRepository.deleteAll();
    }
}
