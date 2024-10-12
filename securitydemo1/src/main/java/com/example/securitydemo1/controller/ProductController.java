package com.example.securitydemo1.controller;

import com.example.securitydemo1.model.Product;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public List<Product> getProducts() {
       return List.of(
               new Product(1, "HP", 99),
               new Product(2, "DELL", 88)
       );
    }

    @PostMapping
    public Product addProduct(@RequestBody Product product) {
        return product;
    }
}
