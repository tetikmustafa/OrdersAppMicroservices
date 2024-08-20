package com.smartera.orderservice.servicetest;

import com.smartera.orderservice.entity.Order;
import com.smartera.orderservice.service.OrderService;
import com.smartera.orderservice.service.impl.ProductServiceImpl;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.exception.ProductNotFoundException;
import com.smartera.orderservice.repository.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ProductServiceImplTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private OrderService orderService;

    @InjectMocks
    private ProductServiceImpl productService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    @Captor
    private ArgumentCaptor<Order> orderArgumentCaptor;

    private Product product;

    private Order order;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductDescription("Test Product Description");
        product.setProductPrice(100.0);
        product.setProductStock(10);

        order = new Order();
        order.setOrderId("1");
        order.setOrderName("Test Order");
        order.setOrderDescription("Test Order Description");
        order.setOrderProductsIds(new ArrayList<>());
    }

    @Test
    public void testSaveProduct() {
        productService.save(product);

        verify(productRepository, times(1)).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        assertEquals(product.getProductId(), capturedProduct.getProductId());
        assertEquals(product.getProductName(), capturedProduct.getProductName());
        assertEquals(product.getProductDescription(), capturedProduct.getProductDescription());
        assertEquals(product.getProductPrice(), capturedProduct.getProductPrice());
        assertEquals(product.getProductStock(), capturedProduct.getProductStock());
    }

    @Test
    public void testFindProductById_ProductExists() {
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        Product foundProduct = productService.findById("1");

        assertEquals(product.getProductId(), foundProduct.getProductId());
        verify(productRepository, times(1)).findById("1");
    }

    @Test
    public void testFindProductById_ProductNotExists() {
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.findById("1");
        });

        verify(productRepository, times(1)).findById("1");
    }

    @Test
    public void testFindAllProducts() {
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productRepository.findAll()).thenReturn(products);

        List<Product> foundProducts = productService.findAll();

        assertEquals(1, foundProducts.size());
        assertEquals(product.getProductId(), foundProducts.get(0).getProductId());
        verify(productRepository, times(1)).findAll();
    }

    @Test
    public void testFindProductsByKeyword() {
        List<Product> products = new ArrayList<>();
        products.add(product);

        when(productRepository.findByProductNameContainingOrProductDescriptionContaining("Test", "Test"))
                .thenReturn(products);

        List<Product> foundProducts = productService.findByKeyword("Test");

        assertEquals(1, foundProducts.size());
        assertEquals(product.getProductId(), foundProducts.get(0).getProductId());
        verify(productRepository, times(1))
                .findByProductNameContainingOrProductDescriptionContaining("Test", "Test");
    }

    @Test
    public void testUpdateProduct_ProductExists() {
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        productService.update(product);

        verify(productRepository, times(1)).findById("1");

        verify(productRepository, times(1)).save(productArgumentCaptor.capture());
        Product capturedProduct = productArgumentCaptor.getValue();
        assertEquals(product.getProductId(), capturedProduct.getProductId());
        assertEquals(product.getProductName(), capturedProduct.getProductName());
        assertEquals(product.getProductDescription(), capturedProduct.getProductDescription());
        assertEquals(product.getProductPrice(), capturedProduct.getProductPrice());
        assertEquals(product.getProductStock(), capturedProduct.getProductStock());
    }

    @Test
    public void testUpdateProduct_ProductNotExists() {
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.update(product);
        });

        verify(productRepository, times(1)).findById("1");

        verify(productRepository, never()).save(any(Product.class));
    }

    @Test
    public void testDeleteProductById_ProductExists() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.findAll()).thenReturn(orders);
        when(productRepository.findById("1")).thenReturn(Optional.of(product));

        productService.deleteById("1");

        verify(productRepository, times(1)).findById("1");

        verify(orderService, times(1)).findAll();

        verify(orderService, times(1)).update(orderArgumentCaptor.capture(), anyString());
        Order capturedOrder = orderArgumentCaptor.getValue();
        assertFalse(capturedOrder.getOrderProductsIds().contains("1"));

        verify(productRepository, times(1)).deleteById("1");
    }

    @Test
    public void testDeleteProductById_ProductNotExists() {
        when(productRepository.findById("1")).thenReturn(Optional.empty());

        assertThrows(ProductNotFoundException.class, () -> {
            productService.deleteById("1");
        });

        verify(productRepository, times(1)).findById("1");
        verify(orderService, never()).findAll();
        verify(orderService, never()).update(any(Order.class), anyString());
        verify(productRepository, never()).deleteById(anyString());
    }

    @Test
    public void testDeleteAllProducts() {
        List<Order> orders = new ArrayList<>();
        orders.add(order);
        when(orderService.findAll()).thenReturn(orders);


        productService.deleteAll();

        verify(orderService, times(1)).findAll();
        verify(orderService, times(1)).update(orderArgumentCaptor.capture(), anyString());
        Order capturedOrder = orderArgumentCaptor.getValue();
        assertTrue(capturedOrder.getOrderProductsIds().isEmpty());
        verify(productRepository, times(1)).deleteAll();
    }
}
