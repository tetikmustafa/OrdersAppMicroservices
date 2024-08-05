package com.smartera.orderservice.service.impl;


import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.exception.ProductNotFoundException;
import com.smartera.orderservice.repository.ProductRepository;
import com.smartera.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    public void save(Product product) {
        productRepository.save(product);
    }

    public Product findById(String productId) {
        return productRepository.findById(productId).orElseThrow(() -> new ProductNotFoundException(productId));
    }

    public List<Product> findAll(){
        return productRepository.findAll();
    }

    public List<Product>  findByKeyword(String keyword){
        return productRepository.findByProductNameContainingOrProductDescriptionContaining(keyword, keyword);
    }


    public void update(Product product) {
        Optional<Product> o = productRepository.findById(product.getProductId());
        if (o.isEmpty()) {
            throw new ProductNotFoundException(product.getProductId());
        }
        productRepository.save(product);

    }

    public void deleteById(String productId){
        Optional<Product> o = productRepository.findById(productId);
        if (o.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        productRepository.deleteById(productId);
    }

    public void deleteAll(){
        productRepository.deleteAll();
    }
}
