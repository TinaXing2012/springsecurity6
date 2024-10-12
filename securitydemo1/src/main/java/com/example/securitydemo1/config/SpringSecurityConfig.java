package com.example.securitydemo1.config;

import com.example.securitydemo1.filter.TenantFilter;
import com.example.securitydemo1.filter.TenantFilter2;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

//@Configuration
public class SpringSecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults()) //BasicAuthenticationFilter
                .authorizeHttpRequests(auth -> auth.anyRequest().authenticated()) //AuthorizationFilter
//                .addFilterAfter(new TenantFilter(), AuthorizationFilter.class)
                .addFilterBefore(new TenantFilter2(), AuthorizationFilter.class)
                .build();
    }

    @Bean
    public UserDetailsService userDetailsService() {
        InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
        UserDetails admin = User.builder()
                .username("admin")
                .password(passwordEncoder().encode("12345"))
                .roles("ADMIN")
                .build();
        UserDetails user = User.builder()
                .username("user")
                .password(passwordEncoder().encode("12345"))
                .roles("user")
                .build();
        manager.createUser(admin);
        manager.createUser(user);
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
