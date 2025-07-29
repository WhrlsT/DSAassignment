package com.mycompany.dsaassignment.boundary;

import com.mycompany.dsaassignment.entity.Queue;
import com.mycompany.dsaassignment.utility.InputUtil;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class QueueUI {
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public int getQueueMenuChoice() {
        System.out.println("\nQUEUE MANAGEMENT MENU");
        System.out.println("1. Add patient to queue");
        System.out.println("2. View current queue");
        System.out.println("3. Update queue status");
        System.out.println("4. Remove patient from queue");
        System.out.println("5. Edit queue arrangement");
        System.out.println("0. Return to patient menu");
        return InputUtil.getIntInput(scanner, "Enter choice: ");
    }

    public void displayQueueTable(String outputStr) {
        System.out.println("\n=== PATIENT QUEUE TABLE ===");
        System.out.println("QueueID\tPatientID\tNumber\tDateTime\t\tStatus\tPriority");
        System.out.println("--------------------------------------------------------");
        System.out.println(outputStr);
    }

    public String inputQueueId() {
        System.out.print("Enter queue ID: ");
        return scanner.nextLine();
    }

    public String inputPatientId() {
        System.out.print("Enter patient ID: ");
        return scanner.nextLine();
    }

    public int inputQueueNumber() {
        return InputUtil.getIntInput(scanner, "Enter queue number: ");
    }

    public LocalDateTime inputQueueDateTime() {
        System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date format. Using current date and time.");
            return LocalDateTime.now();
        }
    }

    public String inputQueueStatus() {
        System.out.println("Select status:");
        System.out.println("1. WAITING");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");
        int choice = InputUtil.getIntInput(scanner, "Enter choice: ");
        switch (choice) {
            case 1: return "WAITING";
            case 2: return "IN_PROGRESS";
            case 3: return "COMPLETED";
            case 4: return "CANCELLED";
            default: return "WAITING";
        }
    }

    public String inputPriority() {
        System.out.println("Select priority:");
        System.out.println("1. NORMAL");
        System.out.println("2. URGENT");
        System.out.println("3. EMERGENCY");
        int choice = InputUtil.getIntInput(scanner, "Enter choice: ");
        switch (choice) {
            case 1: return "NORMAL";
            case 2: return "URGENT";
            case 3: return "EMERGENCY";
            default: return "NORMAL";
        }
    }

    public Queue inputQueueDetails() {
        String queueId = inputQueueId();
        String patientId = inputPatientId();
        int queueNumber = inputQueueNumber();
        LocalDateTime queueDateTime = inputQueueDateTime();
        String status = inputQueueStatus();
        String priority = inputPriority();
        
        return new Queue(queueId, patientId, queueNumber, queueDateTime, status, priority);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
} 