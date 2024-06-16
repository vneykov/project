package com.fib.project.fibProject.services;

import com.fib.project.fibProject.exceptions.FileOperationException;
import org.springframework.stereotype.Service;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class FileWriterService {

    private static final String OPERATIONS_LOG = "operations.txt";
    private static final String BALANCE_LOG = "balances.txt";

    public void logOperation(String operation) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(OPERATIONS_LOG, true))) {
            writer.write(operation);
            writer.newLine();
        } catch (IOException e) {
            throw new FileOperationException("Failed to log operation", e);
        }
    }

    public void logBalance(String balance) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(BALANCE_LOG, true))) {
            writer.write(balance);
            writer.newLine();
        } catch (IOException e) {
            throw new FileOperationException("Failed to log operation", e);
        }
    }
}
