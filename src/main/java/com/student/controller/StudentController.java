package com.student.controller;

import com.student.model.Student;
import com.student.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * REST Controller for Student operations
 */
@RestController
@RequestMapping("/api/students")
@CrossOrigin(origins = "*")
public class StudentController {

    @Autowired
    private StudentService studentService;

    /**
     * Get all students
     */
    @GetMapping
    public ResponseEntity<List<Student>> getAllStudents() {
        List<Student> students = studentService.queryAllStudents();
        return ResponseEntity.ok(students);
    }

    /**
     * Get student by ID
     */
    @GetMapping("/{sid}")
    public ResponseEntity<?> getStudent(@PathVariable String sid) {
        try {
            Student student = studentService.queryStudent(sid);
            return ResponseEntity.ok(student);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * Add a new student
     */
    @PostMapping
    public ResponseEntity<?> addStudent(@RequestBody Student student) {
        try {
            studentService.addStudent(student);
            Map<String, String> response = new HashMap<>();
            response.put("message", "成功添加学生 (Student added successfully)");
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
        }
    }

    /**
     * Update student information
     */
    @PutMapping("/{sid}")
    public ResponseEntity<?> updateStudent(@PathVariable String sid, @RequestBody Student student) {
        try {
            student.setSid(sid);
            studentService.modifyStudent(student);
            Map<String, String> response = new HashMap<>();
            response.put("message", "成功修改学生信息 (Student updated successfully)");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }

    /**
     * Delete student by ID
     */
    @DeleteMapping("/{sid}")
    public ResponseEntity<?> deleteStudent(@PathVariable String sid) {
        try {
            studentService.deleteStudent(sid);
            Map<String, String> response = new HashMap<>();
            response.put("message", "成功删除学生 (Student deleted successfully)");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            Map<String, String> error = new HashMap<>();
            error.put("error", e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
        }
    }
}
