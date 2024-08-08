package com.smartera.customerservice.servicetest;

import com.smartera.customerservice.service.impl.CustomerServiceImpl;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.exception.CustomerNotFoundException;
import com.smartera.customerservice.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    private Customer customer;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setCustomerId("1");
        customer.setCustomerName("Test Customer");
        customer.setCustomerDescription("Test Description");
        customer.setCustomerAuthorization(false);
        customer.setCustomerOrdersIds(new ArrayList<>());
    }

    @Test
    public void testSaveCustomer() {
        customerService.save(customer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testFindById() {
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        Customer foundCustomer = customerService.findById("1");
        assertNotNull(foundCustomer);
        assertEquals("Test Customer", foundCustomer.getCustomerName());
    }

    @Test
    public void testFindById_NotFound() {
        when(customerRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.findById("1"));
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerRepository.findAll()).thenReturn(customers);

        List<Customer> foundCustomers = customerService.findAll();
        assertEquals(1, foundCustomers.size());
        assertEquals("Test Customer", foundCustomers.get(0).getCustomerName());
    }

    @Test
    public void testFindByKeyword() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerRepository.findByCustomerNameContainingOrCustomerDescriptionContaining("Test", "Test"))
                .thenReturn(customers);

        List<Customer> foundCustomers = customerService.findByKeyword("Test");
        assertEquals(1, foundCustomers.size());
        assertEquals("Test Customer", foundCustomers.get(0).getCustomerName());
    }

    @Test
    public void testUpdateCustomer() {
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        customer.setCustomerName("Updated Customer");
        customerService.update(customer);
        verify(customerRepository, times(1)).save(any(Customer.class));
    }

    @Test
    public void testUpdateCustomer_NotFound() {
        when(customerRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.update(customer));
    }

    @Test
    public void testDeleteById() {
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        customerService.deleteById("1");
        verify(customerRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteById_NotFound() {
        when(customerRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.deleteById("1"));
    }

    @Test
    public void testDeleteAll() {
        customerService.deleteAll();
        verify(customerRepository, times(1)).deleteAll();
    }

    @Test
    public void testAuthorizeCustomer() {
        when(customerRepository.findById("1")).thenReturn(Optional.of(customer));
        customerService.authorize("1");
        verify(customerRepository, times(1)).save(any(Customer.class));
        assertTrue(customer.isCustomerAuthorization());
    }

    @Test
    public void testAuthorizeCustomer_NotFound() {
        when(customerRepository.findById("1")).thenReturn(Optional.empty());
        assertThrows(CustomerNotFoundException.class, () -> customerService.authorize("1"));
    }
}
