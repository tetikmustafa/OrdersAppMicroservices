package com.smartera.orderservice.controllertest;

import com.smartera.orderservice.controller.ProductController;
import com.smartera.orderservice.dto.ProductReadDto;
import com.smartera.orderservice.dto.ProductWriteDto;
import com.smartera.orderservice.serviceview.ProductServiceView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
@WebMvcTest(ProductController.class)
public class ProductControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProductServiceView productServiceView;

    private ProductWriteDto productWriteDto;
    private ProductReadDto productReadDto;
    private ProductReadDto productReadDto2;

    @BeforeEach
    public void setUp() {
        productWriteDto = new ProductWriteDto();
        productWriteDto.setProductName("Product Name");
        productWriteDto.setProductDescription("Product Description");
        productWriteDto.setProductPrice(100.0);
        productWriteDto.setProductStock(50);

        productReadDto = new ProductReadDto();
        productReadDto.setProductId("1");
        productReadDto.setProductName("Product Name");
        productReadDto.setProductDescription("Product Description");
        productReadDto.setProductPrice(100.0);
        productReadDto.setProductStock(50);

        productReadDto2 = new ProductReadDto();
        productReadDto2.setProductId("1");
        productReadDto2.setProductName("Updated Product Name");
        productReadDto2.setProductDescription("Updated Product Description");
        productReadDto2.setProductPrice(150);
        productReadDto2.setProductStock(30);

    }

    @Test
    public void testSaveProduct() throws Exception {
        when(productServiceView.save(any(ProductWriteDto.class))).thenReturn("1");
        when(productServiceView.findById("1")).thenReturn(productReadDto);

        mockMvc.perform(post("/products")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Product Name\",\"productDescription\":\"Product Description\",\"productPrice\":100.0,\"productStock\":50}"))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.productId").value("1"))
                .andExpect(jsonPath("$.productName").value("Product Name"))
                .andExpect(jsonPath("$.productDescription").value("Product Description"))
                .andExpect(jsonPath("$.productPrice").value(100.0))
                .andExpect(jsonPath("$.productStock").value(50));
    }

    @Test
    public void testFindProductById() throws Exception {
        when(productServiceView.findById("1")).thenReturn(productReadDto);

        mockMvc.perform(get("/products/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("1"))
                .andExpect(jsonPath("$.productName").value("Product Name"))
                .andExpect(jsonPath("$.productDescription").value("Product Description"))
                .andExpect(jsonPath("$.productPrice").value(100.0))
                .andExpect(jsonPath("$.productStock").value(50));
    }

    @Test
    public void testFindAllProducts() throws Exception {
        List<ProductReadDto> products = new ArrayList<>();
        products.add(productReadDto);

        when(productServiceView.findAll()).thenReturn(products);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value("1"))
                .andExpect(jsonPath("$[0].productName").value("Product Name"))
                .andExpect(jsonPath("$[0].productDescription").value("Product Description"))
                .andExpect(jsonPath("$[0].productPrice").value(100.0))
                .andExpect(jsonPath("$[0].productStock").value(50));
    }

    @Test
    public void testFindByKeyword() throws Exception {
        List<ProductReadDto> products = new ArrayList<>();
        products.add(productReadDto);

        when(productServiceView.findByKeyword("Product")).thenReturn(products);

        mockMvc.perform(get("/products/keyword/Product"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].productId").value("1"))
                .andExpect(jsonPath("$[0].productName").value("Product Name"))
                .andExpect(jsonPath("$[0].productDescription").value("Product Description"))
                .andExpect(jsonPath("$[0].productPrice").value(100.0))
                .andExpect(jsonPath("$[0].productStock").value(50));
    }

    @Test
    public void testUpdateProduct() throws Exception {
        doNothing().when(productServiceView).update(any(ProductWriteDto.class), any(String.class));
        when(productServiceView.findById("1")).thenReturn(productReadDto2);

        mockMvc.perform(put("/products/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"productName\":\"Updated Product Name\",\"productDescription\":\"Updated Product Description\",\"productPrice\":150.0,\"productStock\":30}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.productId").value("1"))
                .andExpect(jsonPath("$.productName").value("Updated Product Name"))
                .andExpect(jsonPath("$.productDescription").value("Updated Product Description"))
                .andExpect(jsonPath("$.productPrice").value(150.0))
                .andExpect(jsonPath("$.productStock").value(30));
    }

    @Test
    public void testDeleteProductById() throws Exception {
        doNothing().when(productServiceView).deleteById("1");

        mockMvc.perform(delete("/products/1"))
                .andExpect(status().isOk())
                .andExpect(content().string("Product with id 1 has been deleted"));
    }

    @Test
    public void testDeleteAllProducts() throws Exception {
        doNothing().when(productServiceView).deleteAll();

        mockMvc.perform(delete("/products"))
                .andExpect(status().isOk())
                .andExpect(content().string("All products have been deleted"));
    }
}
