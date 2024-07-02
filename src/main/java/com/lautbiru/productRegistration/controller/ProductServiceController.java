package com.lautbiru.productRegistration.controller;

import com.lautbiru.productRegistration.exception.ProductNotFoundException;
import com.lautbiru.productRegistration.model.Product;
import com.lautbiru.productRegistration.service.ProductService;
import com.lautbiru.productRegistration.wrapper.ProductWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:8080")
@RequestMapping("/products")
public class ProductServiceController {

    @Autowired
    ProductService productService;

    private final static Logger logger = LoggerFactory.getLogger(ProductServiceController.class);

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Object> delete(@PathVariable("id") String id) {
        if(!productService.isProductExists(id))
            throw new ProductNotFoundException();
        productService.deleteProduct(id);
        return new ResponseEntity<>("Product is deleted successfully", HttpStatus.OK);
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id, @RequestBody Product product) {
        if(!productService.isProductExists(id))
            throw new ProductNotFoundException();
        productService.deleteProduct(id);
        product.setId(id);
        productService.updateProduct(id, product);
        return new ResponseEntity<>("Product is updated successfully", HttpStatus.OK);
    }

    @PostMapping(value = "/")
    public ResponseEntity<Object> createProduct(@RequestBody ProductWrapper products) {

        for (Product product : products.getProducts()) {
            productService.createProduct(product);
        }
        return new ResponseEntity<>("Product is created successfully", HttpStatus.OK);
    }

    @GetMapping(value = "/")
    public ResponseEntity<Object> getProduct() {
        return new ResponseEntity<>(productService.getProduct(), HttpStatus.OK);
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<Object> getProduct(@PathVariable("id") String id) {
        if (!productService.isProductExists(id))
            throw new ProductNotFoundException();
        else
            return new ResponseEntity<>(productService.getProduct(id), HttpStatus.OK);
    }
}
