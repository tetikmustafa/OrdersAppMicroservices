package com.smartera.productservice.controller;


import com.smartera.productservice.dto.ProductCreateDto;
import com.smartera.productservice.dto.ProductDto;
import com.smartera.productservice.dto.ProductIdDto;
import com.smartera.productservice.entity.Product;
import com.smartera.productservice.mapper.ProductMapper;
import com.smartera.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;


@RestController
@CrossOrigin()
@RequestMapping("products")
public class ProductController{

    @Autowired
    ProductService productService;

    @PostMapping()
    public ProductIdDto save(@RequestBody ProductCreateDto productDto){
        Product product = ProductMapper.toProduct(productDto);
        productService.save(product);
        return ProductMapper.toProductIdDto(product);
    }

    @GetMapping("/{productId}")
    public ProductDto findById(@PathVariable UUID productId){
        Product product = productService.findById(productId);
        return ProductMapper.toProductDto(product);
    }

    @GetMapping()
    public List<ProductDto> findAll(){
        return productService.findAll()
                .stream().map(ProductMapper::toProductDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<ProductDto> findByKeyword(@PathVariable String keyword){
        return productService.findByKeyword(keyword)
                .stream().map(ProductMapper::toProductDto).toList();
    }

    @PutMapping("/{productId}")
    public ProductIdDto update(@RequestBody ProductCreateDto productDto,@PathVariable UUID productId){
        Product product = ProductMapper.toProduct(productDto);
        product.setProductId(productId);
        productService.update(product);
        return ProductMapper.toProductIdDto(product);
    }

    @DeleteMapping("/{productId}")
    public String deleteById(@PathVariable UUID productId){
        productService.deleteById(productId);
        return "Product with id " + productId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll(){
        productService.deleteAll();
        return "All products have been deleted";
    }
}
