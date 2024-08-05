package com.smartera.orderservice.controller;


import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.serviceview.ProductServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductServiceView productServiceView;

    @PostMapping()
    public ProductReadDto save(@RequestBody ProductWriteDto productWriteDto){
        String productId = productServiceView.save(productWriteDto);
        return productServiceView.findById(productId);
    }

    @GetMapping("/{productId}")
    public ProductReadDto findById(@PathVariable String productId){
        return productServiceView.findById(productId);
    }

    @GetMapping()
    public List<ProductReadDto> findAll(){
        return productServiceView.findAll();
    }

    @GetMapping("/keyword/{keyword}")
    public List<ProductReadDto> findByKeyword(@PathVariable String keyword){
        return productServiceView.findByKeyword(keyword);
    }

    @PutMapping("/{productId}")
    public ProductReadDto update(@RequestBody ProductWriteDto productDto, @PathVariable String productId){
        productServiceView.update(productDto, productId);
        return productServiceView.findById(productId);
    }

    @DeleteMapping("/{productId}")
    public String deleteById(@PathVariable String productId){
        productServiceView.deleteById(productId);
        return "Product with id " + productId + " has been deleted";
    }

    @DeleteMapping()
    public String deleteAll(){
        productServiceView.deleteAll();
        return "All products have been deleted";
    }
}
