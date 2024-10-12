package com.example.jwtdemo.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class AuthResponse {

    private String token;
    private String email;
}
