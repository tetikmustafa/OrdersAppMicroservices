package com.smartera.orderservice.mapper;


import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.entity.Product;

public class ProductMapper {
    public static ProductReadDto toProductReadDto(Product product) {
        ProductReadDto productReadDto = new ProductReadDto();
        productReadDto.setProductId(product.getProductId());
        productReadDto.setProductName(product.getProductName());
        productReadDto.setProductDescription(product.getProductDescription());
        productReadDto.setProductPrice(product.getProductPrice());
        productReadDto.setProductStock(product.getProductStock());
        return productReadDto;
    }

    public static Product toProduct(ProductWriteDto productWriteDto) {
        Product product = new Product();
        product.setProductName(productWriteDto.getProductName());
        product.setProductDescription(productWriteDto.getProductDescription());
        product.setProductPrice(productWriteDto.getProductPrice());
        product.setProductStock(productWriteDto.getProductStock());
        return product;
    }
}
