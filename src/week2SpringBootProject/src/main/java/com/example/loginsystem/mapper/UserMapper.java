package com.example.loginsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.loginsystem.model.User;

@Mapper
public interface UserMapper {
    void insert(User user);
    User findByUsername(String username);
    User findByEmail(String email);
    User findById(Long id);
    List<User> findAll();
    void delete(User user);
    void update(User user);
    // 动态SQL查询
    List<User> findUsersByCondition(User user);
    // 用户与角色关联查询
    User findUserWithRole(Long id);
    List<User> findAllUsersWithRole();
}