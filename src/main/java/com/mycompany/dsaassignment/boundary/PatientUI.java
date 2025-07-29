package com.mycompany.dsaassignment.boundary;

import com.mycompany.dsaassignment.entity.Patient;
import com.mycompany.dsaassignment.utility.InputUtil;
import java.util.Scanner;

public class PatientUI {
    private Scanner scanner = new Scanner(System.in);

    public int getMenuChoice() {
        System.out.println("\nPATIENT MANAGEMENT MENU");
        System.out.println("1. Add new patient");
        System.out.println("2. View all patients");
        System.out.println("3. Update patient");
        System.out.println("4. Delete patient");
        System.out.println("5. Manage patient queue");
        System.out.println("0. Return to main menu");
        return InputUtil.getIntInput(scanner, "Enter choice: ");
    }

    public void displayPatientsTable(String outputStr) {
        System.out.println("\n=== PATIENTS TABLE ===");
        System.out.println("ID\t\tName\t\tIC/Passport\tContact\t\tEmail");
        System.out.println("--------------------------------------------------------");
        System.out.println(outputStr);
    }

    public String inputPatientId() {
        System.out.print("Enter patient ID: ");
        return scanner.nextLine();
    }

    public String inputPatientName() {
        System.out.print("Enter patient name: ");
        return scanner.nextLine();
    }

    public String inputIcOrPassport() {
        System.out.print("Enter IC/Passport: ");
        return scanner.nextLine();
    }

    public String inputContact() {
        System.out.print("Enter contact number: ");
        return scanner.nextLine();
    }

    public String inputEmail() {
        System.out.print("Enter email: ");
        return scanner.nextLine();
    }

    public Patient inputPatientDetails() {
        String patientId = inputPatientId();
        String name = inputPatientName();
        String icOrPassport = inputIcOrPassport();
        String contact = inputContact();
        String email = inputEmail();
        
        return new Patient(patientId, name, icOrPassport, contact, email);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
} 