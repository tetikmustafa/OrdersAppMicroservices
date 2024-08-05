package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class ProductDto {
    private String productId;
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
}
