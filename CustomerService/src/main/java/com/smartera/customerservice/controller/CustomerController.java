package com.smartera.customerservice.controller;

import com.smartera.customerservice.dto.customer.CustomerCreateDto;
import com.smartera.customerservice.dto.customer.CustomerDto;
import com.smartera.customerservice.dto.customer.CustomerIdDto;
import com.smartera.customerservice.dto.order.OrderCreateDto;
import com.smartera.customerservice.dto.order.OrderDto;
import com.smartera.customerservice.dto.order.OrderIdDto;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.entity.Order;
import com.smartera.customerservice.mapper.CustomerMapper;
import com.smartera.customerservice.mapper.OrderMapper;
import com.smartera.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin()
@RequestMapping("customers")
public class
CustomerController{

    @Autowired
    CustomerService customerService;

    @PostMapping()
    public CustomerIdDto save(@RequestBody CustomerCreateDto customerCreateDto) {
        Customer customer = CustomerMapper.toCustomer(customerCreateDto);
        customerService.save(customer);
        return CustomerMapper.toCustomerIdDto(customerService.findById(customer.getCustomerId()));
    }

    @PostMapping("/{customerId}/orders")
    public OrderIdDto saveOrder(@RequestBody OrderCreateDto orderCreateDto, @PathVariable UUID customerId) {
        Order order = OrderMapper.toOrder(orderCreateDto);
        customerService.saveOrder(customerId,order);
        OrderDto orderDto = customerService.findOrdersByCustomerId(customerId)
                .stream().filter(o -> o.getOrderName().equals(order.getOrderName())).findFirst().get();
        return OrderMapper.toOrderIdDto(orderDto);
    }

    @GetMapping("/{customerId}")
    public CustomerDto findById(@PathVariable UUID customerId) {
        return CustomerMapper.toCustomerDto(customerService.findById(customerId));
    }

    @GetMapping()
    public List<CustomerDto> findAll() {
        return customerService.findAll()
                .stream().map(CustomerMapper::toCustomerDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<CustomerDto> findByKeyword(@PathVariable String keyword) {
        return customerService.findByKeyword(keyword)
                .stream().map(CustomerMapper::toCustomerDto).toList();
    }

    @GetMapping("/{customerId}/orders")
    public List<OrderDto> findOrdersByCustomerId(@PathVariable UUID customerId) {
        return customerService.findOrdersByCustomerId(customerId);
    }

    @GetMapping("/{customerId}/orders/{keyword}")
    public List<OrderDto> findOrdersByCustomerIdAndKeyword(@PathVariable UUID customerId, @PathVariable String keyword) {
        return customerService.findOrdersByCustomerIdAndKeyword(customerId, keyword);
    }

    @GetMapping("/orders")
    public List<OrderDto> findAllOrders() {
        return customerService.findAllOrders();
    }

    @GetMapping("/orders/{orderId}")
    public OrderDto findOrderById(@PathVariable UUID orderId) {
        return customerService.findOrderById(orderId);
    }

    @GetMapping("/orders/keyword/{keyword}")
    public List<OrderDto> findOrdersByKeyword(@PathVariable String keyword) {
        return customerService.findOrdersByKeyword(keyword);
    }

    @PutMapping("/{customerId}")
    public CustomerIdDto update(@RequestBody CustomerCreateDto customerCreateDto, @PathVariable UUID customerId) {
        Customer customer = CustomerMapper.toCustomer(customerCreateDto);
        customer.setCustomerId(customerId);
        customerService.update(customer);
        return CustomerMapper.toCustomerIdDto(customerService.findById(customerId));
    }


    @PutMapping("/{customerId}/authorize")
    public String authorize(@PathVariable UUID customerId) {
        customerService.authorize(customerId);
        return "Customer "+customerId+" has been authorized.";
    }

    @PutMapping("/orders/{orderId}")
    public OrderIdDto updateOrder(@RequestBody OrderCreateDto orderCreateDto, @PathVariable UUID orderId) {
        Order order = OrderMapper.toOrder(orderCreateDto);
        customerService.updateOrder(orderId, order);
        return OrderMapper.toOrderIdDto(customerService.findOrderById(orderId));
    }

    @DeleteMapping("/{customerId}")
    public String deleteById(@PathVariable UUID customerId) {
        customerService.deleteById(customerId);
        return "Customer with id " + customerId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        customerService.deleteAll();
        return "All customers have been deleted";
    }

    @DeleteMapping("/{customerId}/orders")
    public String deleteOrderCustomerId(@PathVariable UUID customerId) {
        customerService.deleteOrderByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping("/orders/{orderId}")
    public String deleteOrder(@PathVariable UUID orderId) {
        customerService.deleteOrder(orderId);
        return "Order with id " + orderId + " has been deleted";
    }

    @DeleteMapping("/orders")
    public String deleteAllOrders() {
        customerService.deleteAllOrders();
        return "All orders have been deleted";
    }
}
