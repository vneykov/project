package com.fib.project.fibProject.exceptions;

public class FileOperationException extends RuntimeException {
    public FileOperationException(String message, Throwable clause) {
        super(message, clause);
    }
}
