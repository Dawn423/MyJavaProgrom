package com.example.loginsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.loginsystem.model.User;
import com.example.loginsystem.storage.UserStorage;

@Controller
public class LoginController {
    private final UserStorage userStorage;
    
    public LoginController() {
        this.userStorage = UserStorage.getInstance();
    }

    // 根路径重定向到登录页
    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model) {
        // 从UserStorage中验证用户
        User user = userStorage.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            model.addAttribute("username", username);
            model.addAttribute("id", user.getFormattedId()); // 添加格式化的用户ID
            return "home";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    // 显示注册页面
    @GetMapping("/register")
    public String showRegisterForm() {
        return "register";
    }

    // 处理注册
    @PostMapping("/register")
    public String processRegister(@RequestParam("username") String username,
                                @RequestParam("password") String password,
                                Model model) {
        // 检查用户名是否已存在
        if (userStorage.getUserByUsername(username) != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        
        // 创建新用户并添加到UserStorage
        long newId = userStorage.getNextId();
        User newUser = new User(newId, username, password);
        userStorage.addUser(newUser);

        
        // 显示注册成功信息，包含格式化的ID
        model.addAttribute("success", "注册成功！用户名: " + username + ", 密码: " + password + ", 用户ID: " + newUser.getFormattedId() + "。请点击下方重新登录。");
        return "register";
    }

    // 显示登录后的首页
    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
