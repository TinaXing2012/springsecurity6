package com.example.jwtdemo.model;

import lombok.Data;

@Data
public class AuthRequest {
    private String email;
    private String password;
}