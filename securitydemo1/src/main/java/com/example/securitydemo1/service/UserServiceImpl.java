package com.example.securitydemo1.service;

import com.example.securitydemo1.model.User;
import com.example.securitydemo1.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;

    @PreAuthorize("hasAuthority('admin')")
    @Override
    public List<User> getAll() {
        return userRepository.findAll();
    }
}
