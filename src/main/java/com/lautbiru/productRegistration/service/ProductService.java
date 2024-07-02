package com.lautbiru.productRegistration.service;

import com.lautbiru.productRegistration.model.Product;

import java.util.Collection;

public interface ProductService {

    public abstract void createProduct(Product product);

    public abstract void updateProduct(String id, Product product);

    public abstract void deleteProduct(String id);

    public abstract Collection<Product> getProduct();

    public abstract Product getProduct(String id);

    public abstract boolean isProductExists(String id);

}
