package com.smartera.orderservice.serviceviewtest;


import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.entity.Product;
import com.smartera.orderservice.service.ProductService;
import com.smartera.orderservice.serviceview.impl.ProductServiceViewImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class ProductServiceViewTest {

    @InjectMocks
    private ProductServiceViewImpl productServiceView;

    @Mock
    private ProductService productService;

    @Captor
    private ArgumentCaptor<Product> productArgumentCaptor;

    private Product product;

    private ProductWriteDto productWriteDto;
    private ProductReadDto productReadDto;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        product = new Product();
        product.setProductId("1");
        product.setProductName("Test Product");
        product.setProductDescription("Test Description");
        product.setProductPrice(100.0);
        product.setProductStock(10);

        productWriteDto = new ProductWriteDto();
        productWriteDto.setProductName("Test Product");
        productWriteDto.setProductDescription("Test Description");
        productWriteDto.setProductPrice(100.0);
        productWriteDto.setProductStock(10);

        productReadDto = new ProductReadDto();
        productReadDto.setProductId("1");
        productReadDto.setProductName("Test Product");
        productReadDto.setProductDescription("Test Description");
        productReadDto.setProductPrice(100.0);
        productReadDto.setProductStock(10);
    }

    @Test
    public void saveProductTest() {
        doNothing().when(productService).save(product);
        productServiceView.save(productWriteDto);
        verify(productService, times(1)).save(productArgumentCaptor.capture());
        assertEquals(product.getProductName(), productArgumentCaptor.getValue().getProductName());
        assertEquals(product.getProductDescription(), productArgumentCaptor.getValue().getProductDescription());
        assertEquals(product.getProductPrice(), productArgumentCaptor.getValue().getProductPrice());
    }

    @Test
    public void findByIdTest() {
        when(productService.findById("1")).thenReturn(product);
        ProductReadDto productReadDto = productServiceView.findById("1");
        assertEquals(product.getProductName(), productReadDto.getProductName());
        assertEquals(product.getProductDescription(), productReadDto.getProductDescription());
        assertEquals(product.getProductPrice(), productReadDto.getProductPrice());
        assertEquals(product.getProductStock(), productReadDto.getProductStock());

        verify(productService, times(1)).findById("1");
    }

    @Test
    public void findAllTest() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.findAll()).thenReturn(products);
        ProductReadDto productReadDto = productServiceView.findAll().get(0);
        assertEquals(product.getProductName(), productReadDto.getProductName());
        assertEquals(product.getProductDescription(), productReadDto.getProductDescription());
        assertEquals(product.getProductPrice(), productReadDto.getProductPrice());
        assertEquals(product.getProductStock(), productReadDto.getProductStock());
        verify(productService, times(1)).findAll();
    }

    @Test
    public void findByKeywordTest() {
        List<Product> products = new ArrayList<>();
        products.add(product);
        when(productService.findByKeyword("Test")).thenReturn(products);
        ProductReadDto productReadDto = productServiceView.findByKeyword("Test").get(0);
        assertEquals(product.getProductName(), productReadDto.getProductName());
        assertEquals(product.getProductDescription(), productReadDto.getProductDescription());
        assertEquals(product.getProductPrice(), productReadDto.getProductPrice());
        assertEquals(product.getProductStock(), productReadDto.getProductStock());
        verify(productService, times(1)).findByKeyword("Test");
    }

    @Test
    public void updateProductTest() {
        doNothing().when(productService).update(product);
        productServiceView.update(productWriteDto, "1");
        verify(productService, times(1)).update(productArgumentCaptor.capture());
        assertEquals(product.getProductName(), productArgumentCaptor.getValue().getProductName());
        assertEquals(product.getProductDescription(), productArgumentCaptor.getValue().getProductDescription());
        assertEquals(product.getProductPrice(), productArgumentCaptor.getValue().getProductPrice());
        assertEquals(product.getProductStock(), productArgumentCaptor.getValue().getProductStock());
    }

    @Test
    public void deleteByIdTest() {
        productServiceView.deleteById("1");
        verify(productService, times(1)).deleteById("1");
    }

    @Test
    public void deleteAllTest() {
        productServiceView.deleteAll();
        verify(productService, times(1)).deleteAll();
    }

}
