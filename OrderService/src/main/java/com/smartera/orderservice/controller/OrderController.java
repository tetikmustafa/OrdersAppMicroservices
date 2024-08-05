package com.smartera.orderservice.controller;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.serviceview.OrderServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("orders")
public class OrderController{

    @Autowired
    OrderServiceView orderServiceView;

    @PostMapping("/{customerId}")
    public OrderReadDto save(@RequestBody OrderWriteDto orderWriteDto, @PathVariable String customerId) {
        String orderId = orderServiceView.save(orderWriteDto, customerId);
        return orderServiceView.findById(orderId);
    }

    @GetMapping("/{orderId}")
    public OrderReadDto findById(@PathVariable String orderId) {
        return orderServiceView.findById(orderId);
    }

    @GetMapping()
    public List<OrderReadDto> findAll() {
        return orderServiceView.findAll();
    }

    @GetMapping("/keyword/{keyword}")
    public List<OrderReadDto> findByKeyword(@PathVariable String keyword) {
        return orderServiceView.findByKeyword(keyword);
    }

    @GetMapping("/byCustomerId/{customerId}")
    public List<OrderReadDto> findByCustomerId(@PathVariable String customerId) {
        return orderServiceView.findByCustomerId(customerId);
    }

    @GetMapping("/byCustomerId/{customerId}/{keyword}")
    public List<OrderReadDto> findByKeyword(@PathVariable String customerId, @PathVariable String keyword) {
        return orderServiceView.findByCustomerIdKeyword(customerId, keyword);
    }

    @PutMapping("/{orderId}")
    public OrderReadDto update(@RequestBody OrderWriteDto orderWriteDto, @PathVariable String orderId) {
        orderServiceView.update(orderWriteDto, orderId);
        return orderServiceView.findById(orderId);
    }


    @DeleteMapping("/{orderId}")
    public String deleteById(@PathVariable String orderId) {
        orderServiceView.deleteById(orderId);
        return "Order with id " + orderId + " has been deleted";
    }


    @DeleteMapping("/byCustomerId/{customerId}")
    public String deleteByCustomerId(@PathVariable String customerId) {
        orderServiceView.deleteByCustomerId(customerId);
        return "Orders of customer with id " + customerId + " have been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        orderServiceView.deleteAll();
        return "All orders have been deleted";
    }
}
