package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class OrderDto {
    private String orderId;
    private String orderCustomerId;
    private String orderName;
    private String orderDescription;
    private List<String> orderProductsIds;
}
