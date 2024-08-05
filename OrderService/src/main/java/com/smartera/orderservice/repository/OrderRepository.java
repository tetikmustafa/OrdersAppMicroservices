package com.smartera.orderservice.repository;

import com.smartera.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends JpaRepository<Order, String> {

    List<Order> findByOrderCustomerId(String customerId);

    List<Order> findByOrderCustomerIdAndOrderDescriptionContaining(String customerId, String keyword);

    List<Order> findByOrderNameContainingOrOrderDescriptionContaining(String keyword, String keyword1);
}
