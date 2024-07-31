package com.smartera.customerservice.dto.order;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.UUID;

@Getter
@Setter
public class OrderCreateDto {
    private String orderName;
    private String orderDescription;
    private List<UUID> orderProductsIds;
}
