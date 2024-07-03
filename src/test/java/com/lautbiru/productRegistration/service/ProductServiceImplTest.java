package com.lautbiru.productRegistration.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.Test;

import com.lautbiru.productRegistration.model.Product;

public class ProductServiceImplTest {

    @Test
    void testCreateProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.createProduct(record());

        assertTrue(productService.isProductExists(record().getId()));
    }

    @Test
    void testDeleteProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();

        productService.createProduct(record());
        assertEquals(3, productService.getProduct().size());
        productService.deleteProduct(record().getId());

        assertEquals(2, productService.getProduct().size());
    }

    @Test
    void testGetProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();
        productService.createProduct(record());

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
        ProductServiceImpl productService = new ProductServiceImpl();

        productService.createProduct(record());

        assertEquals(productService.getProduct(record().getId()).getName(), "Pineapple");
    }

    @Test
    void testIsProductExists() {
        ProductServiceImpl productService = new ProductServiceImpl();

        productService.createProduct(record());

        assertTrue(productService.isProductExists(record().getId()));
    }

    @Test
    void testUpdateProduct() {
        ProductServiceImpl productService = new ProductServiceImpl();

        productService.createProduct(record());

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
