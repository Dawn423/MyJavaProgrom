package com.example.loginsystem.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.NavigableSet;
import java.util.TreeSet;
import java.util.concurrent.ConcurrentHashMap;

import com.example.loginsystem.model.User;

public class UserStorage {
    private static UserStorage instance;
    private final Map<String, User> usersByUsername;
    private final Map<Long, User> usersById;
    private long nextId;
    private final NavigableSet<Long> deletedIds; // 存储已删除的用户ID，用于重用
    
    private UserStorage() {
        usersByUsername = new ConcurrentHashMap<>();
        usersById = new ConcurrentHashMap<>();
        deletedIds = new TreeSet<>(); // 初始化已删除ID集合
        nextId = 1;
        
        // 添加内置账号，ID设为1（对应格式化后的000001）
        addUser(new User(1L, "Dawn", "666666"));
        nextId = 2; // 下一个ID从2开始
    }
    
    public static synchronized UserStorage getInstance() {
        if (instance == null) {
            instance = new UserStorage();
        }
        return instance;
    }
    
    public void addUser(User user) {
        usersByUsername.put(user.getUsername(), user);
        usersById.put(user.getId(), user);
    }
    
    public User getUserByUsername(String username) {
        return usersByUsername.get(username);
    }
    
    public User getUserById(Long id) {
        return usersById.get(id);
    }
    
    public List<User> getAllUsers() {
        return new ArrayList<>(usersByUsername.values());
    }
    
    public long getNextId() {
        if (!deletedIds.isEmpty()) {
            // 优先重用已删除的最小ID
            return deletedIds.pollFirst();
        }
        return nextId++;
    }
    
    // 通过用户名删除用户（不允许删除内置账号）
    public boolean deleteUser(String username) {
        // 不允许删除内置账号
        if ("Dawn".equals(username)) {
            return false;
        }
        
        User user = usersByUsername.remove(username);
        if (user != null) {
            Long id = user.getId();
            usersById.remove(id);
            deletedIds.add(id); // 将删除的ID添加到已删除集合中
            return true;
        }
        return false;
    }
    
    // 通过ID删除用户（不允许删除内置账号）
    public boolean deleteUserById(Long id) {
        User user = usersById.get(id);
        // 不允许删除内置账号或用户不存在
        if (user == null || "Dawn".equals(user.getUsername())) {
            return false;
        }
        
        usersById.remove(id);
        usersByUsername.remove(user.getUsername());
        deletedIds.add(id); // 将删除的ID添加到已删除集合中
        return true;
    }
}