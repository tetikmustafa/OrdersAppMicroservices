package com.smartera.customerservice.service.impl;

import com.smartera.customerservice.dto.order.OrderDto;
import com.smartera.customerservice.dto.order.OrderIdDto;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.Order;
import com.smartera.customerservice.exception.CustomerNotAuthorizedException;
import com.smartera.customerservice.exception.CustomerNotFoundException;
import com.smartera.customerservice.feign.CustomerInterface;
import com.smartera.customerservice.mapper.OrderMapper;
import com.smartera.customerservice.repository.CustomerRepository;
import com.smartera.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CustomerInterface customerInterface;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findById(@PathVariable UUID customerId) {
        return  customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findByKeyword(String keyword) {
        return customerRepository.findByCustomerNameContainingOrCustomerDescriptionContaining(keyword, keyword);
    }

    public void update(Customer customer) {
        Optional<Customer> o = customerRepository.findById(customer.getCustomerId());
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customer.getCustomerId());
        }
        customerRepository.save(customer);
    }

    public void deleteById(@PathVariable UUID customerId) {
        Optional<Customer> o = customerRepository.findById(customerId);
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        customerRepository.deleteById(customerId);
    }

    public void deleteAll() {
        customerRepository.deleteAll();
    }

    public void authorize(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.setCustomerAuthorization(true);
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void checkAuthorization(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            if(!c.isCustomerAuthorization()){
                throw new CustomerNotAuthorizedException(customerId);
            }
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void saveOrder(UUID customerId, Order order) {
        checkAuthorization(customerId);
        order.setOrderCustomerId(customerId);
        OrderIdDto orderIdDto = customerInterface.save(OrderMapper.toOrderDto(order));

        UUID orderId = orderIdDto.getOrderId();
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().add(orderId);
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public List<OrderDto> findOrdersByCustomerId(UUID customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            return customerInterface.findByCustomerId(customerId);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public List<OrderDto> findAllOrders() {
        return customerInterface.findAll();
    }

    public List<OrderDto> findOrdersByKeyword(String keyword) {
        return customerInterface.findByKeyword(keyword);
    }

    public List<OrderDto> findOrdersByCustomerIdAndKeyword(UUID customerId, String keyword) {
        return customerInterface.findByKeyword(customerId, keyword);
    }

    public OrderDto findOrderById(UUID orderId) {
        return customerInterface.findById(orderId);
    }

    public void updateOrder(UUID orderId, Order updatedOrder) {
        OrderDto order = customerInterface.findById(orderId);
        UUID customerId = order.getOrderCustomerId();
        updatedOrder.setOrderId(orderId);
        updatedOrder.setOrderCustomerId(customerId);
        if(updatedOrder.getOrderName()==null){
            updatedOrder.setOrderName(order.getOrderName());
        } else if(updatedOrder.getOrderDescription()==null){
            updatedOrder.setOrderDescription(order.getOrderDescription());
        }else if(updatedOrder.getOrderProductsIds()==null){
            updatedOrder.setOrderProductsIds(order.getOrderProductsIds());
        }
        customerInterface.update(OrderMapper.toOrderDto(updatedOrder));
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().remove((Object) order.getOrderId());
            c.getCustomerOrdersIds().add(order.getOrderId());
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void deleteOrderByCustomerId(UUID customerId) {
        customerInterface.deleteByCustomerId(customerId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().clear();
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }


    }

    public void deleteOrder(UUID orderId) {
        OrderDto order = customerInterface.findById(orderId);
        UUID customerId = order.getOrderCustomerId();
        customerInterface.deleteById(orderId);
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.getCustomerOrdersIds().remove((Object) orderId);
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }

    public void deleteAllOrders() {
        customerInterface.deleteAll();
        List<Customer> customers = customerRepository.findAll();
        for(Customer c: customers){
            c.getCustomerOrdersIds().clear();
            customerRepository.save(c);
        }
    }
}
