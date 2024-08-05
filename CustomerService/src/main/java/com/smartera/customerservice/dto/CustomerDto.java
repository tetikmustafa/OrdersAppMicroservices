package com.smartera.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;


@Getter
@Setter
public class CustomerDto {
    private String customerId;
    private String customerName;
    private String customerDescription;
    private boolean customerAuthorization;
    private List<String> customerOrdersIds;
}
