package com.smartera.orderservice.controllertest;

import com.smartera.orderservice.controller.OrderController;
import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.serviceview.OrderServiceView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(SpringExtension.class)
@WebMvcTest(OrderController.class)
public class OrderControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceView orderServiceView;

    private OrderWriteDto orderWriteDto;
    private OrderReadDto orderReadDto;
    private OrderReadDto orderReadDto2;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        orderWriteDto = new OrderWriteDto();
        orderWriteDto.setOrderName("Order Name");
        orderWriteDto.setOrderDescription("Order Description");
        orderWriteDto.setOrderProductsIds(List.of("prod1", "prod2"));

        orderReadDto = new OrderReadDto();
        orderReadDto.setOrderId("1");
        orderReadDto.setOrderCustomerId("customer1");
        orderReadDto.setOrderName("Order Name");
        orderReadDto.setOrderDescription("Order Description");
        orderReadDto.setOrderProductsIds(List.of("prod1", "prod2"));

        orderReadDto2 = new OrderReadDto();
        orderReadDto2.setOrderId("1");
        orderReadDto2.setOrderCustomerId("customer1");
        orderReadDto2.setOrderName("Updated Order Name");
        orderReadDto2.setOrderDescription("Updated Order Description");
        orderReadDto2.setOrderProductsIds(List.of("prod1", "prod2"));
    }

    @Test
    public void testSaveOrder() throws Exception {
        when(orderServiceView.save(any(OrderWriteDto.class), any(String.class))).thenReturn("1");
        when(orderServiceView.findById("1")).thenReturn(orderReadDto);

        mockMvc.perform(post("/orders/customer1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderName\":\"Order Name\",\"orderDescription\":\"Order Description\",\"orderProductsIds\":[\"prod1\",\"prod2\"]}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.orderId").value("1"))
                .andExpect(jsonPath("$.orderCustomerId").value("customer1"))
                .andExpect(jsonPath("$.orderName").value("Order Name"))
                .andExpect(jsonPath("$.orderDescription").value("Order Description"))
                .andExpect(jsonPath("$.orderProductsIds[0]").value("prod1"))
                .andExpect(jsonPath("$.orderProductsIds[1]").value("prod2"));
    }

    @Test
    public void testFindOrderById() throws Exception {
        when(orderServiceView.findById("1")).thenReturn(orderReadDto);

        mockMvc.perform(get("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("1"))
                .andExpect(jsonPath("$.orderCustomerId").value("customer1"))
                .andExpect(jsonPath("$.orderName").value("Order Name"))
                .andExpect(jsonPath("$.orderDescription").value("Order Description"))
                .andExpect(jsonPath("$.orderProductsIds[0]").value("prod1"))
                .andExpect(jsonPath("$.orderProductsIds[1]").value("prod2"));
    }

    @Test
    public void testFindAllOrders() throws Exception {
        List<OrderReadDto> orders = new ArrayList<>();
        orders.add(orderReadDto);

        when(orderServiceView.findAll()).thenReturn(orders);

        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value("1"))
                .andExpect(jsonPath("$[0].orderCustomerId").value("customer1"))
                .andExpect(jsonPath("$[0].orderName").value("Order Name"))
                .andExpect(jsonPath("$[0].orderDescription").value("Order Description"))
                .andExpect(jsonPath("$[0].orderProductsIds[0]").value("prod1"))
                .andExpect(jsonPath("$[0].orderProductsIds[1]").value("prod2"));
    }

    @Test
    public void testFindByKeyword() throws Exception {
        List<OrderReadDto> orders = new ArrayList<>();
        orders.add(orderReadDto);

        when(orderServiceView.findByKeyword("Order")).thenReturn(orders);

        mockMvc.perform(get("/orders/keyword/Order"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value("1"))
                .andExpect(jsonPath("$[0].orderCustomerId").value("customer1"))
                .andExpect(jsonPath("$[0].orderName").value("Order Name"))
                .andExpect(jsonPath("$[0].orderDescription").value("Order Description"))
                .andExpect(jsonPath("$[0].orderProductsIds[0]").value("prod1"))
                .andExpect(jsonPath("$[0].orderProductsIds[1]").value("prod2"));
    }

    @Test
    public void testFindByCustomerId() throws Exception {
        List<OrderReadDto> orders = new ArrayList<>();
        orders.add(orderReadDto);

        when(orderServiceView.findByCustomerId("customer1")).thenReturn(orders);

        mockMvc.perform(get("/orders/byCustomerId/customer1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].orderId").value("1"))
                .andExpect(jsonPath("$[0].orderCustomerId").value("customer1"))
                .andExpect(jsonPath("$[0].orderName").value("Order Name"))
                .andExpect(jsonPath("$[0].orderDescription").value("Order Description"))
                .andExpect(jsonPath("$[0].orderProductsIds[0]").value("prod1"))
                .andExpect(jsonPath("$[0].orderProductsIds[1]").value("prod2"));
    }

    @Test
    public void testUpdateOrder() throws Exception {
        doNothing().when(orderServiceView).update(any(OrderWriteDto.class), any(String.class));
        when(orderServiceView.findById("1")).thenReturn(orderReadDto2);

        mockMvc.perform(put("/orders/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"orderName\":\"Updated Order Name\",\"orderDescription\":\"Updated Order Description\",\"orderProductsIds\":[\"prod1\",\"prod2\"]}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.orderId").value("1"))
                .andExpect(jsonPath("$.orderCustomerId").value("customer1"))
                .andExpect(jsonPath("$.orderName").value("Updated Order Name"))
                .andExpect(jsonPath("$.orderDescription").value("Updated Order Description"))
                .andExpect(jsonPath("$.orderProductsIds[0]").value("prod1"))
                .andExpect(jsonPath("$.orderProductsIds[1]").value("prod2"));
    }

    @Test
    public void testDeleteOrderById() throws Exception {
        doNothing().when(orderServiceView).deleteById("1");

        mockMvc.perform(delete("/orders/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Order with id 1 has been deleted"));
    }

    @Test
    public void testDeleteByCustomerId() throws Exception {
        doNothing().when(orderServiceView).deleteByCustomerId("customer1");

        mockMvc.perform(delete("/orders/byCustomerId/customer1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Orders of customer with id customer1 have been deleted"));
    }

    @Test
    public void testDeleteAllOrders() throws Exception {
        doNothing().when(orderServiceView).deleteAll();

        mockMvc.perform(delete("/orders"))
                .andExpect(status().isOk())
                .andExpect(content().string("All orders have been deleted"));
    }
}

