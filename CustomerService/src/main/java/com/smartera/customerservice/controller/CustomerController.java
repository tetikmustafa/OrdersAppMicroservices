package com.smartera.customerservice.controller;

import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.serviceview.CustomerServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("customers")
public class CustomerController{

    @Autowired
    CustomerServiceView customerServiceView;

    @PostMapping()
    public ResponseEntity<CustomerReadDto> save(@RequestBody CustomerWriteDto customerWriteDto) {
        String customerId = customerServiceView.save(customerWriteDto);
        return new ResponseEntity<>(customerServiceView.findById(customerId), HttpStatus.CREATED);
    }

    @GetMapping("/{customerId}")
    public ResponseEntity<CustomerReadDto> findById(@PathVariable String customerId) {
        return new ResponseEntity<>(customerServiceView.findById(customerId), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<CustomerReadDto>> findAll() {
        return new ResponseEntity<>(customerServiceView.findAll(), HttpStatus.OK);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<CustomerReadDto>> findByKeyword(@PathVariable String keyword) {
        return new ResponseEntity<>(customerServiceView.findByKeyword(keyword), HttpStatus.OK);
    }

    @PutMapping("/{customerId}")
    public ResponseEntity<CustomerReadDto> update(@RequestBody CustomerUpdateDto customerUpdateDto, @PathVariable String customerId) {
        customerServiceView.update(customerUpdateDto, customerId);
        return new ResponseEntity<>(customerServiceView.findById(customerId), HttpStatus.OK);
    }


    @PutMapping("/{customerId}/authorize")
    public ResponseEntity<String> authorize(@PathVariable String customerId) {
        customerServiceView.authorize(customerId);
        return new ResponseEntity<>("Customer with id " + customerId + " has been authorized", HttpStatus.OK);
    }

    @DeleteMapping("/{customerId}")
    public ResponseEntity<String> deleteById(@PathVariable String customerId) {
        customerServiceView.deleteById(customerId);
        return new ResponseEntity<>("Customer with id " + customerId + " has been deleted", HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAll() {
        customerServiceView.deleteAll();
        return new ResponseEntity<>("All customers have been deleted", HttpStatus.OK);
    }
}
