package com.example.loginsystem.model;

public class User {
    private Long id;
    private String username;
    private String password;
    
    public User() {
    }
    
    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }
    
    public Long getId() {
        return id;
    }
    
    public void setId(Long id) {
        this.id = id;
    }
    
    public String getUsername() {
        return username;
    }
    
    public void setUsername(String username) {
        this.username = username;
    }
    
    public String getPassword() {
        return password;
    }
    
    public void setPassword(String password) {
        this.password = password;
    }
    
    // 获取格式化的6位数字ID字符串
    public String getFormattedId() {
        return String.format("%06d", id);
    }
}