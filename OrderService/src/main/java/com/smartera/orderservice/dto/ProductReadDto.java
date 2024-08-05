package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ProductReadDto extends ProductWriteDto{
    private String productId;
}
