package com.lautbiru.productRegistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.lautbiru.productRegistration.model.Product;

public class ProductServiceImplTest {

    ProductServiceImpl productService;

    @BeforeEach
    void setUp() {
        productService = new ProductServiceImpl();
        productService.createProduct(record());
    }

    @Test
    void testCreateProduct() {
        assertTrue(productService.isProductExists(record().getId()));
    }

    @Test
    void testDeleteProduct() {
        assertEquals(1, productService.getProduct().size());
        productService.deleteProduct(record().getId());

        assertEquals(0, productService.getProduct().size());
    }

    @Test
    void testGetProduct() {
        List<Product> iterator = productService.getProduct().stream().toList();

        boolean idExists = false;
        for (Product product : iterator) {
            if(product.getName().equals("Pineapple")) {
                idExists = true;
                break;
            }
        }
        assertTrue(idExists);
    }

    @Test
    void testGetProductById() {
        assertEquals(productService.getProduct(record().getId()).getName(), "Pineapple");
    }

    @Test
    void testIsProductExists() {
        assertTrue(productService.isProductExists(record().getId()));
    }

    @Test
    void testUpdateProduct() {
        Product product = new Product();
        product.setId("3");
        product.setName("Mangostene");

        productService.updateProduct(product.getId(), product);

        assertEquals("Mangostene", productService.getProduct(product.getId()).getName());
    }

    public Product record() {
        Product product = new Product();
        product.setId("3");
        product.setName("Pineapple");

        return product;
    }
}
