package com.smartera.customerservice.controller;

import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.serviceview.CustomerServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin()
@RequestMapping("customers")
public class CustomerController{

    @Autowired
    CustomerServiceView customerServiceView;

    @PostMapping()
    public CustomerReadDto save(@RequestBody CustomerWriteDto customerWriteDto) {
        String customerId = customerServiceView.save(customerWriteDto);
        return customerServiceView.findById(customerId);
    }

    @GetMapping("/{customerId}")
    public CustomerReadDto findById(@PathVariable String customerId) {
        return customerServiceView.findById(customerId);
    }

    @GetMapping()
    public List<CustomerReadDto> findAll() {
        return customerServiceView.findAll();
    }

    @GetMapping("/keyword/{keyword}")
    public List<CustomerReadDto> findByKeyword(@PathVariable String keyword) {
        return customerServiceView.findByKeyword(keyword);
    }

    @PutMapping("/{customerId}")
    public CustomerReadDto update(@RequestBody CustomerUpdateDto customerUpdateDto, @PathVariable String customerId) {
        customerServiceView.update(customerUpdateDto, customerId);
        return customerServiceView.findById(customerId);
    }


    @PutMapping("/{customerId}/authorize")
    public String authorize(@PathVariable String customerId) {
        customerServiceView.authorize(customerId);
        return "Customer "+customerId+" has been authorized.";
    }

    @DeleteMapping("/{customerId}")
    public String deleteById(@PathVariable String customerId) {
        customerServiceView.deleteById(customerId);
        return "Customer with id " + customerId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        customerServiceView.deleteAll();
        return "All customers have been deleted";
    }
}
