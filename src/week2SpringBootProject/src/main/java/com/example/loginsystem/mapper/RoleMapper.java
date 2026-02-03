package com.example.loginsystem.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.loginsystem.model.Role;

@Mapper
public interface RoleMapper {
    void insert(Role role);
    Role findById(Long id);
    Role findByName(String name);
    List<Role> findAll();
    void update(Role role);
    void delete(Long id);
}