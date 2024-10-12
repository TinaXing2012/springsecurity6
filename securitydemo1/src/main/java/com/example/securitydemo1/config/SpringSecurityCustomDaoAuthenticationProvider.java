package com.example.securitydemo1.config;

import com.example.securitydemo1.filter.TenantFilter2;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@RequiredArgsConstructor
@Configuration
@EnableMethodSecurity
public class SpringSecurityCustomDaoAuthenticationProvider {

    private final UserDetailsService userDetailsService;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .httpBasic(Customizer.withDefaults()) //BasicAuthenticationFilter
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/products/**").hasAuthority("admin")
//                        .requestMatchers("/users/**").hasAnyAuthority("admin", "user")
                        .anyRequest().authenticated()) //AuthorizationFilter
//                .addFilterAfter(new TenantFilter(), AuthorizationFilter.class)
//                .addFilterBefore(new TenantFilter2(), AuthorizationFilter.class)
                .csrf(csrf -> csrf.disable())
                .build();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setPasswordEncoder(passwordEncoder());
        authProvider.setUserDetailsService(userDetailsService);
        return authProvider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
