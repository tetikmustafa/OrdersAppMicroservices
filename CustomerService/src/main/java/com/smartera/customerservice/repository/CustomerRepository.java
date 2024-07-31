package com.smartera.customerservice.repository;

import com.smartera.customerservice.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, UUID> {

    List<Customer> findByCustomerNameContainingOrCustomerDescriptionContaining(String keyword, String keyword1);
}
