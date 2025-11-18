package com.student.service;

import com.student.model.Student;
import java.util.List;

/**
 * Student service interface
 */
public interface StudentService {
    /**
     * Add a new student
     */
    void addStudent(Student student);

    /**
     * Delete a student by ID
     */
    void deleteStudent(String sid);

    /**
     * Modify student information
     */
    void modifyStudent(Student student);

    /**
     * Query student by ID
     */
    Student queryStudent(String sid);

    /**
     * Query all students
     */
    List<Student> queryAllStudents();
}
