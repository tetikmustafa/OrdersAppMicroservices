package com.smartera.orderservice.controller;


import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.serviceview.ProductServiceView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin()
@RequestMapping("products")
public class ProductController {

    @Autowired
    ProductServiceView productServiceView;

    @PostMapping()
    public ResponseEntity<ProductReadDto> save(@RequestBody ProductWriteDto productWriteDto){
        String productId = productServiceView.save(productWriteDto);
        return new ResponseEntity<>(productServiceView.findById(productId), HttpStatus.CREATED);
    }

    @GetMapping("/{productId}")
    public ResponseEntity<ProductReadDto> findById(@PathVariable String productId){
        return new ResponseEntity<>(productServiceView.findById(productId),HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<ProductReadDto>> findAll(){
        return new ResponseEntity<>(productServiceView.findAll(),HttpStatus.OK);
    }

    @GetMapping("/keyword/{keyword}")
    public ResponseEntity<List<ProductReadDto>> findByKeyword(@PathVariable String keyword){
        return new ResponseEntity<>(productServiceView.findByKeyword(keyword),HttpStatus.OK);
    }

    @PutMapping("/{productId}")
    public ResponseEntity<ProductReadDto> update(@RequestBody ProductWriteDto productDto, @PathVariable String productId){
        productServiceView.update(productDto, productId);
        return new ResponseEntity<>(productServiceView.findById(productId),HttpStatus.OK);
    }

    @DeleteMapping("/{productId}")
    public ResponseEntity<String> deleteById(@PathVariable String productId){
        productServiceView.deleteById(productId);
        return new ResponseEntity<>("Product with id " + productId + " has been deleted",HttpStatus.OK);
    }

    @DeleteMapping()
    public ResponseEntity<String> deleteAll(){
        productServiceView.deleteAll();
        return new ResponseEntity<>("All products have been deleted",HttpStatus.OK);
    }
}
