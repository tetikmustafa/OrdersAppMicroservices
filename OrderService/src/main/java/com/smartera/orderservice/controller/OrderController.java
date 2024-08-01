package com.smartera.orderservice.controller;

import com.smartera.orderservice.dto.OrderDto;
import com.smartera.orderservice.dto.OrderIdDto;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.mapper.OrderMapper;
import com.smartera.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@CrossOrigin()
@RequestMapping("orders")
public class OrderController{

    @Autowired
    OrderService orderService;

    @PostMapping()
    public OrderIdDto save(@RequestBody OrderDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        orderService.save(order);
        return OrderMapper.toOrderIdDto(orderService.findById(order.getOrderId()));
    }

    @GetMapping("/{orderId}")
    public OrderDto findById(@PathVariable UUID orderId) {
        return OrderMapper.toOrderDto(orderService.findById(orderId));
    }

    @GetMapping()
    public List<OrderDto> findAll() {
        return orderService.findAll()
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<OrderDto> findByKeyword(@PathVariable String keyword) {
        return orderService.findByKeyword(keyword)
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @GetMapping("/byCustomerId/{customerId}")
    public List<OrderDto> findByCustomerId(@PathVariable UUID customerId) {
        return orderService.findByCustomerId(customerId)
                .stream().map(OrderMapper::toOrderDto).toList();
    }

    @GetMapping("/byCustomerId/{customerId}/{keyword}")
    public List<OrderDto> findByKeyword(@PathVariable UUID customerId, @PathVariable String keyword) {
        return orderService.findByCustomerIdKeyword(customerId, keyword)
                .stream().map(OrderMapper::toOrderDto).toList();
    }

//    deişik
    @PutMapping()
    public OrderIdDto update(@RequestBody OrderDto orderDto) {
        Order order = OrderMapper.toOrder(orderDto);
        orderService.update(order);
        return OrderMapper.toOrderIdDto(orderService.findById(order.getOrderId()));
    }


    @DeleteMapping("/{orderId}")
    public String deleteById(@PathVariable UUID orderId) {
        orderService.deleteById(orderId);
        return "Order with id " + orderId + " has been deleted";
    }


    @DeleteMapping("/byCustomerId/{customerId}")
    public String deleteByCustomerId(@PathVariable UUID customerId) {
        orderService.deleteByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        orderService.deleteAll();
        return "All orders have been deleted";
    }
}
