package com.smartera.orderservice.controller;


import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.mapper.ProductMapper;
import com.smartera.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping()
    public ProductReadDto save(@RequestBody ProductWriteDto productDto){
        Product product = ProductMapper.toProduct(productDto);
        productService.save(product);
        return ProductMapper.toProductReadDto(product);
    }

    @GetMapping("/{productId}")
    public ProductReadDto findById(@PathVariable String productId){
        Product product = productService.findById(productId);
        return ProductMapper.toProductReadDto(product);
    }

    @GetMapping()
    public List<ProductReadDto> findAll(){
        return productService.findAll()
                .stream().map(ProductMapper::toProductReadDto).toList();
    }

    @GetMapping("/keyword/{keyword}")
    public List<ProductReadDto> findByKeyword(@PathVariable String keyword){
        return productService.findByKeyword(keyword)
                .stream().map(ProductMapper::toProductReadDto).toList();
    }

    @PutMapping("/{productId}")
    public ProductReadDto update(@RequestBody ProductWriteDto productDto, @PathVariable String productId){
        Product product = ProductMapper.toProduct(productDto);
        product.setProductId(productId);
        productService.update(product);
        return ProductMapper.toProductReadDto(product);
    }

    @DeleteMapping("/{productId}")
    public String deleteById(@PathVariable String productId){
        productService.deleteById(productId);
        return "Product with id " + productId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll(){
        productService.deleteAll();
        return "All products have been deleted";
    }
}
