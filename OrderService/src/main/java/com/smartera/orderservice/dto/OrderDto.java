package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderDto {
    private UUID orderId;
    private UUID orderCustomerId;
    private String orderName;
    private String orderDescription;
    private List<UUID> orderProductsIds;
}
