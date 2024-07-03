package com.lautbiru.productRegistration.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.lautbiru.productRegistration.service.ProductServiceImpl;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
public class ProductServiceControllerTest {

    private MockMvc mockMvc;

    @Autowired
    ProductServiceController productServiceController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(productServiceController).build();
    }

    @Test
    public void testCreateProduct() throws Exception {
        dummyData();

        mockMvc.perform(get("/products/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)))
        .andExpect(jsonPath("$[1].name", is("Guava")));

        //TO empty static value "hotelReservationService" in HotelReservationController.
        // To avoid any unit test failure when executing all unit tests
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        productServiceImpl.deleteProduct("7");
        productServiceImpl.deleteProduct("8");
    }

    @Test
    void testDelete() throws Exception {
        dummyData();

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        assertEquals(2, productServiceImpl.getProduct().size());

        mockMvc.perform(get("/products/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));

        mockMvc.perform(delete("/products/7"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", containsString("Product is deleted successfully")));

        assertEquals(1, productServiceImpl.getProduct().size());

        //Update back to it's original value to avoid other unit test failure when executing the class
        productServiceImpl.deleteProduct("8");
    }

    @Test
    public void testGetProduct() throws Exception {
        dummyData();

        mockMvc.perform(get("/products/"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", hasSize(2)));

        //Update back to it's original value to avoid other unit test failure when executing the class
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        productServiceImpl.deleteProduct("7");
        productServiceImpl.deleteProduct("8");
    }

    @Test
    void testGetProductById() throws Exception {
        dummyData();

        mockMvc.perform(get("/products/8"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id", is("8")))
        .andExpect(jsonPath("$.name", is("Guava")));

        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        productServiceImpl.deleteProduct("7");
        productServiceImpl.deleteProduct("8");
    }

    @Test
    void testUpdateProduct() throws Exception {
        dummyData();

        mockMvc.perform(put("/products/7")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"name\":\"Tamarind\"}"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$", containsString("Product is updated successfully")));

        //Update back to it's original value to avoid other unit test failure when executing the class
        ProductServiceImpl productServiceImpl = new ProductServiceImpl();
        productServiceImpl.deleteProduct("7");
        productServiceImpl.deleteProduct("8");
    }

    public void dummyData() throws Exception {
        mockMvc.perform(post("/products/")
        .contentType(MediaType.APPLICATION_JSON)
        .content("{\"products\":[{\"id\":\"7\",\"name\":\"ciku\"},{\"id\":\"8\",\"name\":\"Guava\"}]}")
        ).andExpect(status().isOk())
        .andExpect(jsonPath("$", containsString("Product is created successfully")));
    }
}
