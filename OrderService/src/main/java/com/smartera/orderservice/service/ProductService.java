package com.smartera.orderservice.service;



import com.smartera.orderservice.entity.Product;

import java.util.List;


public interface ProductService {
    void save(Product product);
    Product findById(String productId);
    List<Product> findAll();
    List<Product> findByKeyword(String keyword);
    void update(Product product);
    void deleteById(String productId);
    void deleteAll();

}
