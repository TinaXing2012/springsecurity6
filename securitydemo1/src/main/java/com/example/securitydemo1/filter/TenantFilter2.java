package com.example.securitydemo1.filter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class TenantFilter2 extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String header = request.getHeader("X-TENANT-ID");

        if(header != null && header.contains("admin")){
            filterChain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("DENIED");
        }
    }
}
