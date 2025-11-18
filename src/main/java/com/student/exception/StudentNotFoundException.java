package com.student.exception;

/**
 * Custom exception for student not found
 */
public class StudentNotFoundException extends RuntimeException {
    public StudentNotFoundException(String message) {
        super(message);
    }
}
