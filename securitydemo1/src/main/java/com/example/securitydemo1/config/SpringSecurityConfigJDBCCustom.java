package com.example.securitydemo1.config;

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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

import javax.sql.DataSource;

//@Configuration
public class SpringSecurityConfigJDBCCustom {

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
    public UserDetailsService userDetailsService(DataSource dataSource) {
        JdbcUserDetailsManager manager = new JdbcUserDetailsManager(dataSource);
        manager.setUsersByUsernameQuery("select email, password, enable from employee where email = ?");
        manager.setAuthoritiesByUsernameQuery("select username, authority from roles where username = ?");
        return manager;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
