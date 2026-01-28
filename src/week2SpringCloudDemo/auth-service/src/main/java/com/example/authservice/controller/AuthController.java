package com.example.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.authservice.client.UserServiceClient;
import com.example.authservice.model.User;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceClient userServiceClient;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServiceClient.getUserById(id);
    }

    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userServiceClient.getUserByUsername(username);
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userServiceClient.createUser(user);
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Auth Service is running";
    }
}