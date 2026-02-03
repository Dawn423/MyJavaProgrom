package com.example.loginsystem.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.loginsystem.mapper.UserMapper;
import com.example.loginsystem.model.User;

import jakarta.annotation.PostConstruct;

@Component
public class UserStorage {
    private final UserMapper userMapper;

    @Autowired
    public UserStorage(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    // 初始化内置账号
    @PostConstruct
    public void init() {
        // 检查是否已存在内置账号
        if (userMapper.findByUsername("Dawn") == null) {
            // 添加内置账号，设置角色为ADMIN（ID=1）
            User dawn = new User(1L, "Dawn", "666666", "dawn@example.com", 1L);
            userMapper.insert(dawn);
        }
    }

    // 添加用户
    public void addUser(User user) {
        userMapper.insert(user);
    }

    // 通过用户名获取用户
    public User getUserByUsername(String username) {
        return userMapper.findByUsername(username);
    }

    // 通过邮箱获取用户
    public User getUserByEmail(String email) {
        return userMapper.findByEmail(email);
    }

    // 通过ID获取用户
    public User getUserById(Long id) {
        return userMapper.findById(id);
    }

    // 获取所有用户
    public List<User> getAllUsers() {
        return userMapper.findAll();
    }

    // 获取所有用户（带角色信息）
    public List<User> getAllUsersWithRole() {
        return userMapper.findAllUsersWithRole();
    }

    // 获取用户（带角色信息）
    public User getUserWithRole(Long id) {
        return userMapper.findUserWithRole(id);
    }

    // 根据条件查询用户
    public List<User> getUsersByCondition(User user) {
        return userMapper.findUsersByCondition(user);
    }

    // 获取下一个ID（使用最小可用ID）
    public long getNextId() {
        // 获取所有用户的ID
        List<User> users = userMapper.findAll();
        // 创建一个集合存储已使用的ID
        java.util.Set<Long> usedIds = new java.util.HashSet<>();
        for (User user : users) {
            usedIds.add(user.getId());
        }
        
        // 从1开始查找最小的可用ID
        long nextId = 1;
        while (usedIds.contains(nextId)) {
            nextId++;
        }
        
        return nextId;
    }

    // 通过用户名删除用户（不允许删除内置账号）
    public boolean deleteUser(String username) {
        // 不允许删除内置账号
        if ("Dawn".equals(username)) {
            return false;
        }

        User user = userMapper.findByUsername(username);
        if (user != null) {
            userMapper.delete(user);
            return true;
        }
        return false;
    }

    // 通过ID删除用户（不允许删除内置账号）
    public boolean deleteUserById(Long id) {
        User user = userMapper.findById(id);
        // 不允许删除内置账号或用户不存在
        if (user == null || "Dawn".equals(user.getUsername())) {
            return false;
        }

        userMapper.delete(user);
        return true;
    }
}