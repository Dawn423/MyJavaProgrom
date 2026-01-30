package com.example.loginsystem.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.loginsystem.model.User;
import com.example.loginsystem.service.EmailService;
import com.example.loginsystem.storage.UserStorage;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    private final UserStorage userStorage;
    private final EmailService emailService;

    @Autowired
    public LoginController(UserStorage userStorage, EmailService emailService) {
        this.userStorage = userStorage;
        this.emailService = emailService;
    }

    // 根路径重定向到登录页
    @GetMapping("/")
    public String index(HttpSession session) {
        // 检查Session中是否有用户信息
        if (session.getAttribute("username") != null) {
            return "redirect:/home";
        }
        return "redirect:/login";
    }

    // 显示登录页面
    @GetMapping("/login")
    public String showLoginForm(HttpSession session) {
        // 检查Session中是否有用户信息，如果有则直接跳转到首页
        if (session.getAttribute("username") != null) {
            return "redirect:/home";
        }
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                               @RequestParam("password") String password,
                               @RequestParam(value = "remember", required = false) String remember,
                               Model model,
                               HttpSession session) {
        // 从UserStorage中验证用户
        User user = userStorage.getUserByUsername(username);
        if (user != null && user.getPassword().equals(password)) {
            // 将用户信息存储到Session中
            session.setAttribute("username", username);
            session.setAttribute("id", user.getFormattedId());
            session.setAttribute("userId", user.getId());
            
            // 如果勾选了"记住我"，设置Session的最大不活动时间为7天
            if ("on".equals(remember)) {
                session.setMaxInactiveInterval(60 * 60 * 24 * 7); // 7天
            } else {
                // 否则设置为默认的30分钟
                session.setMaxInactiveInterval(60 * 30); // 30分钟
            }
            
            model.addAttribute("username", username);
            model.addAttribute("id", user.getFormattedId());
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
                                  @RequestParam("email") String email,
                                  Model model) {
        // 检查用户名是否已存在
        if (userStorage.getUserByUsername(username) != null) {
            model.addAttribute("error", "用户名已存在");
            return "register";
        }
        
        // 检查邮箱是否已存在
        if (userStorage.getUserByEmail(email) != null) {
            model.addAttribute("error", "邮箱已存在");
            return "register";
        }

        // 创建新用户并添加到UserStorage
        long newId = userStorage.getNextId();
        User newUser = new User(newId, username, password, email);
        userStorage.addUser(newUser);

        // 发送注册成功邮件
        try {
            emailService.sendRegistrationEmail(email, username, newUser.getFormattedId());
        } catch (Exception e) {
            // 邮件发送失败，记录错误但不影响注册流程
            System.err.println("Failed to send registration email: " + e.getMessage());
        }

        // 显示注册成功信息，包含格式化的ID
        model.addAttribute("success", "注册成功！用户名: " + username + ", 密码: " + password + ", 用户ID: " + newUser.getFormattedId() + ", 邮件已发送到: " + email);
        return "register";
    }

    // 显示登录后的首页
    @GetMapping("/home")
    public String showHomePage(HttpSession session, Model model) {
        // 检查Session中是否有用户信息，如果没有则重定向到登录页
        if (session.getAttribute("username") == null) {
            return "redirect:/login";
        }
        // 从Session中获取用户信息
        String username = (String) session.getAttribute("username");
        String id = (String) session.getAttribute("id");
        model.addAttribute("username", username);
        model.addAttribute("id", id);
        return "home";
    }
    
    // 退出登录
    @GetMapping("/logout")
    public String logout(HttpSession session) {
        // 清除Session中的用户信息
        session.invalidate();
        return "redirect:/login";
    }
}

