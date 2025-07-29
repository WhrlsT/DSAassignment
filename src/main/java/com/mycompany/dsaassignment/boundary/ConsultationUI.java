package com.mycompany.dsaassignment.boundary;

import com.mycompany.dsaassignment.entity.Consultation;
import com.mycompany.dsaassignment.utility.InputUtil;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ConsultationUI {
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

    public int getMenuChoice() {
        System.out.println("\nCONSULTATION MANAGEMENT MENU");
        System.out.println("1. Add new consultation");
        System.out.println("2. View all consultations");
        System.out.println("3. Update consultation");
        System.out.println("4. Delete consultation");
        System.out.println("5. Schedule follow-up appointment");
        System.out.println("0. Return to main menu");
        return InputUtil.getIntInput(scanner, "Enter choice: ");
    }

    public void displayConsultationsTable(String outputStr) {
        System.out.println("\n=== CONSULTATIONS TABLE ===");
        System.out.println("ID\t\tPatient\tDoctor\tDateTime\t\tStatus");
        System.out.println("--------------------------------------------------------");
        System.out.println(outputStr);
    }

    public String inputConsultationId() {
        System.out.print("Enter consultation ID: ");
        return scanner.nextLine();
    }

    public String inputPatientId() {
        System.out.print("Enter patient ID: ");
        return scanner.nextLine();
    }

    public String inputDoctorId() {
        System.out.print("Enter doctor ID: ");
        return scanner.nextLine();
    }

    public LocalDateTime inputDateTime() {
        System.out.print("Enter date and time (yyyy-MM-dd HH:mm): ");
        String dateTimeStr = scanner.nextLine();
        try {
            return LocalDateTime.parse(dateTimeStr, formatter);
        } catch (Exception e) {
            System.out.println("Invalid date format. Using current date and time.");
            return LocalDateTime.now();
        }
    }

    public String inputDiagnosis() {
        System.out.print("Enter diagnosis: ");
        return scanner.nextLine();
    }

    public String inputTreatment() {
        System.out.print("Enter treatment: ");
        return scanner.nextLine();
    }

    public String inputNotes() {
        System.out.print("Enter notes: ");
        return scanner.nextLine();
    }

    public String inputStatus() {
        System.out.println("Select status:");
        System.out.println("1. SCHEDULED");
        System.out.println("2. IN_PROGRESS");
        System.out.println("3. COMPLETED");
        System.out.println("4. CANCELLED");
        int choice = InputUtil.getIntInput(scanner, "Enter choice: ");
        switch (choice) {
            case 1: return "SCHEDULED";
            case 2: return "IN_PROGRESS";
            case 3: return "COMPLETED";
            case 4: return "CANCELLED";
            default: return "SCHEDULED";
        }
    }

    public Consultation inputConsultationDetails() {
        String consultationId = inputConsultationId();
        String patientId = inputPatientId();
        String doctorId = inputDoctorId();
        LocalDateTime consultationDateTime = inputDateTime();
        String diagnosis = inputDiagnosis();
        String treatment = inputTreatment();
        String notes = inputNotes();
        String status = inputStatus();
        
        return new Consultation(consultationId, patientId, doctorId, consultationDateTime, 
                              diagnosis, treatment, notes, status);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
} 