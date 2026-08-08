package com.softpro.Project2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softpro.Project2.entity.User;
import com.softpro.Project2.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;


    // Save user
    public User saveUser(User user) {
        return userRepository.save(user);
    }


    // Find user by email
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }


    // Check email already exists
    public boolean emailExists(String email) {
        return userRepository.existsByEmail(email);
    }

}