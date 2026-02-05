package com.example.userservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.userservice.model.User;
import com.example.userservice.repository.UserRepository;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @PostMapping
    public User createUser(@RequestBody User user) {
        // 计算连续的ID
        Long nextId = calculateNextId();
        user.setId(nextId);
        return userRepository.save(user);
    }

    private Long calculateNextId() {
        List<User> users = userRepository.findAll();
        if (users.isEmpty()) {
            return 1L;
        }
        
        // 提取所有ID并排序
        List<Long> ids = users.stream()
                .map(User::getId)
                .sorted()
                .collect(java.util.stream.Collectors.toList());
        
        // 检查是否有从1开始的空缺
        for (int i = 0; i < ids.size(); i++) {
            if (ids.get(i) != (i + 1L)) {
                return (i + 1L);
            }
        }
        
        // 没有空缺，使用最大ID+1
        return ids.get(ids.size() - 1) + 1L;
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @GetMapping("/username/{username}")
    public User getUserByUsername(@PathVariable String username) {
        return userRepository.findByUsername(username);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userRepository.deleteById(id);
    }

    @DeleteMapping
    public void deleteUserByUsername(@RequestParam("username") String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            userRepository.delete(user);
        }
    }
}