package com.example.securitydemo1.filter;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.nio.file.AccessDeniedException;

public class TenantFilter extends HttpFilter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest servletRequest = (HttpServletRequest) request;

        String header = servletRequest.getHeader("X-TENANT-ID");

        if(header != null && header.contains("admin")){
            chain.doFilter(request, response);
        } else {
            throw new AccessDeniedException("DENIED");
        }

    }
}
