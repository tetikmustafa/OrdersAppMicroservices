package com.smartera.orderservice.controller;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.mapper.OrderMapper;
import com.smartera.orderservice.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("orders")
public class OrderController{

    @Autowired
    OrderService orderService;

    @PostMapping("/{customerId}")
    public OrderReadDto save(@RequestBody OrderWriteDto orderDto, @PathVariable String customerId) {
        Order order = OrderMapper.toOrder(orderDto);
        orderService.save(order,customerId);
        return OrderMapper.toOrderReadDto(orderService.findById(order.getOrderId()));
    }

    @GetMapping("/{orderId}")
    public OrderReadDto findById(@PathVariable String orderId) {
        return OrderMapper.toOrderReadDto(orderService.findById(orderId));
    }

    @GetMapping()
    public List<OrderReadDto> findAll() {
        return orderService.findAll()
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<OrderReadDto> findByKeyword(@PathVariable String keyword) {
        return orderService.findByKeyword(keyword)
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    @GetMapping("/byCustomerId/{customerId}")
    public List<OrderReadDto> findByCustomerId(@PathVariable String customerId) {
        return orderService.findByCustomerId(customerId)
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    @GetMapping("/byCustomerId/{customerId}/{keyword}")
    public List<OrderReadDto> findByKeyword(@PathVariable String customerId, @PathVariable String keyword) {
        return orderService.findByCustomerIdKeyword(customerId, keyword)
                .stream().map(OrderMapper::toOrderReadDto).toList();
    }

    @PutMapping("/{orderId}")
    public OrderReadDto update(@RequestBody OrderWriteDto orderDto, @PathVariable String orderId) {
        Order order = OrderMapper.toOrder(orderDto);
        orderService.update(order,orderId);
        return OrderMapper.toOrderReadDto(orderService.findById(order.getOrderId()));
    }


    @DeleteMapping("/{orderId}")
    public String deleteById(@PathVariable String orderId) {
        orderService.deleteById(orderId);
        return "Order with id " + orderId + " has been deleted";
    }


    @DeleteMapping("/byCustomerId/{customerId}")
    public String deleteByCustomerId(@PathVariable String customerId) {
        orderService.deleteByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        orderService.deleteAll();
        return "All orders have been deleted";
    }
}
