# Email Service 项目介绍

## 项目概述

Email Service 是一个基于 Spring Boot 的邮件发送微服务项目，集成了 Spring Cloud Eureka 客户端，可以自动注册到服务注册中心，实现服务发现和监控。该项目提供了邮件发送功能，支持发送普通邮件、注册模板邮件和通知模板邮件。

## 技术栈

- Spring Boot 3.2.0
- Spring Cloud 2023.0.0
- Spring Cloud Netflix Eureka Client
- Spring Boot Starter Mail
- Spring Web

## 项目结构

```
email-service/
├── src/
│   └── main/
│       ├── java/
│       │   └── com/
│       │       └── example/
│       │           └── emailservice/
│       │               ├── EmailServiceApplication.java   # 应用主类
│       │               ├── controller/
│       │               │   └── EmailController.java       # 邮件控制器
│       │               ├── model/
│       │               │   └── EmailRequest.java           # 邮件请求模型
│       │               └── service/
│       │                   └── EmailService.java            # 邮件服务
│       └── resources/
│           └── application.properties                      # 应用配置
└── pom.xml                                                # Maven配置
```

## 功能特性

### 1. 邮件发送功能
- 支持发送普通邮件
- 支持发送注册模板邮件
- 支持发送通知模板邮件
- 邮件发送失败时提供详细错误信息

### 2. Eureka集成
- 自动注册到Eureka服务注册中心
- 支持服务发现和监控
- 服务状态实时更新

### 3. API接口
| 路径 | 方法 | 功能 |
|------|------|------|
| `/email/health` | GET | 健康检查 |
| `/email/send` | POST | 发送普通邮件 |
| `/email/send-template/registration` | POST | 发送注册模板邮件 |
| `/email/send-template/notification` | POST | 发送通知模板邮件 |

## 配置说明

### 1. 服务配置
```properties
# 服务端口
server.port=8085

# 应用名称（Eureka注册名称）
spring.application.name=email-service

# Eureka服务地址
eureka.client.service-url.defaultZone=http://localhost:8761/eureka/
```

### 2. 邮件配置
```properties
# QQ邮箱SMTP配置
spring.mail.host=smtp.qq.com
spring.mail.port=587
spring.mail.username=your-qq-email@qq.com
spring.mail.password=your-qq-authorization-code
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

### 3. 获取QQ邮箱授权码
1. 登录QQ邮箱
2. 点击"设置" -> "账户"
3. 找到"POP3/IMAP/SMTP/Exchange/CardDAV/CalDAV服务"
4. 开启"SMTP服务"
5. 点击"生成授权码"
6. 使用生成的授权码作为`spring.mail.password`的值

## 运行步骤

### 1. 更新邮件配置
- 打开 `src/main/resources/application.properties`
- 将 `spring.mail.username` 改为你的QQ邮箱
- 将 `spring.mail.password` 改为你的QQ邮箱授权码

### 2. 启动Eureka Server
Email Service 需要注册到Eureka Server，所以需要先启动Cloud项目中的Eureka Server：

```bash
# 进入Cloud项目的Eureka Server目录
cd ../src/week2SpringCloudDemo/eureka-server

# 启动服务
mvn spring-boot:run

# 访问Eureka控制台
# http://localhost:8761
```

### 3. 启动Email Service

```bash
# 进入Email Service目录
cd email-service

# 启动服务
mvn spring-boot:run

# 服务端口：8085
```

### 4. 验证服务注册
在Eureka控制台（http://localhost:8761）中，确认 `EMAIL-SERVICE` 已经成功注册。

## API使用示例

### 1. 发送普通邮件
**请求**：
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"to":"recipient@example.com","subject":"测试邮件","text":"这是一封测试邮件","from":"your-qq-email@qq.com"}' \
http://localhost:8085/email/send
```

**响应**：
```json
"邮件发送成功"
```

### 2. 发送注册模板邮件
**请求**：
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"to":"recipient@example.com","from":"your-qq-email@qq.com"}' \
http://localhost:8085/email/send-template/registration
```

**响应**：
```json
"注册邮件发送成功"
```

### 3. 发送通知模板邮件
**请求**：
```bash
curl -X POST -H "Content-Type: application/json" \
-d '{"to":"recipient@example.com","subject":"系统通知","text":"您有一条新的系统通知","from":"your-qq-email@qq.com"}' \
http://localhost:8085/email/send-template/notification
```

**响应**：
```json
"通知邮件发送成功"
```

### 4. 健康检查
**请求**：
```bash
curl http://localhost:8085/email/health
```

**响应**：
```json
"Email Service is running"
```

## 与其他服务集成

### 1. 通过Eureka服务发现
其他服务可以通过Eureka服务发现来调用Email Service：

```java
@FeignClient(name = "email-service")
public interface EmailServiceClient {
    @PostMapping("/email/send-template/registration")
    ResponseEntity<?> sendRegistrationEmail(@RequestBody EmailRequest emailRequest);
}
```

### 2. 直接HTTP调用
也可以通过直接HTTP调用来使用Email Service：

```java
RestTemplate restTemplate = new RestTemplate();
EmailRequest emailRequest = new EmailRequest();
emailRequest.setTo("user@example.com");
emailRequest.setFrom("service@example.com");

ResponseEntity<String> response = restTemplate.postForEntity(
    "http://localhost:8085/email/send-template/registration",
    emailRequest,
    String.class
);
```

## 注意事项

1. **邮件配置**：
   - 必须使用正确的QQ邮箱和授权码
   - 确保QQ邮箱已开启SMTP服务

2. **Eureka依赖**：
   - 必须先启动Eureka Server
   - 确保网络连接正常，能够访问Eureka服务

3. **服务端口**：
   - 默认端口为8085
   - 如需修改，更新`server.port`配置

4. **错误处理**：
   - 邮件发送失败时会抛出异常
   - 建议在调用时添加异常处理

## 故障排除

### 1. 服务注册失败
- 检查Eureka Server是否运行
- 检查网络连接是否正常
- 检查`eureka.client.service-url.defaultZone`配置是否正确

### 2. 邮件发送失败
- 检查QQ邮箱和授权码是否正确
- 检查SMTP服务是否开启
- 检查网络连接是否正常
- 检查收件人邮箱格式是否正确

### 3. API调用失败
- 检查服务是否运行
- 检查端口是否正确
- 检查请求格式是否符合要求
- 查看服务日志获取详细错误信息

## 总结

Email Service 是一个功能完整、配置简单的邮件发送微服务，通过集成Eureka实现了服务的注册和发现，方便与其他微服务进行集成。该服务可以作为系统中的邮件发送中心，为用户提供注册通知、系统通知等邮件服务。