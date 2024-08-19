package com.smartera.customerservice.serviceviewtest;

import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.entity.Customer;
import com.smartera.customerservice.service.CustomerService;
import com.smartera.customerservice.serviceview.impl.CustomerServiceViewImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

public class CustomerServiceViewTest {

    @InjectMocks
    private CustomerServiceViewImpl customerServiceView;

    @Mock
    private CustomerService customerService;

    @Captor
    private ArgumentCaptor<Customer> customerArgumentCaptor;

    private Customer customer;
    private CustomerWriteDto customerWriteDto;
    private CustomerReadDto customerReadDto;
    private CustomerUpdateDto customerUpdateDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        customer = new Customer();
        customer.setCustomerId("1");
        customer.setCustomerName("Test Customer");
        customer.setCustomerDescription("Test Description");
        customer.setCustomerAuthorization(false);
        customer.setCustomerOrdersIds(new ArrayList<>());

        customerWriteDto = new CustomerWriteDto();
        customerWriteDto.setCustomerName("Test Customer");
        customerWriteDto.setCustomerDescription("Test Description");

        customerUpdateDto = new CustomerUpdateDto();
        customerUpdateDto.setCustomerName("Test Customer");
        customerUpdateDto.setCustomerDescription("Test Description");
        customerUpdateDto.setCustomerOrdersIds(new ArrayList<>());

        customerReadDto = new CustomerReadDto();
        customerReadDto.setCustomerId("1");
        customerReadDto.setCustomerName("Test Customer");
        customerReadDto.setCustomerDescription("Test Description");
        customerReadDto.setCustomerAuthorization(false);
        customerReadDto.setCustomerOrdersIds(new ArrayList<>());
    }

    @Test
    public void testSave() {
        customerServiceView.save(customerWriteDto);
        verify(customerService, times(1)).save(customerArgumentCaptor.capture());
        assertEquals("Test Customer", customerArgumentCaptor.getValue().getCustomerName());
        assertEquals("Test Description", customerArgumentCaptor.getValue().getCustomerDescription());
        assertFalse(customerArgumentCaptor.getValue().isCustomerAuthorization());
        assertNull(customerArgumentCaptor.getValue().getCustomerOrdersIds());
    }

    @Test
    public void testFindById() {
        when(customerService.findById("1")).thenReturn(customer);
        CustomerReadDto foundCustomer = customerServiceView.findById("1");
        assertNotNull(foundCustomer);
        assertEquals("Test Customer", foundCustomer.getCustomerName());
        assertEquals("Test Description", foundCustomer.getCustomerDescription());
        assertFalse(foundCustomer.isCustomerAuthorization());
        assertEquals(0, foundCustomer.getCustomerOrdersIds().size());
        verify(customerService, times(1)).findById("1");
    }

    @Test
    public void testFindAll() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerService.findAll()).thenReturn(customers);
        assertEquals(1, customerServiceView.findAll().size());
        verify(customerService, times(1)).findAll();
    }

    @Test
    public void testFindByKeyword() {
        List<Customer> customers = new ArrayList<>();
        customers.add(customer);
        when(customerService.findByKeyword("Test")).thenReturn(customers);
        assertEquals(1, customerServiceView.findByKeyword("Test").size());
        verify(customerService, times(1)).findByKeyword("Test");
    }

    @Test
    public void testUpdate() {
        customerServiceView.update(customerUpdateDto, "1");
        verify(customerService, times(1)).update(customerArgumentCaptor.capture());
        assertEquals("Test Customer", customerArgumentCaptor.getValue().getCustomerName());
        assertEquals("Test Description", customerArgumentCaptor.getValue().getCustomerDescription());
        assertFalse(customerArgumentCaptor.getValue().isCustomerAuthorization());
        assertEquals(0, customerArgumentCaptor.getValue().getCustomerOrdersIds().size());
    }

    @Test
    public void testDeleteById() {
        customerServiceView.deleteById("1");
        verify(customerService, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteAll() {
        customerServiceView.deleteAll();
        verify(customerService, times(1)).deleteAll();
    }

    @Test
    public void testAuthorize() {
        customerServiceView.authorize("1");
        verify(customerService, times(1)).authorize("1");
    }
}
