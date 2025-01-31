package com.smartera.orderservice.service.impl;


import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.exception.ProductNotFoundException;
import com.smartera.orderservice.repository.ProductRepository;
import com.smartera.orderservice.service.OrderService;
import com.smartera.orderservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    OrderService orderService;

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
        if (product.getProductName() == null || product.getProductName().isEmpty()) {
            product.setProductName(o.get().getProductName());
        }
        if (product.getProductDescription() == null || product.getProductDescription().isEmpty()) {
            product.setProductDescription(o.get().getProductDescription());
        }
        if (product.getProductPrice() == 0) {
            product.setProductPrice(o.get().getProductPrice());
        }
        if (product.getProductStock() == 0) {
            product.setProductStock(o.get().getProductStock());
        }
        productRepository.save(product);

    }

    public void deleteById(String productId){
        Optional<Product> o = productRepository.findById(productId);
        if (o.isEmpty()) {
            throw new ProductNotFoundException(productId);
        }
        orderService.findAll().forEach(order -> {
            order.getOrderProductsIds().removeIf(p -> p.equals(productId));
            orderService.update(order,order.getOrderId());
        });
        productRepository.deleteById(productId);
    }

    public void deleteAll(){
        orderService.findAll().forEach(order -> {
            order.getOrderProductsIds().clear();
            orderService.update(order,order.getOrderId());
        });
        productRepository.deleteAll();
    }
}
