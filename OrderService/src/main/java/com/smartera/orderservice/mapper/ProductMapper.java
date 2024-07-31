package com.smartera.orderservice.mapper;

import com.smartera.orderservice.dto.product.ProductCreateDto;
import com.smartera.orderservice.dto.product.ProductDto;
import com.smartera.orderservice.dto.product.ProductIdDto;
import com.smartera.orderservice.entity.Product;

public class ProductMapper {
    public static ProductDto toProductDto(Product product) {
        ProductDto productDto = new ProductDto();
        productDto.setProductId(product.getProductId());
        productDto.setProductName(product.getProductName());
        productDto.setProductDescription(product.getProductDescription());
        productDto.setProductPrice(product.getProductPrice());
        productDto.setProductStock(product.getProductStock());
        return productDto;
    }

    public static Product toProduct(ProductCreateDto productCreateDto) {
        Product product = new Product();
        product.setProductName(productCreateDto.getProductName());
        product.setProductDescription(productCreateDto.getProductDescription());
        product.setProductPrice(productCreateDto.getProductPrice());
        product.setProductStock(productCreateDto.getProductStock());
        return product;
    }

    public static ProductIdDto toProductIdDto(Product product) {
        ProductIdDto productIdDto = new ProductIdDto();
        productIdDto.setProductId(product.getProductId());
        return productIdDto;
    }
}
