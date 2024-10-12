package com.example.jwtdemo.controller;

import com.example.jwtdemo.model.AuthRequest;
import com.example.jwtdemo.model.AuthResponse;
import com.example.jwtdemo.model.User;
import com.example.jwtdemo.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RequiredArgsConstructor
@RestController
@RequestMapping("/auth")
public class JwtAuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest authRequest){
        try {
            Authentication authentication = new UsernamePasswordAuthenticationToken(authRequest.getEmail(), authRequest.getPassword());
            Authentication authented = authenticationManager.authenticate(authentication);
            User u = (User) authented.getPrincipal();
            String token = jwtTokenUtil.generateToken(u);
            return ResponseEntity.ok(new AuthResponse(token, u.getEmail()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
        }
    }
}
