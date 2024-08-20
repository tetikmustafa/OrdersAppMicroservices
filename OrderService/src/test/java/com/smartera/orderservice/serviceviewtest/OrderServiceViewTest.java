package com.smartera.orderservice.serviceviewtest;

import com.smartera.orderservice.dto.OrderReadDto;
import com.smartera.orderservice.dto.OrderWriteDto;
import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.service.OrderService;
import com.smartera.orderservice.serviceview.impl.OrderServiceViewImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;


public class OrderServiceViewTest {

    @InjectMocks
    private OrderServiceViewImpl orderServiceView;

    @Mock
    private OrderService orderService;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    private Order order;
    private OrderWriteDto orderWriteDto;
    private OrderReadDto orderReadDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        order = new Order();
        order.setOrderId("1");
        order.setOrderName("Test Order");
        order.setOrderDescription("Test Description");
        order.setOrderCustomerId("1");
        order.setOrderProductsIds(new ArrayList<>());

        orderWriteDto = new OrderWriteDto();
        orderWriteDto.setOrderName("Test Order");
        orderWriteDto.setOrderDescription("Test Description");
        orderWriteDto.setOrderProductsIds(new ArrayList<>());

        orderReadDto = new OrderReadDto();
        orderReadDto.setOrderId("1");
        orderReadDto.setOrderName("Test Order");
        orderReadDto.setOrderDescription("Test Description");
        orderReadDto.setOrderCustomerId("1");
        orderReadDto.setOrderProductsIds(new ArrayList<>());
    }

    @Test
    public void saveOrderTest() {
        doNothing().when(orderService).save(order, "1");
        orderServiceView.save(orderWriteDto, "1");
        verify(orderService, times(1)).save(orderArgumentCaptor.capture(), eq("1"));
        assertEquals(order.getOrderName(), orderArgumentCaptor.getValue().getOrderName());
        assertEquals(order.getOrderDescription(), orderArgumentCaptor.getValue().getOrderDescription());
        assertEquals(order.getOrderProductsIds().size(), orderArgumentCaptor.getValue().getOrderProductsIds().size());

    }

    @Test
    public void findByIdTest() {
        when(orderService.findById("1")).thenReturn(order);
        OrderReadDto orderReadDto = orderServiceView.findById("1");
        assertEquals(order.getOrderId(), orderReadDto.getOrderId());
        assertEquals(order.getOrderName(), orderReadDto.getOrderName());
        assertEquals(order.getOrderDescription(), orderReadDto.getOrderDescription());
        assertEquals(order.getOrderCustomerId(), orderReadDto.getOrderCustomerId());
        assertEquals(order.getOrderProductsIds().size(), orderReadDto.getOrderProductsIds().size());
        verify(orderService, times(1)).findById("1");
    }

    @Test
    public void findAllTest() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.findAll()).thenReturn(orders);;
        List<OrderReadDto> orderReadDtos = orderServiceView.findAll();
        assertEquals(orders.size(), orderReadDtos.size());
        assertEquals(orders.get(0).getOrderId(), orderReadDtos.get(0).getOrderId());
        assertEquals(orders.get(0).getOrderName(), orderReadDtos.get(0).getOrderName());
        assertEquals(orders.get(0).getOrderDescription(), orderReadDtos.get(0).getOrderDescription());
        assertEquals(orders.get(0).getOrderCustomerId(), orderReadDtos.get(0).getOrderCustomerId());
        assertEquals(orders.get(0).getOrderProductsIds().size(), orderReadDtos.get(0).getOrderProductsIds().size());

        verify(orderService, times(1)).findAll();
    }

    @Test
    public void findByKeywordTest() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.findByKeyword("Test")).thenReturn(orders);
        List<OrderReadDto> orderReadDtos = orderServiceView.findByKeyword("Test");
        assertEquals(orders.size(), orderReadDtos.size());
        assertEquals(orders.get(0).getOrderId(), orderReadDtos.get(0).getOrderId());
        assertEquals(orders.get(0).getOrderName(), orderReadDtos.get(0).getOrderName());
        assertEquals(orders.get(0).getOrderDescription(), orderReadDtos.get(0).getOrderDescription());
        assertEquals(orders.get(0).getOrderCustomerId(), orderReadDtos.get(0).getOrderCustomerId());
        assertEquals(orders.get(0).getOrderProductsIds().size(), orderReadDtos.get(0).getOrderProductsIds().size());

        verify(orderService, times(1)).findByKeyword("Test");
    }

    @Test
    public void findByCustomerIdTest() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.findByCustomerId("1")).thenReturn(orders);
        List<OrderReadDto> orderReadDtos = orderServiceView.findByCustomerId("1");
        assertEquals(orders.size(), orderReadDtos.size());
        assertEquals(orders.get(0).getOrderId(), orderReadDtos.get(0).getOrderId());
        assertEquals(orders.get(0).getOrderName(), orderReadDtos.get(0).getOrderName());
        assertEquals(orders.get(0).getOrderDescription(), orderReadDtos.get(0).getOrderDescription());
        assertEquals(orders.get(0).getOrderCustomerId(), orderReadDtos.get(0).getOrderCustomerId());
        assertEquals(orders.get(0).getOrderProductsIds().size(), orderReadDtos.get(0).getOrderProductsIds().size());

        verify(orderService, times(1)).findByCustomerId("1");
    }

    @Test
    public void findByCustomerIdKeywordTest() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.findByCustomerIdKeyword("1", "Test")).thenReturn(orders);
        List<OrderReadDto> orderReadDtos = orderServiceView.findByCustomerIdKeyword("1", "Test");
        assertEquals(orders.size(), orderReadDtos.size());
        assertEquals(orders.get(0).getOrderId(), orderReadDtos.get(0).getOrderId());
        assertEquals(orders.get(0).getOrderName(), orderReadDtos.get(0).getOrderName());
        assertEquals(orders.get(0).getOrderDescription(), orderReadDtos.get(0).getOrderDescription());
        assertEquals(orders.get(0).getOrderCustomerId(), orderReadDtos.get(0).getOrderCustomerId());
        assertEquals(orders.get(0).getOrderProductsIds().size(), orderReadDtos.get(0).getOrderProductsIds().size());

        verify(orderService, times(1)).findByCustomerIdKeyword("1", "Test");
    }

    @Test
    public void updateTest() {
        orderServiceView.update(orderWriteDto, "1");
        verify(orderService, times(1)).update(orderArgumentCaptor.capture(), eq("1"));
        assertEquals(order.getOrderName(), orderArgumentCaptor.getValue().getOrderName());
        assertEquals(order.getOrderDescription(), orderArgumentCaptor.getValue().getOrderDescription());
        assertEquals(order.getOrderProductsIds().size(), orderArgumentCaptor.getValue().getOrderProductsIds().size());
    }

    @Test
    public void deleteByIdTest() {
        orderServiceView.deleteById("1");
        verify(orderService, times(1)).deleteById("1");
    }

    @Test
    public void deleteAllTest() {
        orderServiceView.deleteAll();
        verify(orderService, times(1)).deleteAll();
    }

    @Test
    public void deleteByCustomerIdTest() {
        orderServiceView.deleteByCustomerId("1");
        verify(orderService, times(1)).deleteByCustomerId("1");
    }
}
