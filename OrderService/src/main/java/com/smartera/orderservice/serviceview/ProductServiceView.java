package com.smartera.orderservice.serviceview;

import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.dto.ProductWriteDto;

import java.util.List;

public interface ProductServiceView {
    String save(ProductWriteDto product);
    ProductReadDto findById(String productId);
    List<ProductReadDto> findAll();
    List<ProductReadDto> findByKeyword(String keyword);
    void update(ProductWriteDto product, String productId);
    void deleteById(String productId);
    void deleteAll();
}
