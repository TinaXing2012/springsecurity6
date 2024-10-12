package com.example.securitydemo1.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Product {

    private int id;
    private String name;
    private double price;
}
