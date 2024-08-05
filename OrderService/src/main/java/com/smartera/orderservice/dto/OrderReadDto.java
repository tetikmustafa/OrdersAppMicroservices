package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OrderReadDto extends OrderWriteDto {
    private String orderId;
    private String orderCustomerId;
}
