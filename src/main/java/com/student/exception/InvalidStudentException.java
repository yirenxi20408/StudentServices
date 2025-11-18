package com.student.exception;

/**
 * Custom exception for invalid student data
 */
public class InvalidStudentException extends RuntimeException {
    public InvalidStudentException(String message) {
        super(message);
    }
}
