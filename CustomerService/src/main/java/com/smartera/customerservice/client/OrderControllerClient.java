package com.smartera.customerservice.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "ORDERSERVICE")
public interface OrderControllerClient {

    @DeleteMapping("orders/byCustomerId/{customerId}")
    public ResponseEntity<String> deleteByCustomerId(@PathVariable String customerId);
}
