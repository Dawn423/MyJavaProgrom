package com.example.loginsystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.authentication.logout.SimpleUrlLogoutSuccessHandler;

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
            .httpBasic(httpBasic -> httpBasic.disable())
            // 配置会话管理
            .sessionManagement(session -> session
                // 允许并发会话数
                .maximumSessions(1)
                // 会话过期后跳转到登录页
                .expiredUrl("/login")
            )
            // 配置退出登录
            .logout(logout -> logout
                .logoutUrl("/logout")
                .logoutSuccessHandler(logoutSuccessHandler())
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID")
            );

        return http.build();
    }
    
    @Bean
    public LogoutSuccessHandler logoutSuccessHandler() {
        SimpleUrlLogoutSuccessHandler handler = new SimpleUrlLogoutSuccessHandler();
        handler.setDefaultTargetUrl("/login");
        return handler;
    }
}
