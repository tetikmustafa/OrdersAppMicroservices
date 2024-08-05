package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductWriteDto {
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
}
