package com.example.loginsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            // 禁用 CSRF 保护（仅用于开发环境）
            .csrf(csrf -> csrf.disable())
            // 允许所有请求访问
            .authorizeRequests(authorize -> authorize
                .anyRequest().permitAll()
            )
            // 禁用默认的登录表单
            .formLogin(form -> form.disable())
            // 禁用默认的 HTTP 基本认证
            .httpBasic(httpBasic -> httpBasic.disable());

        return http.build();
    }
}
