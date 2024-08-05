package com.smartera.orderservice.client;

import com.smartera.orderservice.dto.CustomerReadDto;
import com.smartera.orderservice.dto.CustomerUpdateDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@FeignClient(name = "CUSTOMERSERVICE")
public interface CustomerControllerClient {

    @GetMapping("customers/{customerId}")
    public CustomerReadDto findById(@PathVariable String customerId);

    @GetMapping("customers")
    public List<CustomerReadDto> findAll();

    @PutMapping("customers/{customerId}")
    public CustomerReadDto update(@RequestBody CustomerUpdateDto customerUpdateDto, @PathVariable String customerId);
}
