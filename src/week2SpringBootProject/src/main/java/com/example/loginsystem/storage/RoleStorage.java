package com.example.loginsystem.storage;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.loginsystem.mapper.RoleMapper;
import com.example.loginsystem.model.Role;

import jakarta.annotation.PostConstruct;

@Component
public class RoleStorage {
    private final RoleMapper roleMapper;

    @Autowired
    public RoleStorage(RoleMapper roleMapper) {
        this.roleMapper = roleMapper;
    }

    // 初始化默认角色
    @PostConstruct
    public void init() {
        // 检查是否已存在默认角色
        if (roleMapper.findByName("ADMIN") == null) {
            // 添加管理员角色
            Role admin = new Role(1L, "ADMIN", "管理员角色，拥有所有权限");
            roleMapper.insert(admin);
        }

        if (roleMapper.findByName("USER") == null) {
            // 添加普通用户角色
            Role user = new Role(2L, "USER", "普通用户角色，拥有基本权限");
            roleMapper.insert(user);
        }
    }

    // 添加角色
    public void addRole(Role role) {
        roleMapper.insert(role);
    }

    // 通过ID获取角色
    public Role getRoleById(Long id) {
        return roleMapper.findById(id);
    }

    // 通过名称获取角色
    public Role getRoleByName(String name) {
        return roleMapper.findByName(name);
    }

    // 获取所有角色
    public List<Role> getAllRoles() {
        return roleMapper.findAll();
    }

    // 更新角色
    public void updateRole(Role role) {
        roleMapper.update(role);
    }

    // 删除角色
    public boolean deleteRole(Long id) {
        // 不允许删除默认角色
        Role role = roleMapper.findById(id);
        if (role != null && ("ADMIN".equals(role.getName()) || "USER".equals(role.getName()))) {
            return false;
        }

        roleMapper.delete(id);
        return true;
    }
}