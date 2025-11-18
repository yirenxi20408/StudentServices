package com.student;

import com.student.exception.InvalidOperationException;
import com.student.exception.InvalidStudentException;
import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import com.student.service.StudentService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;
import java.util.Scanner;

/**
 * Main application class for Student Management System
 */
public class Main {

    private static StudentService studentService;
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("学生信息管理系统 (Student Management System)");
        System.out.println("使用IoC和AOP技术 (Using IoC and AOP)");
        System.out.println("========================================\n");

        // Load Spring context
        ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
        studentService = context.getBean(StudentService.class);
        
        System.out.println("Spring容器加载成功! (Spring container loaded successfully!)\n");

        // Main menu loop
        while (true) {
            showMenu();
            String command = scanner.nextLine().trim();
            
            try {
                processCommand(command);
            } catch (InvalidOperationException e) {
                System.err.println("错误 (Error): " + e.getMessage());
                System.out.println();
            } catch (InvalidStudentException e) {
                System.err.println("无效数据 (Invalid Data): " + e.getMessage());
                System.out.println();
            } catch (StudentNotFoundException e) {
                System.err.println("未找到 (Not Found): " + e.getMessage());
                System.out.println();
            } catch (Exception e) {
                System.err.println("系统错误 (System Error): " + e.getMessage());
                System.out.println();
            }
        }
    }

    private static void showMenu() {
        System.out.println("请选择操作 (Please select an operation):");
        System.out.println("1. 添加学生 (Add Student)");
        System.out.println("2. 删除学生 (Delete Student)");
        System.out.println("3. 修改学生 (Modify Student)");
        System.out.println("4. 查询学生 (Query Student)");
        System.out.println("5. 查询所有学生 (Query All Students)");
        System.out.println("6. 退出 (Exit)");
        System.out.print("输入命令 (Enter command): ");
    }

    private static void processCommand(String command) {
        switch (command) {
            case "1":
                addStudent();
                break;
            case "2":
                deleteStudent();
                break;
            case "3":
                modifyStudent();
                break;
            case "4":
                queryStudent();
                break;
            case "5":
                queryAllStudents();
                break;
            case "6":
                System.out.println("感谢使用! (Thank you!)");
                System.exit(0);
                break;
            default:
                throw new InvalidOperationException(
                    "无效的命令: '" + command + "'. 请输入1-6之间的数字 " +
                    "(Invalid command: '" + command + "'. Please enter a number between 1-6)"
                );
        }
    }

    private static void addStudent() {
        System.out.print("学生ID (Student ID): ");
        String sid = scanner.nextLine().trim();
        
        System.out.print("姓名 (Name): ");
        String name = scanner.nextLine().trim();
        
        System.out.print("电话 (Telephone): ");
        String tele = scanner.nextLine().trim();
        
        Student student = new Student(sid, name, tele);
        studentService.addStudent(student);
    }

    private static void deleteStudent() {
        System.out.print("学生ID (Student ID): ");
        String sid = scanner.nextLine().trim();
        
        studentService.deleteStudent(sid);
    }

    private static void modifyStudent() {
        System.out.print("学生ID (Student ID): ");
        String sid = scanner.nextLine().trim();
        
        System.out.print("新姓名 (New Name): ");
        String name = scanner.nextLine().trim();
        
        System.out.print("新电话 (New Telephone): ");
        String tele = scanner.nextLine().trim();
        
        Student student = new Student(sid, name, tele);
        studentService.modifyStudent(student);
    }

    private static void queryStudent() {
        System.out.print("学生ID (Student ID): ");
        String sid = scanner.nextLine().trim();
        
        Student student = studentService.queryStudent(sid);
        System.out.println("\n查询结果 (Query Result):");
        System.out.println(student);
        System.out.println();
    }

    private static void queryAllStudents() {
        List<Student> students = studentService.queryAllStudents();
        System.out.println("\n所有学生 (All Students): 共 " + students.size() + " 人");
        if (students.isEmpty()) {
            System.out.println("暂无学生数据 (No student data)");
        } else {
            for (Student student : students) {
                System.out.println(student);
            }
        }
        System.out.println();
    }
}
