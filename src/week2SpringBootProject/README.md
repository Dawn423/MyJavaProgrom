# 登录系统项目介绍

## 1. 项目概述

本项目是一个基于Spring Boot框架开发的用户登录系统，提供用户注册、登录、查询和管理功能。系统采用分层架构设计，使用MySQL作为数据库存储用户信息，Thymeleaf作为模板引擎渲染前端页面，Spring Security提供安全认证功能。

### 1.1 项目特点

- 简单易用的用户注册和登录功能
- 内置默认管理员账号
- 支持用户信息查询和管理
- 智能ID管理：用户注销后，新注册用户使用最小的可用ID，保持ID连续排序
- 采用分层架构设计，代码结构清晰
- 使用Spring Boot 3.2.0版本，集成最新技术栈

## 2. 技术栈

| 技术/框架 | 版本 | 用途 |
|----------|------|------|
| Spring Boot | 3.2.0 | 应用框架 |
| Spring Web | - | Web开发 |
| Spring Security | - | 安全认证 |
| Spring Data JPA | - | 数据访问 |
| Thymeleaf | - | 模板引擎 |
| MySQL | - | 数据库 |
| Java | 17 | 开发语言 |

## 3. 项目结构

```
src/
├── main/
│   ├── java/
│   │   └── com/
│   │       └── example/
│   │           └── loginsystem/
│   │               ├── config/          # 配置类
│   │               │   └── SecurityConfig.java
│   │               ├── controller/       # 控制器
│   │               │   ├── LoginController.java
│   │               │   └── UserController.java
│   │               ├── model/            # 模型类
│   │               │   └── User.java
│   │               ├── repository/       # 数据访问层
│   │               │   └── UserRepository.java
│   │               ├── storage/          # 存储服务
│   │               │   └── UserStorage.java
│   │               └── LoginSystemApplication.java  # 应用入口
│   └── resources/
│       ├── templates/                    # 前端模板
│       │   ├── home.html
│       │   ├── login.html
│       │   └── register.html
│       └── application.properties        # 应用配置
└── pom.xml                               # Maven配置
```

## 4. 核心功能

### 4.1 用户注册

- 提供注册页面，用户可以输入用户名和密码进行注册
- 检查用户名是否已存在，确保用户名唯一性
- 智能ID分配：使用最小的可用ID，保持用户ID连续排序
- 注册成功后显示成功信息，包含用户ID

### 4.2 用户登录

- 提供登录页面，用户可以输入用户名和密码进行登录
- 支持"记住我"功能，勾选后关闭浏览器再次访问无需重新登录
- 验证用户名和密码是否正确
- 登录成功后跳转到首页，显示用户信息
- 登录失败时显示错误信息
- 支持退出登录功能，清除登录状态

### 4.3 用户查询

- 支持通过用户ID查询用户信息
- 支持查询所有用户列表
- 显示格式化的用户ID（6位数字）

### 4.4 用户管理

- 支持通过用户名或ID删除用户
- 只能注销当前登录的账号，无法注销其他用户的账号
- 注销成功后自动清除登录状态并跳转到登录页面
- 内置账号（Dawn）不可删除，确保系统安全

## 5. API接口

### 5.1 页面接口

| 接口路径 | 方法 | 功能描述 |
|---------|------|----------|
| / | GET | 重定向到登录页（已登录则跳转到首页） |
| /login | GET | 显示登录页面（已登录则跳转到首页） |
| /login | POST | 处理登录请求 |
| /register | GET | 显示注册页面 |
| /register | POST | 处理注册请求 |
| /home | GET | 显示登录后首页（未登录则跳转到登录页） |
| /logout | GET | 退出登录，清除登录状态 |

### 5.2 RESTful接口

| 接口路径 | 方法 | 功能描述 | 参数 | 响应 |
|---------|------|----------|------|------|
| /user/{id} | GET | 根据ID查询用户 | id: 用户ID | 用户信息或未找到提示 |
| /users | GET | 查询所有用户 | 无 | 用户列表 |
| /user | POST | 创建新用户 | username: 用户名<br>password: 密码 | 创建结果 |
| /user | DELETE | 删除用户 | username: 用户名（可选）<br>id: 用户ID（可选） | 删除结果（只能删除当前登录的账号，成功后自动跳转到登录页面） |

## 6. 数据库设计

### 6.1 数据库连接信息

```properties
spring.datasource.url=jdbc:mysql://192.168.133.176:3306/db_member_center_gldjc?useUnicode=true&characterEncoding=UTF-8&zeroDateTimeBehavior=convertToNull&serverTimezone=Asia/Shanghai&useSSL=false
spring.datasource.username=gcj_admin
spring.datasource.password=
```

### 6.2 用户表结构

| 字段名 | 数据类型 | 约束 | 描述 |
|-------|---------|------|------|
| id | BIGINT | PRIMARY KEY | 用户ID（由代码管理，保持连续排序） |
| username | VARCHAR(255) | UNIQUE, NOT NULL | 用户名 |
| password | VARCHAR(255) | NOT NULL | 密码 |

### 6.3 实体类

```java
@Entity
@Table(name = "users")
public class User {
    @Id
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;
    
    // 构造方法、getter和setter方法
    
    // 获取格式化的6位数字ID字符串
    public String getFormattedId() {
        return String.format("%06d", id);
    }
}
```

## 7. 核心组件

### 7.1 控制器

#### LoginController

处理用户登录和注册相关的请求，包括显示登录页面、处理登录请求、显示注册页面和处理注册请求。

#### UserController

处理用户管理相关的RESTful请求，包括根据ID查询用户、查询所有用户、创建新用户和删除用户。

### 7.2 存储服务

#### UserStorage

提供用户数据的存储和管理服务，包括初始化内置账号、添加用户、通过用户名或ID获取用户、获取所有用户和删除用户。

### 7.3 数据访问

#### UserRepository

继承自JpaRepository，提供基本的数据访问方法，如保存用户、根据用户名查找用户、根据ID查找用户和获取所有用户。

### 7.4 安全配置

#### SecurityConfig

配置Spring Security，禁用CSRF保护，允许所有请求访问，禁用默认的登录表单和HTTP基本认证。

## 8. 配置信息

### 8.1 服务器配置

```properties
server.port=8080
server.servlet.context-path=/
```

### 8.2 Thymeleaf配置

```properties
spring.thymeleaf.enabled=true
spring.thymeleaf.prefix=classpath:/templates/
spring.thymeleaf.suffix=.html
spring.thymeleaf.mode=HTML
spring.thymeleaf.encoding=UTF-8
spring.thymeleaf.servlet.content-type=text/html
```

### 8.3 开发工具配置

```properties
spring.devtools.restart.enabled=true
spring.devtools.restart.additional-paths=src/main/java
```

### 8.4 安全配置

```properties
spring.security.user.name=Dawn
spring.security.user.password=666666
```

### 8.5 JPA配置

```properties
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
spring.jpa.hibernate.naming.implicit-strategy=org.hibernate.boot.model.naming.ImplicitNamingStrategyJpaCompliantImpl
spring.jpa.hibernate.ddl-auto=none
```

## 9. 内置账号

系统启动时会自动初始化一个内置账号：

- 用户名：Dawn
- 密码：666666
- 用户ID：1

此账号为系统默认管理员账号，不可删除。

## 10. 运行指南

### 10.1 环境要求

- JDK 17或更高版本
- Maven 3.6.0或更高版本
- MySQL 8.0或更高版本

### 10.2 数据库准备

1. 创建数据库：`db_member_center_gldjc`
2. 创建用户表：

```sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY,
    username VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL
);
```

### 10.3 项目运行

1. 克隆项目到本地
2. 配置数据库连接信息（application.properties）
3. 执行Maven构建：`mvn clean install`
4. 运行应用：`mvn spring-boot:run`
5. 访问系统：`http://localhost:8080`

## 11. 页面说明

### 11.1 登录页面

- 路径：`/login`
- 功能：用户输入用户名和密码进行登录
- 记住我：支持勾选"记住我"选项，设置登录状态的保存时间
- 错误提示：用户名或密码错误时显示错误信息
- 自动跳转：已登录用户访问时会自动跳转到首页

### 11.2 注册页面

- 路径：`/register`
- 功能：用户输入用户名和密码进行注册
- 错误提示：用户名已存在时显示错误信息
- 成功提示：注册成功时显示成功信息，包含用户ID

### 11.3 首页

- 路径：`/home`
- 功能：登录成功后显示的页面，展示用户信息和系统功能
- 退出登录：提供退出登录按钮，点击后清除登录状态
- 自动跳转：未登录用户访问时会自动跳转到登录页

## 12. 代码示例

### 12.1 用户登录示例

```java
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
```

### 12.2 用户注册示例

```java
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
    model.addAttribute("success", "注册成功！用户名: " + username + ", 密码: " + password + ", 用户ID: " + newUser.getFormattedId());
    return "register";
}
```

### 12.3 用户查询示例

```java
@GetMapping("/user/{id}")
public String getUser(@PathVariable Long id) {
    // 从UserStorage中获取用户
    User user = userStorage.getUserById(id);
    if (user != null) {
        return "User ID: " + user.getFormattedId() + ", Name: " + user.getUsername();
    } else {
        return "User not found with ID: " + id;
    }
}
```

### 12.4 退出登录示例

```java
@GetMapping("/logout")
public String logout(HttpSession session) {
    // 清除Session中的用户信息
    session.invalidate();
    return "redirect:/login";
}
```

## 13. 总结

本项目是一个功能完整的用户登录系统，采用Spring Boot框架开发，具有以下特点：

- 结构清晰：采用分层架构设计，代码组织合理
- 功能完善：提供用户注册、登录、查询和管理功能
- 登录保存：支持"记住我"功能，可设置登录状态的保存时间
- 智能跳转：根据登录状态自动跳转到相应页面
- 智能ID管理：用户注销后，新注册用户使用最小的可用ID，保持ID连续排序
- 安全可靠：内置默认账号，支持用户权限管理
- 技术先进：使用Spring Boot 3.2.0版本，集成最新技术栈
- 易于扩展：模块化设计，便于功能扩展和维护

该系统可以作为小型应用的用户认证基础，也可以在此基础上扩展更多功能，如用户权限管理、密码重置、邮箱验证等。