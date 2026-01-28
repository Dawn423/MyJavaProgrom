# 微服务Demo项目

## 项目结构

```
week2SpringCloudDemo/
├── eureka-server/           # 服务注册中心
├── user-service/            # 用户服务
├── auth-service/            # 认证服务
└── requests.http           # HTTP请求测试文件
```

## 技术栈

- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Eureka 服务注册与发现
- Feign 声明式REST客户端
- Spring Data JPA
- MySQL 数据库
- Spring Security

## 运行步骤

### 1. 启动Eureka Server

```bash
cd eureka-server
mvn spring-boot:run
```

访问 Eureka 控制台：http://localhost:8761

### 2. 启动User Service

```bash
cd user-service
mvn spring-boot:run
```

### 3. 启动Auth Service

```bash
cd auth-service
mvn spring-boot:run
```

## 服务说明

### Eureka Server
- 端口：8761
- 功能：服务注册与发现

### User Service
- 端口：8082
- 功能：用户管理
- API：
  - GET /users - 获取所有用户
  - POST /users - 创建用户
  - GET /users/{id} - 根据ID获取用户
  - GET /users/username/{username} - 根据用户名获取用户

### Auth Service
- 端口：8084
- 功能：认证服务
- API：
  - GET /auth/user/{id} - 根据ID获取用户（调用User Service）
  - GET /auth/user/username/{username} - 根据用户名获取用户（调用User Service）
  - POST /auth/register - 注册用户（调用User Service）
  - GET /auth/health - 健康检查




## Spring Security配置

Auth Service 使用 Spring Security 进行安全控制，已配置以下路径允许匿名访问：
- /auth/health
- /auth/register
- /auth/user/**

## 服务间调用

Auth Service 通过 Feign 客户端调用 User Service 的接口，实现服务间通信。

## HTTP请求测试

项目根目录下的 `requests.http` 文件包含了所有服务的API测试请求：

1. 在 IntelliJ IDEA 中打开该文件
2. 每个请求左侧会出现 Run/▶ 按钮
3. 点击按钮即可发送请求并查看响应
4. 确保所有服务都已启动后再测试

### 测试说明
- **Health - auth-service**：测试认证服务健康状态
- **注册用户**：通过认证服务注册新用户
- **查询所有用户**：直接访问用户服务获取所有用户
- **按 id 查询用户**：直接访问用户服务根据ID获取用户
- **按 username 查询用户**：直接访问用户服务根据用户名获取用户
- **通过 auth-service 按 id 查询用户**：通过认证服务调用用户服务获取用户
- **通过 auth-service 按 username 查询用户**：通过认证服务调用用户服务获取用户