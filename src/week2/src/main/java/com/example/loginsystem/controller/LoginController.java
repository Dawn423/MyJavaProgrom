package com.example.loginsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    @GetMapping("/")
    public String index() {
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String showLoginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String processLogin(@RequestParam("username") String username,
                              @RequestParam("password") String password,
                              Model model) {
        // 简单的硬编码验证，实际项目中应该从数据库查询
        if ("Dawn".equals(username) && "666666".equals(password)) {
            model.addAttribute("username", username);
            return "home";
        } else {
            model.addAttribute("error", "用户名或密码错误");
            return "login";
        }
    }

    @GetMapping("/home")
    public String showHomePage() {
        return "home";
    }
}
