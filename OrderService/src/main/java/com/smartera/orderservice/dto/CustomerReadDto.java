package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class CustomerReadDto extends CustomerUpdateDto{
    private String customerId;
}
