# StudentServices - 学生信息管理系统

基于Spring Boot IoC和AOP技术实现的学生信息管理系统 (Student Information Management System using Spring Boot IoC and AOP)

## 项目简介 (Project Overview)

本项目使用Spring Boot框架的IoC（控制反转）和AOP（面向切面编程）技术，实现了学生信息的增删改查操作。系统能够对所有操作进行日志记录，并对无效访问提供自定义错误提示。

This project uses Spring Boot Framework's IoC (Inversion of Control) and AOP (Aspect-Oriented Programming) to implement student information CRUD operations. The system logs all operations and provides custom error messages for invalid access.

## 功能特性 (Features)

- ✅ **学生实体类** (Student Entity): 包含学号(sid)、姓名(name)、电话(tele)
- ✅ **CRUD操作** (CRUD Operations):
  - addStudent - 添加学生
  - deleteStudent - 删除学生
  - modifyStudent - 修改学生信息
  - queryStudent - 查询单个学生
  - queryAllStudents - 查询所有学生
- ✅ **IoC依赖注入** (IoC Dependency Injection): 使用Spring容器管理Service层
- ✅ **AOP日志记录** (AOP Logging): 自动记录所有操作的调用日志
- ✅ **自定义异常处理** (Custom Exception Handling): 针对无效操作的友好错误提示
- ✅ **完整测试覆盖** (Complete Test Coverage): JUnit测试验证所有功能

## 技术栈 (Tech Stack)

- Java 8
- Spring Boot 2.7.18
- Spring Boot AOP Starter
- JUnit 5 (Jupiter)
- Maven

## 项目结构 (Project Structure)

```
StudentServices/
├── src/
│   ├── main/
│   │   ├── java/com/student/
│   │   │   ├── model/
│   │   │   │   └── Student.java              # 学生实体类
│   │   │   ├── service/
│   │   │   │   ├── StudentService.java       # Service接口
│   │   │   │   └── StudentServiceImpl.java   # Service实现
│   │   │   ├── aspect/
│   │   │   │   └── LoggingAspect.java        # AOP日志切面
│   │   │   ├── exception/
│   │   │   │   ├── StudentNotFoundException.java
│   │   │   │   ├── InvalidStudentException.java
│   │   │   │   └── InvalidOperationException.java
│   │   │   └── Main.java                     # 主程序入口
│   │   └── resources/
│   │       └── applicationContext.xml        # Spring配置文件
│   └── test/
│       └── java/com/student/
│           └── StudentServiceTest.java       # 测试类
├── pom.xml                                   # Maven配置
└── README.md
```

## 快速开始 (Quick Start)

### 编译项目 (Build Project)

```bash
mvn clean compile
```

### 运行测试 (Run Tests)

```bash
mvn test
```

### 运行主程序 (Run Main Application)

```bash
mvn spring-boot:run
```

## AOP日志示例 (AOP Logging Example)

系统会自动记录所有操作的详细日志：

```
=== AOP日志 (AOP Log) ===
时间 (Time): 2025-11-18 05:55:34
操作 (Operation): addStudent
参数 (Parameters): [Student{sid='001', name='张三', tele='13800138000'}]
成功添加学生: Student{sid='001', name='张三', tele='13800138000'} (Student added successfully)
操作 (Operation) 'addStudent' 执行成功 (executed successfully)
执行时间 (Execution time): 1ms
======================
```

## 错误处理示例 (Error Handling Example)

系统对无效操作提供友好的错误提示：

```
错误信息 (Error message): 未找到学生ID: 999 (Student not found)
错误信息 (Error message): 学生ID已存在: 001 (Student ID already exists)
错误信息 (Error message): 学生信息不能为空 (Student information cannot be null)
```

## 测试结果 (Test Results)

所有测试均已通过：

```
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
```

测试覆盖：
- ✅ 添加学生
- ✅ 删除学生
- ✅ 修改学生
- ✅ 查询学生
- ✅ 查询所有学生
- ✅ 添加无效学生数据
- ✅ 重复学生ID
- ✅ 删除不存在的学生
- ✅ 修改不存在的学生
- ✅ 查询不存在的学生

## 核心技术说明 (Core Technologies)

### IoC (控制反转)

使用Spring Boot容器管理StudentService，通过@Service注解和@SpringBootApplication自动配置实现依赖注入：

```java
@SpringBootApplication  // 自动启用组件扫描和自动配置
public class Main { ... }
```

### AOP (面向切面编程)

通过LoggingAspect类实现横切关注点，自动记录所有Service方法的调用：

- @Before: 方法执行前记录
- @AfterReturning: 方法成功执行后记录
- @AfterThrowing: 方法抛出异常时记录
- @Around: 记录方法执行时间

## License

MIT License