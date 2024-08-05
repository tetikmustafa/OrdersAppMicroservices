package com.smartera.orderservice.serviceview.impl;

import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.mapper.ProductMapper;
import com.smartera.orderservice.service.ProductService;
import com.smartera.orderservice.serviceview.ProductServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceViewImpl implements ProductServiceView {

    @Autowired
    ProductService productService;

    public String save(ProductWriteDto productWriteDto) {
        Product product = ProductMapper.toProduct(productWriteDto);
        productService.save(product);
        return product.getProductId();
    }

    public ProductReadDto findById(String productId) {
        return ProductMapper.toProductReadDto(productService.findById(productId));
    }

    public List<ProductReadDto> findAll() {
        return productService.findAll()
                .stream().map(ProductMapper::toProductReadDto).toList();
    }

    public List<ProductReadDto> findByKeyword(String keyword) {
        return productService.findByKeyword(keyword)
                .stream().map(ProductMapper::toProductReadDto).toList();
    }

    public void update(ProductWriteDto productWriteDto, String productId) {
        Product product = ProductMapper.toProduct(productWriteDto);
        product.setProductId(productId);
        productService.update(product);
    }

    public void deleteById(String productId) {
        productService.deleteById(productId);
    }

    public void deleteAll() {
        productService.deleteAll();
    }
}
