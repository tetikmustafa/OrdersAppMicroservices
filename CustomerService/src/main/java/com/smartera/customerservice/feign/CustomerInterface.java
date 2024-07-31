package com.smartera.customerservice.feign;

import com.smartera.customerservice.dto.order.OrderDto;
import com.smartera.customerservice.dto.order.OrderIdDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@FeignClient(name = "ORDERSERVICE")
public interface CustomerInterface {

    @PostMapping("orders")
    public OrderIdDto save(@RequestBody OrderDto orderDto);

    @GetMapping("orders/{orderId}")
    public OrderDto findById(@PathVariable UUID orderId);

    @GetMapping("orders/byCustomerId/{customerId}")
    public List<OrderDto> findByCustomerId(@PathVariable UUID customerId);

    @GetMapping("orders")
    public List<OrderDto> findAll();

    @GetMapping("orders/keyword/{keyword}")
    public List<OrderDto> findByKeyword(@PathVariable String keyword);

    @GetMapping("orders/byCustomerId/{customerId}/{keyword}")
    public List<OrderDto> findByKeyword(@PathVariable UUID customerId, @PathVariable String keyword);

    @PutMapping("orders")
    public OrderIdDto update(@RequestBody OrderDto orderDto);

    @DeleteMapping("orders/{orderId}")
    public String deleteById(@PathVariable UUID orderId);

    @DeleteMapping("orders/byCustomerId/{customerId}")
    public String deleteByCustomerId(@PathVariable UUID customerId);

    @DeleteMapping("orders")
    public String deleteAll();
}
