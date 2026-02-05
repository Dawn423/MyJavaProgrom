# MyJavaProgrom 项目介绍

## 项目概述

MyJavaProgrom 是一个综合性的Java学习项目，包含了从基础Java语法到Spring Boot和Spring Cloud微服务架构的完整学习路径。该项目旨在帮助开发者系统地学习Java相关技术栈，从基础概念到实际应用，逐步构建完整的知识体系。

## 项目结构

```
MyJavaProgrom/
├── MavenLearning/                # Maven学习项目
├── src/
│   ├── email-service/            # 邮件发送微服务
│   ├── ipp-test/                 # IPP资源字段映射测试项目
│   ├── week1/                    # 第一周：Java基础练习
│   │   ├── day1/                 # 第一天：基础语法练习
│   │   ├── day2/                 # 第二天：面向对象编程
│   │   ├── day3/                 # 第三天：数据结构与算法
│   │   └── helloworld/           # Hello World示例
│   ├── week2SpringBootProject/   # 第二周：Spring Boot登录系统
│   └── week2SpringCloudDemo/     # 第二周：Spring Cloud微服务项目
├── .gitignore
├── MavenLearning.iml
├── MyBatis学习指南.md            # MyBatis学习文档
├── MyJavaProgrom.iml
├── Spring Boot入门学习指南.md     # Spring Boot学习文档
├── SpringCloud介绍.md            # Spring Cloud学习文档
├── Spring项目创建步骤与核心概念.md # Spring核心概念文档
├── 数据库连接介绍.md             # 数据库连接学习文档
└── 项目详细介绍.md               # 项目详细说明文档
```

## 模块详解

### 1. MavenLearning

**功能**：Maven基础学习项目，演示Maven项目的基本结构和构建过程。

**结构**：
- `src/main/java/org/example/App.java` - 主应用类
- `src/test/java/org/example/AppTest.java` - 测试类
- `pom.xml` - Maven项目配置文件

### 2. week1 (Java基础练习)

**功能**：Java基础语法和核心概念练习，包含三天的学习内容。

#### 2.1 day1 (基础语法练习)
- `BasicCalculator.java` - 基础计算器实现
- `NumberSort.java` - 数字排序算法
- `SafeRefactor.java` - 代码重构练习

#### 2.2 day2 (面向对象编程)
- `AbstractEmployee.java` - 抽象员工类
- `CompanySalarySystem.java` - 公司薪资系统
- `Developer.java` - 开发者类
- `Payable.java` - 可支付接口

#### 2.3 day3 (数据结构与算法)
- `datadeduplication/` - 数据去重实现
  - `DataDeduplication.java` - 数据去重算法
  - `DeduplicationData.java` - 去重数据模型
- `KeyValuePairStorage.java` - 键值对存储实现

#### 2.4 helloworld
- `HelloWorld.java` - 经典Hello World示例

### 3. week2SpringBootProject (Spring Boot登录系统)

**功能**：基于Spring Boot的登录系统项目，实现了用户注册、登录、注销等功能。

**结构**：
- `src/main/java/com/example/loginsystem/`
  - `config/SecurityConfig.java` - 安全配置
  - `controller/` - 控制器
    - `LoginController.java` - 登录相关控制器
    - `UserController.java` - 用户管理控制器
  - `model/User.java` - 用户模型
  - `repository/UserRepository.java` - 用户仓库
  - `storage/UserStorage.java` - 用户存储实现
  - `LoginSystemApplication.java` - 应用主类
- `src/main/resources/`
  - `templates/` - HTML模板
    - `home.html` - 首页
    - `login.html` - 登录页
    - `register.html` - 注册页
  - `application.properties` - 应用配置文件

**技术栈**：
- Spring Boot 3.2.0
- Spring Security
- Thymeleaf
- 内存存储

### 4. week2SpringCloudDemo (Spring Cloud微服务项目)

**功能**：基于Spring Cloud的微服务架构项目，包含服务注册中心、用户服务和认证服务。

**结构**：
- `eureka-server/` - 服务注册中心
  - `src/main/java/com/example/eurekaserver/EurekaServerApplication.java` - 应用主类
  - `src/main/resources/application.properties` - 应用配置

- `user-service/` - 用户服务
  - `src/main/java/com/example/userservice/`
    - `controller/UserController.java` - 用户管理控制器
    - `model/User.java` - 用户模型（支持email字段）
    - `repository/UserRepository.java` - 用户仓库
    - `UserServiceApplication.java` - 应用主类
  - `src/main/resources/application.properties` - 应用配置

- `auth-service/` - 认证服务
  - `src/main/java/com/example/authservice/`
    - `client/UserServiceClient.java` - Feign客户端，调用用户服务
    - `config/SecurityConfig.java` - 安全配置
    - `controller/AuthController.java` - 认证控制器
    - `model/User.java` - 用户模型（支持email字段）
    - `service/EmailService.java` - 邮件服务
    - `AuthServiceApplication.java` - 应用主类
  - `src/main/resources/application.properties` - 应用配置（含邮件服务器配置）

**技术栈**：
- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Eureka 服务注册与发现
- Feign 声明式REST客户端
- Spring Data JPA
- MySQL 数据库
- Spring Security
- Spring Boot Starter Mail (邮件发送)

**新增功能**：
- 邮箱输入功能
- 邮件发送功能（用户注册成功后自动发送邮件通知）
- 用户删除功能

### 5. email-service (邮件发送微服务)

**功能**：基于Spring Boot的邮件发送微服务项目，集成了Spring Cloud Eureka客户端，可以自动注册到服务注册中心，实现服务发现和监控。该项目提供了邮件发送功能，支持发送普通邮件、注册模板邮件和通知模板邮件。

**结构**：
- `src/main/java/com/example/emailservice/`
  - `EmailServiceApplication.java` - 应用主类
  - `controller/EmailController.java` - 邮件控制器
  - `model/EmailRequest.java` - 邮件请求模型
  - `service/EmailService.java` - 邮件服务
- `src/main/resources/application.properties` - 应用配置
- `pom.xml` - Maven项目配置文件

**技术栈**：
- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Spring Cloud Netflix Eureka Client
- Spring Boot Starter Mail
- Spring Web

**API接口**：
| 路径 | 方法 | 功能 |
|------|------|------|
| `/email/health` | GET | 健康检查 |
| `/email/send` | POST | 发送普通邮件 |
| `/email/send-template/registration` | POST | 发送注册模板邮件 |
| `/email/send-template/notification` | POST | 发送通知模板邮件 |

### 6. ipp-test (IPP资源字段映射测试项目)

**功能**：用于管理和映射IPP（Industry Price Platform）资源字段的测试项目。该项目提供了完整的CRUD操作，支持按各种条件查询字段映射关系，并实现了特殊的id连续自增和删除后自动调整的功能。

**结构**：
- `src/main/java/com/example/ipptest/`
  - `IppTestApplication.java` - 应用启动类
  - `controller/IppResourceFieldMapperController.java` - 控制器
  - `domain/IppResourceFieldMapper.java` - 数据模型
  - `mapper/IppResourceFieldMapperMapper.java` - MyBatis映射接口
  - `service/IppResourceFieldMapperService.java` - 业务逻辑
- `src/main/resources/`
  - `mappers/IppResourceFieldMapperMapper.xml` - MyBatis XML映射文件
  - `application.properties` - 默认配置文件（H2数据库）
  - `application-mysql.properties` - MySQL配置文件
  - `schema.sql` - 数据库表结构
  - `data.sql` - 初始测试数据

**技术栈**：
- Spring Boot 3.2.7
- Java 21
- MyBatis 3.0.4
- MySQL / H2 (默认)
- Maven
- RESTful API

**API接口**：
| 路径 | 方法 | 功能 |
|------|------|------|
| `/api/ipp/resource-field-mapper/all` | GET | 查询全部未删除记录 |
| `/api/ipp/resource-field-mapper/{id}` | GET | 按id查询单条记录 |
| `/api/ipp/resource-field-mapper/field/{field}` | GET | 按field查询记录 |
| `/api/ipp/resource-field-mapper/by-original?originalStr=值` | GET | 按原始字符串查询 |
| `/api/ipp/resource-field-mapper/by-standard?standardStr=值` | GET | 按标准字符串查询 |
| `/api/ipp/resource-field-mapper` | POST | 新增一条记录 |
| `/api/ipp/resource-field-mapper` | PUT | 全量更新记录 |
| `/api/ipp/resource-field-mapper` | PATCH | 部分更新记录 |
| `/api/ipp/resource-field-mapper/{id}` | DELETE | 按id物理删除 |
| `/api/ipp/resource-field-mapper/by-condition?originalStr=值&standardStr=值&field=值` | DELETE | 按条件删除 |
- 用户删除功能

## 技术栈总览

| 技术类别 | 技术名称 | 版本 | 应用模块 |
|---------|---------|------|----------|
| 基础语言 | Java | 17+ | 所有模块 |
| 基础语言 | Java | 21 | ipp-test |
| 构建工具 | Maven | 3.8+ | 所有模块 |
| Web框架 | Spring Boot | 3.2.0 | week2SpringBootProject, week2SpringCloudDemo, email-service |
| Web框架 | Spring Boot | 3.2.7 | ipp-test |
| 微服务框架 | Spring Cloud | 2023.0.0 | week2SpringCloudDemo, email-service |
| 服务注册 | Eureka | - | week2SpringCloudDemo, email-service |
| REST客户端 | Feign | - | week2SpringCloudDemo |
| 安全框架 | Spring Security | - | week2SpringBootProject, week2SpringCloudDemo |
| ORM框架 | Spring Data JPA | - | week2SpringCloudDemo |
| ORM框架 | MyBatis | 3.0.4 | ipp-test |
| 模板引擎 | Thymeleaf | - | week2SpringBootProject |
| 数据库 | MySQL | 8.0+ | week2SpringCloudDemo, ipp-test |
| 数据库 | H2 | - | ipp-test (默认) |
| 邮件服务 | Spring Boot Starter Mail | - | week2SpringCloudDemo, email-service |

## 运行指南

### 1. 运行 week1 基础练习

```bash
# 进入相应目录
cd src/week1/day1

# 编译并运行
javac BasicCalculator.java
java BasicCalculator
```

### 2. 运行 week2SpringBootProject

```bash
# 进入项目目录
cd src/week2SpringBootProject

# 启动应用
mvn spring-boot:run

# 访问应用
# 登录页：http://localhost:8080/login
# 注册页：http://localhost:8080/register
# 首页：http://localhost:8080/home
```

### 3. 运行 week2SpringCloudDemo

#### 3.1 启动 Eureka Server

```bash
# 进入目录
cd src/week2SpringCloudDemo/eureka-server

# 启动服务
mvn spring-boot:run

# 访问 Eureka 控制台
# http://localhost:8761
```

#### 3.2 启动 User Service

```bash
# 进入目录
cd src/week2SpringCloudDemo/user-service

# 启动服务
mvn spring-boot:run

# 服务端口：8082
```

#### 3.3 启动 Auth Service

```bash
# 进入目录
cd src/week2SpringCloudDemo/auth-service

# 启动服务
mvn spring-boot:run

# 服务端口：8084
```

### 4. 运行 email-service

```bash
# 进入目录
cd src/email-service

# 启动服务
mvn spring-boot:run

# 服务端口：8085

# 验证服务注册
# 在Eureka控制台（http://localhost:8761）中确认EMAIL-SERVICE已注册
```

### 5. 运行 ipp-test

```bash
# 进入目录
cd src/ipp-test

# 启动服务
mvn spring-boot:run

# 服务端口：8080

# 访问应用
# 应用地址：http://localhost:8080
# H2数据库控制台：http://localhost:8080/h2-console

# 测试API
# 推荐使用项目中的Postman集合进行测试
```

## 服务API说明

### week2SpringBootProject API

| 路径 | 方法 | 功能 |
|------|------|------|
| `/` | GET | 根路径，重定向到登录页 |
| `/login` | GET | 显示登录页面 |
| `/login` | POST | 处理登录请求 |
| `/register` | GET | 显示注册页面 |
| `/register` | POST | 处理注册请求 |
| `/home` | GET | 显示首页 |
| `/logout` | GET | 退出登录 |
| `/user/{id}` | GET | 获取用户信息 |
| `/users` | GET | 获取所有用户 |
| `/user` | POST | 创建新用户 |
| `/user` | DELETE | 删除用户 |

### week2SpringCloudDemo API

#### User Service

| 路径 | 方法 | 功能 |
|------|------|------|
| `/users` | GET | 获取所有用户 |
| `/users` | POST | 创建用户（支持username、password、email参数） |
| `/users/{id}` | GET | 根据ID获取用户 |
| `/users/username/{username}` | GET | 根据用户名获取用户 |
| `/users/{id}` | DELETE | 根据ID删除用户 |
| `/users` | DELETE | 根据用户名删除用户（使用username查询参数） |

#### Auth Service

| 路径 | 方法 | 功能 |
|------|------|------|
| `/auth/user/{id}` | GET | 根据ID获取用户（调用User Service） |
| `/auth/user/username/{username}` | GET | 根据用户名获取用户（调用User Service） |
| `/auth/register` | POST | 注册用户（调用User Service，支持邮箱输入，注册成功后自动发送邮件） |
| `/auth/register` | GET | 查看注册说明 |
| `/auth/health` | GET | 健康检查 |
| `/auth/user/{id}` | DELETE | 根据ID删除用户（调用User Service） |
| `/auth/user` | DELETE | 根据用户名删除用户（调用User Service，使用username查询参数） |

### email-service API

| 路径 | 方法 | 功能 |
|------|------|------|
| `/email/health` | GET | 健康检查 |
| `/email/send` | POST | 发送普通邮件 |
| `/email/send-template/registration` | POST | 发送注册模板邮件 |
| `/email/send-template/notification` | POST | 发送通知模板邮件 |

### ipp-test API

| 路径 | 方法 | 功能 |
|------|------|------|
| `/api/ipp/resource-field-mapper/all` | GET | 查询全部未删除记录 |
| `/api/ipp/resource-field-mapper/{id}` | GET | 按id查询单条记录 |
| `/api/ipp/resource-field-mapper/field/{field}` | GET | 按field查询记录 |
| `/api/ipp/resource-field-mapper/by-original?originalStr=值` | GET | 按原始字符串查询 |
| `/api/ipp/resource-field-mapper/by-standard?standardStr=值` | GET | 按标准字符串查询 |
| `/api/ipp/resource-field-mapper` | POST | 新增一条记录 |
| `/api/ipp/resource-field-mapper` | PUT | 全量更新记录 |
| `/api/ipp/resource-field-mapper` | PATCH | 部分更新记录 |
| `/api/ipp/resource-field-mapper/{id}` | DELETE | 按id物理删除 |
| `/api/ipp/resource-field-mapper/by-condition?originalStr=值&standardStr=值&field=值` | DELETE | 按条件删除 |

## 学习路径建议

1. **基础阶段**：
   - 学习week1目录下的Java基础练习
   - 阅读相关学习指南文档

2. **Spring Boot阶段**：
   - 学习week2SpringBootProject项目
   - 阅读Spring Boot入门学习指南.md

3. **Spring Cloud阶段**：
   - 学习week2SpringCloudDemo项目
   - 阅读SpringCloud介绍.md

4. **微服务进阶阶段**：
   - 学习email-service项目，了解邮件服务的微服务实现
   - 掌握Spring Cloud Eureka客户端集成

5. **持久层框架阶段**：
   - 学习ipp-test项目，了解MyBatis的使用
   - 掌握MySQL和H2数据库的配置与使用
   - 学习RESTful API的设计与实现

6. **综合应用阶段**：
   - 尝试扩展项目功能
   - 集成MyBatis等其他技术
   - 实现微服务之间的调用与集成

## 注意事项

1. **数据库配置**：
   - week2SpringCloudDemo项目需要MySQL数据库支持
   - 数据库名称：user_db
   - 用户名：XXXXXX
   - 密码：XXXXXX

2. **邮件配置**：
   - week2SpringCloudDemo项目的auth-service需要配置QQ邮箱SMTP服务
   - 需要获取QQ邮箱授权码并更新application.properties文件

3. **端口占用**：
   - 确保相关端口未被占用：8761(Eureka), 8082(User Service), 8084(Auth Service), 8085(Email Service), 8080(ipp-test)

4. **服务依赖**：
   - 运行week2SpringCloudDemo和email-service时，需要先启动Eureka Server，再启动其他服务

5. **邮件配置**：
   - email-service项目需要配置QQ邮箱SMTP服务
   - 需要获取QQ邮箱授权码并更新application.properties文件

6. **ipp-test配置**：
   - 默认使用H2内存数据库，重启后数据会丢失
   - 如需使用MySQL数据库，请修改application.properties文件中的配置

## 总结

MyJavaProgrom项目是一个全面的Java学习平台，从基础语法到高级微服务架构，再到持久层框架应用，涵盖了Java开发的各个方面。通过学习和实践这个项目，开发者可以系统地掌握Java相关技术栈，为实际项目开发打下坚实的基础。

### 项目特色

1. **完整的学习路径**：从Java基础语法到Spring Boot、Spring Cloud微服务，再到MyBatis持久层框架，提供了完整的学习进阶路径。

2. **丰富的实践项目**：
   - 基础Java练习（week1）
   - Spring Boot登录系统（week2SpringBootProject）
   - Spring Cloud微服务架构（week2SpringCloudDemo）
   - 邮件发送微服务（email-service）
   - IPP资源字段映射系统（ipp-test）

3. **多样化的技术栈**：
   - 基础技术：Java 17/21、Maven
   - Web框架：Spring Boot 3.2.x
   - 微服务框架：Spring Cloud 2023.0.0
   - 持久层框架：Spring Data JPA、MyBatis
   - 数据库：MySQL、H2
   - 安全框架：Spring Security
   - 邮件服务：Spring Boot Starter Mail

4. **详细的文档和示例**：
   - 每个模块都有详细的README文档
   - 提供了完整的API接口说明
   - 包含了运行指南和配置说明

5. **模块化设计**：
   - 每个模块都是独立的，可单独学习和运行
   - 模块之间有清晰的依赖关系，便于理解系统架构

### 学习价值

通过学习本项目，你将能够：
- 掌握Java核心语法和面向对象编程思想
- 熟悉Spring Boot的开发流程和核心功能
- 理解Spring Cloud微服务架构的设计和实现
- 学习邮件服务的集成和使用
- 掌握MyBatis的配置和使用
- 了解RESTful API的设计和实现
- 学习不同数据库（MySQL、H2）的配置和使用

项目设计遵循了从简单到复杂、从基础到高级的学习路径，每个模块都有明确的学习目标和实践内容。同时，项目也提供了丰富的学习资源和文档，帮助开发者更好地理解和掌握相关技术。

希望这个项目能够成为你Java学习之旅中的有力助手！