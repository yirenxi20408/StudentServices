package com.student.service;

import com.student.exception.InvalidStudentException;
import com.student.exception.StudentNotFoundException;
import com.student.model.Student;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Student service implementation
 */
@Service
public class StudentServiceImpl implements StudentService {
    
    // In-memory storage for students
    private Map<String, Student> studentMap = new ConcurrentHashMap<>();

    @Override
    public void addStudent(Student student) {
        if (student == null) {
            throw new InvalidStudentException("学生信息不能为空 (Student information cannot be null)");
        }
        if (student.getSid() == null || student.getSid().trim().isEmpty()) {
            throw new InvalidStudentException("学生ID不能为空 (Student ID cannot be empty)");
        }
        if (student.getName() == null || student.getName().trim().isEmpty()) {
            throw new InvalidStudentException("学生姓名不能为空 (Student name cannot be empty)");
        }
        if (studentMap.containsKey(student.getSid())) {
            throw new InvalidStudentException("学生ID已存在: " + student.getSid() + " (Student ID already exists)");
        }
        studentMap.put(student.getSid(), student);
        System.out.println("成功添加学生: " + student + " (Student added successfully)");
    }

    @Override
    public void deleteStudent(String sid) {
        if (sid == null || sid.trim().isEmpty()) {
            throw new InvalidStudentException("学生ID不能为空 (Student ID cannot be empty)");
        }
        if (!studentMap.containsKey(sid)) {
            throw new StudentNotFoundException("未找到学生ID: " + sid + " (Student not found)");
        }
        Student removed = studentMap.remove(sid);
        System.out.println("成功删除学生: " + removed + " (Student deleted successfully)");
    }

    @Override
    public void modifyStudent(Student student) {
        if (student == null) {
            throw new InvalidStudentException("学生信息不能为空 (Student information cannot be null)");
        }
        if (student.getSid() == null || student.getSid().trim().isEmpty()) {
            throw new InvalidStudentException("学生ID不能为空 (Student ID cannot be empty)");
        }
        if (!studentMap.containsKey(student.getSid())) {
            throw new StudentNotFoundException("未找到学生ID: " + student.getSid() + " (Student not found)");
        }
        studentMap.put(student.getSid(), student);
        System.out.println("成功修改学生信息: " + student + " (Student modified successfully)");
    }

    @Override
    public Student queryStudent(String sid) {
        if (sid == null || sid.trim().isEmpty()) {
            throw new InvalidStudentException("学生ID不能为空 (Student ID cannot be empty)");
        }
        Student student = studentMap.get(sid);
        if (student == null) {
            throw new StudentNotFoundException("未找到学生ID: " + sid + " (Student not found)");
        }
        return student;
    }

    @Override
    public List<Student> queryAllStudents() {
        return new ArrayList<>(studentMap.values());
    }
}
