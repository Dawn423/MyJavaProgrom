# IPP Resource Field Mapper Test Project

## 项目简介

IPP Resource Field Mapper 是一个用于管理和映射 IPP（Industry Price Platform）资源字段的测试项目。该项目提供了完整的 CRUD 操作，支持按各种条件查询字段映射关系，并实现了特殊的 id 连续自增和删除后自动调整的功能。

## 技术栈

- **后端框架**: Spring Boot 3.2.7
- **Java 版本**: 21
- **持久层框架**: MyBatis 3.0.4
- **数据库**: MySQL / H2 (默认)
- **项目管理工具**: Maven
- **API 风格**: RESTful

## 项目结构

```
src/
├── main/
│   ├── java/com/example/ipptest/
│   │   ├── controller/         # 控制器层，处理 HTTP 请求
│   │   │   └── IppResourceFieldMapperController.java
│   │   ├── domain/              # 数据模型层
│   │   │   └── IppResourceFieldMapper.java
│   │   ├── mapper/              # MyBatis 映射接口
│   │   │   └── IppResourceFieldMapperMapper.java
│   │   ├── service/             # 业务逻辑层
│   │   │   └── IppResourceFieldMapperService.java
│   │   └── IppTestApplication.java  # 应用启动类
│   └── resources/
│       ├── mappers/             # MyBatis XML 映射文件
│       │   └── IppResourceFieldMapperMapper.xml
│       ├── application.properties  # 默认配置文件
│       ├── application-mysql.properties  # MySQL 配置文件
│       ├── schema.sql           # 数据库表结构
│       ├── data.sql             # 初始测试数据
│       └── data-unit-mapper-sample.sql  # 示例数据
└── test/                        # 测试代码目录
```

## 核心功能

### 1. 数据模型

**IppResourceFieldMapper** 实体包含以下字段：

| 字段名 | 类型 | 描述 |
|-------|------|------|
| id | Long | 主键，自动连续自增 |
| originalStr | String | 原始字符串（如：公分、平方米） |
| standardStr | String | 标准字符串（如：cm、m2） |
| multiple | Integer | 倍数，默认 1 |
| field | Integer | 字段类型，如 1 或 2 |
| isDelete | Integer | 删除标记，0 未删除，1 已删除 |
| userName | String | 操作用户名 |
| createAt | Date | 创建时间 |

### 2. API 接口

#### 查询接口
- **GET /api/ipp/resource-field-mapper/all** - 查询全部未删除记录
- **GET /api/ipp/resource-field-mapper/{id}** - 按 id 查询单条记录
- **GET /api/ipp/resource-field-mapper/field/{field}** - 按 field 查询记录
- **GET /api/ipp/resource-field-mapper/by-original?originalStr=值** - 按原始字符串查询
- **GET /api/ipp/resource-field-mapper/by-standard?standardStr=值** - 按标准字符串查询

#### 新增接口
- **POST /api/ipp/resource-field-mapper** - 新增一条记录（自动生成连续 id）

#### 更新接口
- **PUT /api/ipp/resource-field-mapper** - 全量更新记录
- **PATCH /api/ipp/resource-field-mapper** - 部分更新记录

#### 删除接口
- **DELETE /api/ipp/resource-field-mapper/{id}** - 按 id 物理删除（自动调整后续记录 id）
- **DELETE /api/ipp/resource-field-mapper/by-condition?originalStr=值&standardStr=值&field=值** - 按条件删除

### 3. 特殊功能

1. **连续自增 id**: 新增记录时，id 会自动设为当前最大 id + 1，确保 id 连续
2. **删除后自动调整**: 删除记录后，会自动将后续记录的 id 减 1，并重置自增序列，保持 id 连续
3. **按条件删除**: 支持通过 originalStr + standardStr + field 组合条件删除记录

## 数据库配置

### 默认配置

项目默认使用 H2 内存数据库，配置如下：

```properties
# application.properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.h2.console.enabled=true
```

### MySQL 配置

如需使用 MySQL 数据库，请修改 `application.properties` 文件，取消注释以下配置：

```properties
# 使用 MySQL 数据库
spring.profiles.active=mysql
```

并确保 `application-mysql.properties` 文件中的配置正确：

```properties
# application-mysql.properties
spring.datasource.url=jdbc:mysql://localhost:3306/your_database_name
spring.datasource.username=your_username
spring.datasource.password=your_password
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver
```

## 快速开始

### 1. 克隆项目

```bash
git clone <repository-url>
cd ipp-test
```

### 2. 构建项目

```bash
mvn clean install
```

### 3. 运行项目

```bash
mvn spring-boot:run
```

或使用 IDE 运行 `IppTestApplication.java` 类。

### 4. 访问项目

- **应用地址**: http://localhost:8080
- **H2 数据库控制台**: http://localhost:8080/h2-console (使用默认配置登录)

## 测试指南

### Postman 测试

1. 导入项目中的 `IPP-Resource-Field-Mapper.postman_collection.json` 文件到 Postman
2. 按照 `Postman测试流程.md` 中的步骤进行测试

### 推荐测试顺序

1. **查询全部** - GET `/api/ipp/resource-field-mapper/all`，确认初始数据
2. **新增** - POST 一条记录（如 originalStr=公顷, standardStr=ha, field=1）
3. **按 original_str 查询** - GET `/api/ipp/resource-field-mapper/by-original?originalStr=公顷`，确认新增
4. **按 standard_str 查询** - GET `/api/ipp/resource-field-mapper/by-standard?standardStr=ha`
5. **按 field 查询** - GET `/api/ipp/resource-field-mapper/field/1`
6. **更新** - 从响应中取 `id`，用 PUT 或 PATCH 修改记录，再查询验证
7. **删除** - DELETE `/api/ipp/resource-field-mapper/{id}`，再查询确认已删除

## 配置说明

### 两个配置文件说明

- **application.properties**: 默认配置文件，使用 H2 内存数据库
- **application-mysql.properties**: MySQL 数据库配置文件

详细配置说明请参考 `src/main/resources/两个配置文件说明.md` 文件。

## 注意事项

1. **id 连续自增**：项目实现了特殊的 id 连续自增逻辑，删除记录后会自动调整后续记录的 id，这可能会影响并发操作和外键引用，仅适合测试或明确无外键引用的场景

2. **数据库选择**：默认使用 H2 内存数据库，重启后数据会丢失；如需持久化数据，请使用 MySQL 配置

3. **API 接口**：所有接口均遵循 RESTful 风格，返回 JSON 格式数据

4. **错误处理**：项目未实现详细的错误处理机制，生产环境中建议添加异常处理和日志记录

## 依赖管理

项目主要依赖如下：

- spring-boot-starter-web: Web 应用支持
- mybatis-spring-boot-starter: MyBatis 集成
- mysql-connector-j: MySQL 驱动
- h2: H2 内存数据库
- lombok: 代码简化工具
- spring-boot-starter-test: 测试支持

## 版本历史

| 版本 | 日期 | 描述 |
|------|------|------|
| 0.0.1-SNAPSHOT | 2026-02-05 | 初始版本，实现基本 CRUD 操作和特殊 id 管理功能 |

## 联系方式

如有问题或建议，请联系项目维护者。

---

**© 2026 IPP Resource Field Mapper Project. All rights reserved.**