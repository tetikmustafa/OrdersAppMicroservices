package com.smartera.customerservice.controller;

import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.mapper.CustomerMapper;
import com.smartera.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("customers")
public class
CustomerController{

    @Autowired
    CustomerService customerService;

    @PostMapping()
    public CustomerReadDto save(@RequestBody CustomerWriteDto customerWriteDto) {
        Customer customer = CustomerMapper.toCustomer(customerWriteDto);
        customerService.save(customer);
        return CustomerMapper.toCustomerReadDto(customerService.findById(customer.getCustomerId()));
    }

    @GetMapping("/{customerId}")
    public CustomerReadDto findById(@PathVariable String customerId) {
        return CustomerMapper.toCustomerReadDto(customerService.findById(customerId));
    }

    @GetMapping()
    public List<CustomerReadDto> findAll() {
        return customerService.findAll()
                .stream().map(CustomerMapper::toCustomerReadDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<CustomerReadDto> findByKeyword(@PathVariable String keyword) {
        return customerService.findByKeyword(keyword)
                .stream().map(CustomerMapper::toCustomerReadDto).toList();
    }

    @PutMapping("/{customerId}")
    public CustomerReadDto update(@RequestBody CustomerUpdateDto customerUpdateDto, @PathVariable String customerId) {
        Customer customer = CustomerMapper.toCustomer(customerUpdateDto);
        customer.setCustomerId(customerId);
        customerService.update(customer);
        return CustomerMapper.toCustomerReadDto(customerService.findById(customerId));
    }


    @PutMapping("/{customerId}/authorize")
    public String authorize(@PathVariable String customerId) {
        customerService.authorize(customerId);
        return "Customer "+customerId+" has been authorized.";
    }

    @DeleteMapping("/{customerId}")
    public String deleteById(@PathVariable String customerId) {
        customerService.deleteById(customerId);
        return "Customer with id " + customerId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll() {
        customerService.deleteAll();
        return "All customers have been deleted";
    }
}
