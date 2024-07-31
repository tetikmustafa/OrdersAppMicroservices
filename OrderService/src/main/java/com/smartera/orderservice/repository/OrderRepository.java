package com.smartera.orderservice.repository;

import com.smartera.orderservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface OrderRepository extends JpaRepository<Order, UUID> {

    List<Order> findByOrderCustomerId(UUID customerId);

    List<Order> findByOrderCustomerIdAndOrderDescriptionContaining(UUID customerId, String keyword);

    List<Order> findByOrderNameContainingOrOrderDescriptionContaining(String keyword, String keyword1);
}
