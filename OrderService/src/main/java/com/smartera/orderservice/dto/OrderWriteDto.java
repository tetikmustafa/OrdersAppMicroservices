package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrderWriteDto {
    private String orderName;
    private String orderDescription;
    private List<String> orderProductsIds;
}
