package com.smartera.orderservice.servicetest;

import com.smartera.orderservice.service.impl.OrderServiceImpl;
import com.smartera.orderservice.client.CustomerControllerClient;
import com.smartera.orderservice.dto.CustomerReadDto;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.exception.CustomerNotAuthorizedException;
import com.smartera.orderservice.exception.OrderNotFoundException;
import com.smartera.orderservice.repository.OrderRepository;
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

public class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerControllerClient customerControllerClient;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;
    private CustomerReadDto customerReadDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order();
        order.setOrderId("1");
        order.setOrderName("Test Order");
        order.setOrderDescription("Test Order Description");
        order.setOrderCustomerId("customer1");
        order.setOrderProductsIds(List.of("prod1", "prod2"));

        customerReadDto = new CustomerReadDto();
        customerReadDto.setCustomerId("customer1");
        customerReadDto.setCustomerName("Test Customer");
        customerReadDto.setCustomerAuthorization(true);
        customerReadDto.setCustomerOrdersIds(new ArrayList<>());
    }

    @Test
    public void testSaveOrder_CustomerAuthorized() {
        when(customerControllerClient.findById("customer1")).thenReturn(customerReadDto);
        when(orderRepository.save(any(Order.class))).thenReturn(order);

        orderService.save(order, "customer1");

        verify(orderRepository, times(1)).save(order);
        verify(customerControllerClient, times(1)).update(any(), eq("customer1"));
        assertTrue(customerReadDto.getCustomerOrdersIds().contains(order.getOrderId()));
    }

    @Test
    public void testSaveOrder_CustomerNotAuthorized() {
        customerReadDto.setCustomerAuthorization(false);
        when(customerControllerClient.findById("customer1")).thenReturn(customerReadDto);

        assertThrows(CustomerNotAuthorizedException.class, () -> {
            orderService.save(order, "customer1");
        });

        verify(orderRepository, never()).save(any(Order.class));
        verify(customerControllerClient, never()).update(any(), any());
    }

    @Test
    public void testFindOrderById_OrderExists() {
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));

        Order foundOrder = orderService.findById("1");

        assertEquals(order.getOrderId(), foundOrder.getOrderId());
        verify(orderRepository, times(1)).findById("1");
    }

    @Test
    public void testFindOrderById_OrderNotExists() {
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.findById("1");
        });

        verify(orderRepository, times(1)).findById("1");
    }

    @Test
    public void testFindAllOrders() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findAll()).thenReturn(orders);

        List<Order> foundOrders = orderService.findAll();

        assertEquals(1, foundOrders.size());
        assertEquals(order.getOrderId(), foundOrders.get(0).getOrderId());
        verify(orderRepository, times(1)).findAll();
    }

    @Test
    public void testFindOrdersByKeyword() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findByOrderNameContainingOrOrderDescriptionContaining("Test", "Test"))
                .thenReturn(orders);

        List<Order> foundOrders = orderService.findByKeyword("Test");

        assertEquals(1, foundOrders.size());
        assertEquals(order.getOrderId(), foundOrders.get(0).getOrderId());
        verify(orderRepository, times(1))
                .findByOrderNameContainingOrOrderDescriptionContaining("Test", "Test");
    }

    @Test
    public void testUpdateOrder_OrderExists() {
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));

        orderService.update(order, "1");

        verify(orderRepository, times(1)).save(order);
    }

    @Test
    public void testUpdateOrder_OrderNotExists() {
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.update(order, "1");
        });

        verify(orderRepository, never()).save(any(Order.class));
    }

    @Test
    public void testDeleteOrderById_OrderExists() {
        when(orderRepository.findById("1")).thenReturn(Optional.of(order));
        when(customerControllerClient.findById("customer1")).thenReturn(customerReadDto);

        orderService.deleteById("1");

        verify(orderRepository, times(1)).deleteById("1");
        verify(customerControllerClient, times(1)).update(any(), eq("customer1"));
    }

    @Test
    public void testDeleteOrderById_OrderNotExists() {
        when(orderRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(OrderNotFoundException.class, () -> {
            orderService.deleteById("1");
        });

        verify(orderRepository, never()).deleteById(anyString());
        verify(customerControllerClient, never()).update(any(), any());
    }

    @Test
    public void testDeleteOrdersByCustomerId() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);

        when(orderRepository.findByOrderCustomerId("customer1")).thenReturn(orders);
        when(customerControllerClient.findById("customer1")).thenReturn(customerReadDto);

        orderService.deleteByCustomerId("customer1");

        verify(orderRepository, times(1)).deleteById("1");
        verify(customerControllerClient, times(1)).update(any(), eq("customer1"));
    }

    @Test
    public void testDeleteAllOrders() {
        List<CustomerReadDto> customers = new ArrayList<>();
        customers.add(customerReadDto);

        when(customerControllerClient.findAll()).thenReturn(customers);

        orderService.deleteAll();

        verify(orderRepository, times(1)).deleteAll();
        verify(customerControllerClient, times(1)).update(any(), eq("customer1"));
    }
}

