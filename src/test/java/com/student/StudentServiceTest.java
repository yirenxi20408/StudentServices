package com.student;

import com.student.exception.InvalidStudentException;
import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import com.student.service.StudentService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Test class for Student Service with IoC and AOP
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class StudentServiceTest {

    @Autowired
    private StudentService studentService;

    @Before
    public void setUp() {
        // Clear all students before each test
        List<Student> allStudents = studentService.queryAllStudents();
        for (Student student : allStudents) {
            try {
                studentService.deleteStudent(student.getSid());
            } catch (Exception e) {
                // Ignore
            }
        }
    }

    @Test
    public void testAddStudent() {
        System.out.println("\n=== 测试添加学生 (Test Add Student) ===");
        Student student = new Student("001", "张三", "13800138000");
        studentService.addStudent(student);
        
        Student queried = studentService.queryStudent("001");
        assertNotNull(queried);
        assertEquals("张三", queried.getName());
        assertEquals("13800138000", queried.getTele());
    }

    @Test
    public void testDeleteStudent() {
        System.out.println("\n=== 测试删除学生 (Test Delete Student) ===");
        Student student = new Student("002", "李四", "13800138001");
        studentService.addStudent(student);
        
        studentService.deleteStudent("002");
        
        try {
            studentService.queryStudent("002");
            fail("应该抛出StudentNotFoundException (Should throw StudentNotFoundException)");
        } catch (StudentNotFoundException e) {
            assertTrue(e.getMessage().contains("未找到学生ID"));
        }
    }

    @Test
    public void testModifyStudent() {
        System.out.println("\n=== 测试修改学生 (Test Modify Student) ===");
        Student student = new Student("003", "王五", "13800138002");
        studentService.addStudent(student);
        
        Student modified = new Student("003", "王五修改", "13900139000");
        studentService.modifyStudent(modified);
        
        Student queried = studentService.queryStudent("003");
        assertEquals("王五修改", queried.getName());
        assertEquals("13900139000", queried.getTele());
    }

    @Test
    public void testQueryStudent() {
        System.out.println("\n=== 测试查询学生 (Test Query Student) ===");
        Student student = new Student("004", "赵六", "13800138003");
        studentService.addStudent(student);
        
        Student queried = studentService.queryStudent("004");
        assertNotNull(queried);
        assertEquals("004", queried.getSid());
        assertEquals("赵六", queried.getName());
    }

    @Test
    public void testQueryAllStudents() {
        System.out.println("\n=== 测试查询所有学生 (Test Query All Students) ===");
        studentService.addStudent(new Student("005", "孙七", "13800138004"));
        studentService.addStudent(new Student("006", "周八", "13800138005"));
        
        List<Student> students = studentService.queryAllStudents();
        assertEquals(2, students.size());
    }

    @Test
    public void testAddStudentWithInvalidData() {
        System.out.println("\n=== 测试添加无效学生数据 (Test Add Invalid Student) ===");
        
        // Test null student
        try {
            studentService.addStudent(null);
            fail("应该抛出InvalidStudentException (Should throw InvalidStudentException)");
        } catch (InvalidStudentException e) {
            assertTrue(e.getMessage().contains("学生信息不能为空"));
        }
        
        // Test empty student ID
        try {
            studentService.addStudent(new Student("", "测试", "12345678901"));
            fail("应该抛出InvalidStudentException (Should throw InvalidStudentException)");
        } catch (InvalidStudentException e) {
            assertTrue(e.getMessage().contains("学生ID不能为空"));
        }
        
        // Test empty name
        try {
            studentService.addStudent(new Student("007", "", "12345678901"));
            fail("应该抛出InvalidStudentException (Should throw InvalidStudentException)");
        } catch (InvalidStudentException e) {
            assertTrue(e.getMessage().contains("学生姓名不能为空"));
        }
    }

    @Test
    public void testDuplicateStudentId() {
        System.out.println("\n=== 测试重复学生ID (Test Duplicate Student ID) ===");
        Student student1 = new Student("008", "学生A", "13800138006");
        studentService.addStudent(student1);
        
        try {
            Student student2 = new Student("008", "学生B", "13800138007");
            studentService.addStudent(student2);
            fail("应该抛出InvalidStudentException (Should throw InvalidStudentException)");
        } catch (InvalidStudentException e) {
            assertTrue(e.getMessage().contains("学生ID已存在"));
        }
    }

    @Test
    public void testDeleteNonExistentStudent() {
        System.out.println("\n=== 测试删除不存在的学生 (Test Delete Non-existent Student) ===");
        try {
            studentService.deleteStudent("999");
            fail("应该抛出StudentNotFoundException (Should throw StudentNotFoundException)");
        } catch (StudentNotFoundException e) {
            assertTrue(e.getMessage().contains("未找到学生ID"));
        }
    }

    @Test
    public void testModifyNonExistentStudent() {
        System.out.println("\n=== 测试修改不存在的学生 (Test Modify Non-existent Student) ===");
        try {
            Student student = new Student("999", "不存在", "12345678901");
            studentService.modifyStudent(student);
            fail("应该抛出StudentNotFoundException (Should throw StudentNotFoundException)");
        } catch (StudentNotFoundException e) {
            assertTrue(e.getMessage().contains("未找到学生ID"));
        }
    }

    @Test
    public void testQueryNonExistentStudent() {
        System.out.println("\n=== 测试查询不存在的学生 (Test Query Non-existent Student) ===");
        try {
            studentService.queryStudent("999");
            fail("应该抛出StudentNotFoundException (Should throw StudentNotFoundException)");
        } catch (StudentNotFoundException e) {
            assertTrue(e.getMessage().contains("未找到学生ID"));
        }
    }
}
