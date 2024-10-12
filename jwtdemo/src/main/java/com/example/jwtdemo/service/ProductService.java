package com.example.jwtdemo.service;

import com.example.jwtdemo.model.Product;

import java.util.List;

public interface ProductService {

    Product createProduct(Product product);
    List<Product> getAllProducts();
    void deleteProduct(long id);

}
