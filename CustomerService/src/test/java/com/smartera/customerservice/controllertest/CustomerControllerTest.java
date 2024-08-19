package com.smartera.customerservice.controllertest;

import com.smartera.customerservice.controller.CustomerController;
import com.smartera.customerservice.dto.CustomerReadDto;
import com.smartera.customerservice.dto.CustomerUpdateDto;
import com.smartera.customerservice.dto.CustomerWriteDto;
import com.smartera.customerservice.serviceview.CustomerServiceView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CustomerController.class)
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerServiceView customerServiceView;

    @Autowired
    private ObjectMapper objectMapper;

    @Captor
    private ArgumentCaptor<CustomerWriteDto> customerWriteDtoArgumentCaptor;

    @Captor
    private ArgumentCaptor<CustomerUpdateDto> customerUpdateDtoArgumentCaptor;

    private CustomerReadDto customerReadDto;
    private CustomerWriteDto customerWriteDto;
    private CustomerUpdateDto customerUpdateDto;

    @BeforeEach
    void setUp() {
        customerWriteDto = new CustomerWriteDto();
        customerWriteDto.setCustomerName("Test Customer");
        customerWriteDto.setCustomerDescription("Test Description");

        customerUpdateDto = new CustomerUpdateDto();
        customerUpdateDto.setCustomerName("Updated Customer");
        customerUpdateDto.setCustomerDescription("Updated Description");
        customerUpdateDto.setCustomerOrdersIds(new ArrayList<>());

        customerReadDto = new CustomerReadDto();
        customerReadDto.setCustomerId("1");
        customerReadDto.setCustomerName("Test Customer");
        customerReadDto.setCustomerDescription("Test Description");
        customerReadDto.setCustomerAuthorization(false);
        customerReadDto.setCustomerOrdersIds(new ArrayList<>());
    }

    @Test
    void testSaveCustomer() throws Exception {
        when(customerServiceView.save(Mockito.any(CustomerWriteDto.class))).thenReturn("1");
        when(customerServiceView.findById("1")).thenReturn(customerReadDto);

        mockMvc.perform(post("/customers")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerWriteDto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.customerId").value("1"))
                .andExpect(jsonPath("$.customerName").value("Test Customer"));

        verify(customerServiceView).save(customerWriteDtoArgumentCaptor.capture());
        CustomerWriteDto capturedCustomer = customerWriteDtoArgumentCaptor.getValue();
        assert(capturedCustomer.getCustomerName().equals("Test Customer"));
        assert(capturedCustomer.getCustomerDescription().equals("Test Description"));

        verify(customerServiceView).findById("1");


    }

    @Test
    void testFindCustomerById() throws Exception {
        when(customerServiceView.findById("1")).thenReturn(customerReadDto);

        mockMvc.perform(get("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("1"))
                .andExpect(jsonPath("$.customerName").value("Test Customer"));

        verify(customerServiceView).findById("1");
    }

    @Test
    void testFindAllCustomers() throws Exception {
        List<CustomerReadDto> customers = new ArrayList<>();
        customers.add(customerReadDto);

        when(customerServiceView.findAll()).thenReturn(customers);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].customerId").value("1"));

        verify(customerServiceView).findAll();
    }

    @Test
    void testFindCustomersByKeyword() throws Exception {
        List<CustomerReadDto> customers = new ArrayList<>();
        customers.add(customerReadDto);

        when(customerServiceView.findByKeyword("Test")).thenReturn(customers);

        mockMvc.perform(get("/customers/keyword/Test"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].customerId").value("1"));

        verify(customerServiceView).findByKeyword("Test");
    }

    @Test
    void testUpdateCustomer() throws Exception {
        doNothing().when(customerServiceView).update(Mockito.any(CustomerUpdateDto.class), Mockito.eq("1"));
        when(customerServiceView.findById("1")).thenReturn(customerReadDto);

        mockMvc.perform(put("/customers/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(customerUpdateDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.customerId").value("1"))
                .andExpect(jsonPath("$.customerName").value("Test Customer"));

        verify(customerServiceView).update(customerUpdateDtoArgumentCaptor.capture(), eq("1"));
        CustomerUpdateDto capturedCustomer = customerUpdateDtoArgumentCaptor.getValue();
        assert(capturedCustomer.getCustomerName().equals("Updated Customer"));
        assert(capturedCustomer.getCustomerDescription().equals("Updated Description"));
        assert(capturedCustomer.getCustomerOrdersIds().isEmpty());
    }

    @Test
    void testAuthorizeCustomer() throws Exception {
        doNothing().when(customerServiceView).authorize("1");

        mockMvc.perform(put("/customers/1/authorize"))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer with id 1 authorization has been changed."));

        verify(customerServiceView).authorize("1");
    }

    @Test
    void testDeleteCustomerById() throws Exception {
        doNothing().when(customerServiceView).deleteById("1");

        mockMvc.perform(delete("/customers/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Customer with id 1 has been deleted"));

        verify(customerServiceView).deleteById("1");
    }

    @Test
    void testDeleteAllCustomers() throws Exception {
        doNothing().when(customerServiceView).deleteAll();

        mockMvc.perform(delete("/customers"))
                .andExpect(status().isOk())
                .andExpect(content().string("All customers have been deleted"));

        verify(customerServiceView).deleteAll();
    }
}

