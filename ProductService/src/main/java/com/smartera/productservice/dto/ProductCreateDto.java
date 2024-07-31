package com.smartera.productservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductCreateDto {
    private String productName;
    private String productDescription;
    private double productPrice;
    private int productStock;
}
