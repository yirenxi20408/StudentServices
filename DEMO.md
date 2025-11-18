# Student Management System Demo - 系统演示

## 功能演示 (Feature Demonstration)

本文档展示系统的主要功能和AOP日志输出。

### 1. 添加学生 (Add Student)

当添加学生时，AOP会自动记录：

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

### 2. 查询学生 (Query Student)

```
=== AOP日志 (AOP Log) ===
时间 (Time): 2025-11-18 05:55:34
操作 (Operation): queryStudent
参数 (Parameters): [001]
操作 (Operation) 'queryStudent' 执行成功 (executed successfully)
返回值 (Return value): Student{sid='001', name='张三', tele='13800138000'}
执行时间 (Execution time): 0ms
======================
```

### 3. 修改学生 (Modify Student)

```
=== AOP日志 (AOP Log) ===
时间 (Time): 2025-11-18 05:55:34
操作 (Operation): modifyStudent
参数 (Parameters): [Student{sid='003', name='王五修改', tele='13900139000'}]
成功修改学生信息: Student{sid='003', name='王五修改', tele='13900139000'} (Student modified successfully)
操作 (Operation) 'modifyStudent' 执行成功 (executed successfully)
执行时间 (Execution time): 2ms
======================
```

### 4. 删除学生 (Delete Student)

```
=== AOP日志 (AOP Log) ===
时间 (Time): 2025-11-18 05:55:34
操作 (Operation): deleteStudent
参数 (Parameters): [002]
成功删除学生: Student{sid='002', name='李四', tele='13800138001'} (Student deleted successfully)
操作 (Operation) 'deleteStudent' 执行成功 (executed successfully)
执行时间 (Execution time): 0ms
======================
```

### 5. 错误处理 - 学生不存在 (Error Handling - Student Not Found)

```
=== AOP日志 (AOP Log) ===
时间 (Time): 2025-11-18 05:55:34
操作 (Operation): queryStudent
参数 (Parameters): [999]
操作 (Operation) 'queryStudent' 执行失败 (execution failed)
错误信息 (Error message): 未找到学生ID: 999 (Student not found)
执行时间 (Execution time): 1ms
======================
```

### 6. 错误处理 - 重复ID (Error Handling - Duplicate ID)

```
=== AOP日志 (AOP Log) ===
时间 (Time): 2025-11-18 05:55:34
操作 (Operation): addStudent
参数 (Parameters): [Student{sid='008', name='学生B', tele='13800138007'}]
操作 (Operation) 'addStudent' 执行失败 (execution failed)
错误信息 (Error message): 学生ID已存在: 008 (Student ID already exists)
执行时间 (Execution time): 1ms
======================
```

### 7. 错误处理 - 无效数据 (Error Handling - Invalid Data)

```
错误信息 (Error message): 学生信息不能为空 (Student information cannot be null)
错误信息 (Error message): 学生ID不能为空 (Student ID cannot be empty)
错误信息 (Error message): 学生姓名不能为空 (Student name cannot be empty)
```

## 运行系统 (Running the System)

### 运行测试 (Run Tests)

```bash
mvn test
```

结果：
```
Tests run: 10, Failures: 0, Errors: 0, Skipped: 0
BUILD SUCCESS
```

### 运行主程序 (Run Main Application)

```bash
mvn exec:java -Dexec.mainClass="com.student.Main"
```

系统会显示菜单：
```
========================================
学生信息管理系统 (Student Management System)
使用IoC和AOP技术 (Using IoC and AOP)
========================================

Spring容器加载成功! (Spring container loaded successfully!)

请选择操作 (Please select an operation):
1. 添加学生 (Add Student)
2. 删除学生 (Delete Student)
3. 修改学生 (Modify Student)
4. 查询学生 (Query Student)
5. 查询所有学生 (Query All Students)
6. 退出 (Exit)
输入命令 (Enter command):
```

## AOP切面说明 (AOP Aspect Explanation)

系统使用了4种通知类型：

1. **@Before** - 在方法执行前记录时间、操作名称和参数
2. **@AfterReturning** - 在方法成功返回后记录返回值
3. **@AfterThrowing** - 在方法抛出异常时记录错误信息
4. **@Around** - 环绕通知，用于记录方法执行时间

## IoC配置 (IoC Configuration)

使用两种方式配置IoC：

1. **注解方式**：
   - @Service 标注服务实现类
   - @Component 标注切面类
   - @Autowired 自动注入依赖

2. **XML配置**：
   - component-scan 启用组件扫描
   - aspectj-autoproxy 启用AOP自动代理

## 自定义异常 (Custom Exceptions)

- **StudentNotFoundException** - 学生不存在
- **InvalidStudentException** - 无效的学生数据
- **InvalidOperationException** - 无效的操作命令

所有异常都提供中英文双语错误提示。
