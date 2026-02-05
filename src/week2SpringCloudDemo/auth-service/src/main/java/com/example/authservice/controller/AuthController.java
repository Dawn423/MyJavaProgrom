package com.example.authservice.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.authservice.client.UserServiceClient;
import com.example.authservice.model.User;
import com.example.authservice.service.EmailService;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserServiceClient userServiceClient;

    @Autowired
    private EmailService emailService;

    @GetMapping("/user/{id}")
    public User getUserById(@PathVariable Long id) {
        return userServiceClient.getUserById(id);
    }

    @GetMapping("/user/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userServiceClient.getUserByUsername(username);
    }

    @GetMapping("/register")
    public String registerForm() {
        return "Please use POST request to register with JSON body containing username, password, and email";
    }

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        User createdUser = userServiceClient.createUser(user);
        // 发送注册成功邮件
        if (createdUser.getEmail() != null && !createdUser.getEmail().isEmpty()) {
            emailService.sendRegistrationSuccessEmail(createdUser.getEmail(), createdUser.getUsername());
        }
        return createdUser;
    }

    @DeleteMapping("/user/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceClient.deleteUser(id);
        return "User deleted successfully with ID: " + id;
    }

    @DeleteMapping("/user")
    public String deleteUserByUsername(@RequestParam("username") String username) {
        userServiceClient.deleteUserByUsername(username);
        return "User deleted successfully: " + username;
    }

    @GetMapping("/health")
    public String healthCheck() {
        return "Auth Service is running";
    }
}