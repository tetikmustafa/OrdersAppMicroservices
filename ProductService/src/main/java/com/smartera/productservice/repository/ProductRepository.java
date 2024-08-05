package com.smartera.productservice.repository;


import com.smartera.productservice.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ProductRepository extends JpaRepository<Product, String> {

    List<Product> findByProductNameContainingOrProductDescriptionContaining(String keyword, String keyword1);
}
