package com.smartera.orderservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class CustomerUpdateDto {
    private String customerName;
    private String customerDescription;
    private boolean customerAuthorization;
    private List<String> customerOrdersIds;
}
