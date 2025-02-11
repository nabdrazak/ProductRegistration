package com.lautbiru.productRegistration.service;

import com.lautbiru.productRegistration.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private static Map<String, Product> productRepo = new HashMap<>();

    @Override
    public void createProduct(Product product) {
        productRepo.put(product.getId(), product);
    }

    @Override
    public void updateProduct(String id, Product product) {
        productRepo.remove(id);
        product.setId(id);
        productRepo.put(product.getId(), product);
    }

    @Override
    public void deleteProduct(String id) {
        productRepo.remove(id);
    }

    @Override
    public Collection<Product> getProduct() {
        return productRepo.values();
    }

    @Override
    public Product getProduct(String id) {
        return productRepo.get(id);
    }

    @Override
    public boolean isProductExists(String id) {
        return productRepo.containsKey(id);
    }
}
