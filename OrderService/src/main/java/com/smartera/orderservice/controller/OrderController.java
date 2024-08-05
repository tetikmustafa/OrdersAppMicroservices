package com.smartera.orderservice.controller;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.serviceview.OrderServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("orders")
public class OrderController{

    @Autowired
    OrderServiceView orderServiceView;

    @PostMapping("/{customerId}")
    public ResponseEntity<OrderReadDto> save(@RequestBody OrderWriteDto orderWriteDto, @PathVariable String customerId) {
        String orderId = orderServiceView.save(orderWriteDto, customerId);
        return new ResponseEntity<>(orderServiceView.findById(orderId), HttpStatus.CREATED) ;
    }

    @GetMapping("/{orderId}")
    public ResponseEntity<OrderReadDto> findById(@PathVariable String orderId) {
        return new ResponseEntity<>(orderServiceView.findById(orderId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<OrderReadDto>> findAll() {
        return new ResponseEntity<>(orderServiceView.findAll(), HttpStatus.OK);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<OrderReadDto>> findByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<>(orderServiceView.findByKeyword(keyword), HttpStatus.OK);
    }

    @GetMapping("/byCustomerId/{customerId}")
    public ResponseEntity<List<OrderReadDto>> findByCustomerId(@PathVariable String customerId) {
        return new ResponseEntity<>(orderServiceView.findByCustomerId(customerId), HttpStatus.OK);
    }

    @GetMapping("/byCustomerId/{customerId}/{keyword}")
    public ResponseEntity<List<OrderReadDto>> findByKeyword(@PathVariable String customerId, @PathVariable String keyword) {
        return new ResponseEntity<>(orderServiceView.findByCustomerIdKeyword(customerId, keyword), HttpStatus.OK);
    }

    @PutMapping("/{orderId}")
    public ResponseEntity<OrderReadDto> update(@RequestBody OrderWriteDto orderWriteDto, @PathVariable String orderId) {
        orderServiceView.update(orderWriteDto, orderId);
        return new ResponseEntity<>(orderServiceView.findById(orderId), HttpStatus.OK);
    }


    @DeleteMapping("/{orderId}")
    public ResponseEntity<String> deleteById(@PathVariable String orderId) {
        orderServiceView.deleteById(orderId);
        return new ResponseEntity<>("Order with id " + orderId + " has been deleted", HttpStatus.OK);
    }


    @DeleteMapping("/byCustomerId/{customerId}")
    public ResponseEntity<String> deleteByCustomerId(@PathVariable String customerId) {
        orderServiceView.deleteByCustomerId(customerId);
        return new ResponseEntity<>("Orders of customer with id " + customerId + " have been deleted", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAll() {
        orderServiceView.deleteAll();
        return new ResponseEntity<>("All orders have been deleted", HttpStatus.OK);
    }
}
