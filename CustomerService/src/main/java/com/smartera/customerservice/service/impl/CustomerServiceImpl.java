package com.smartera.customerservice.service.impl;

import com.smartera.customerservice.client.OrderControllerClient;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.exception.CustomerNotFoundException;
import com.smartera.customerservice.repository.CustomerRepository;
import com.smartera.customerservice.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    CustomerRepository customerRepository;


    @Autowired
    OrderControllerClient orderControllerClient;

    public void save(Customer customer) {
        customerRepository.save(customer);
    }

    public Customer findById(String customerId) {
        return  customerRepository.findById(customerId).orElseThrow(() -> new CustomerNotFoundException(customerId));
    }

    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    public List<Customer> findByKeyword(String keyword) {
        return customerRepository.findByCustomerNameContainingOrCustomerDescriptionContaining(keyword, keyword);
    }

    public void update(Customer customer) {
        Optional<Customer> o = customerRepository.findById(customer.getCustomerId());
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customer.getCustomerId());
        }
        if(customer.getCustomerName() == null || customer.getCustomerName().isEmpty()){
            customer.setCustomerName(o.get().getCustomerName());
        }
        if(customer.getCustomerDescription() == null || customer.getCustomerDescription().isEmpty()){
            customer.setCustomerDescription(o.get().getCustomerDescription());
        }
        customer.setCustomerAuthorization(o.get().isCustomerAuthorization());
        if(customer.getCustomerOrdersIds() == null || customer.getCustomerOrdersIds().isEmpty()){
            customer.setCustomerOrdersIds(o.get().getCustomerOrdersIds());
        }
        customerRepository.save(customer);
    }

    public void deleteById(String customerId) {
        Optional<Customer> o = customerRepository.findById(customerId);
        if (o.isEmpty()) {
            throw new CustomerNotFoundException(customerId);
        }
        orderControllerClient.deleteByCustomerId(customerId);
        customerRepository.deleteById(customerId);
    }

    public void deleteAll() {
        customerRepository.findAll()
                .forEach(customer -> orderControllerClient.deleteByCustomerId(customer.getCustomerId()));
        customerRepository.deleteAll();
    }

    public void authorize(String customerId) {
        Optional<Customer> customer = customerRepository.findById(customerId);
        if(customer.isPresent()){
            Customer c = customer.get();
            c.setCustomerAuthorization(!c.isCustomerAuthorization());
            customerRepository.save(c);
        }
        else{
            throw new CustomerNotFoundException(customerId);
        }
    }
}
