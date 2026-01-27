package com.example.loginsystem.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.loginsystem.model.User;
import com.example.loginsystem.storage.UserStorage;


@RestController
public class UserController {
    private final UserStorage userStorage;
    
    public UserController() {
        this.userStorage = UserStorage.getInstance();
    }

    @GetMapping("/user/{id}")
    public String getUser(@PathVariable Long id) {
        // 从UserStorage中获取用户
        User user = userStorage.getUserById(id);
        if (user != null) {
            return "User ID: " + user.getFormattedId() + ", Name: " + user.getUsername();
        } else {
            return "User not found with ID: " + id;
        }
    }

    @GetMapping("/users")
    public String getUsers() {
        // 从UserStorage中获取所有用户
        List<User> users = userStorage.getAllUsers();
        String userList = users.stream()
                .map(user -> user.getUsername() + "(" + user.getFormattedId() + ")")
                .collect(Collectors.joining(", "));
        return "[" + userList + "]";
    }

    @PostMapping("/user")
    public String createUser(@RequestParam String username, @RequestParam String password) {
        // 检查用户名是否已存在
        if (userStorage.getUserByUsername(username) != null) {
            return "User already exists with username: " + username;
        }
        
        // 创建新用户并添加到UserStorage
        long newId = userStorage.getNextId();
        User newUser = new User(newId, username, password);
        userStorage.addUser(newUser);
        
        return "User created: " + username + ", Password: " + password;
    }
    
    // 注销账号（支持通过用户名或ID删除）
    @DeleteMapping("/user")
    public String deleteUser(@RequestParam(required = false) String username, @RequestParam(required = false) Long id) {
        boolean deleted = false;
        String message = "";
        
        if (id != null) {
            // 通过ID删除用户
            deleted = userStorage.deleteUserById(id);
            if (deleted) {
                message = "User deleted successfully with ID: " + String.format("%06d", id);
            } else {
                message = "Failed to delete user with ID: " + String.format("%06d", id) + " (either user not found or cannot delete built-in account)";
            }
        } else if (username != null) {
            // 通过用户名删除用户
            deleted = userStorage.deleteUser(username);
            if (deleted) {
                message = "User deleted successfully: " + username;
            } else {
                message = "Failed to delete user: " + username + " (either user not found or cannot delete built-in account)";
            }
        } else {
            message = "Failed to delete user: Please provide either username or user ID";
        }
        
        return message;
    }
}
