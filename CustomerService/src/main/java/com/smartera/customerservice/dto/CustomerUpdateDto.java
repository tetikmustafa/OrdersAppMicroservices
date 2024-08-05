package com.smartera.customerservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CustomerUpdateDto extends CustomerWriteDto {
    private boolean customerAuthorization;
    private List<String> customerOrdersIds;
}
