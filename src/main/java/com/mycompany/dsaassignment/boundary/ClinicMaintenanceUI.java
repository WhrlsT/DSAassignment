package com.mycompany.dsaassignment.boundary;

import java.util.Scanner;
import com.mycompany.dsaassignment.utility.InputUtil;

/**
 * Simple UI for Clinic Maintenance
 */
public class ClinicMaintenanceUI {
    private Scanner scanner = new Scanner(System.in);

    public void printWelcome() {
        System.out.println("\n====================================");
        System.out.println(" Welcome to TAR UMT Clinic System ");
        System.out.println("====================================\n");
    }

    public int getMenuChoice() {
        System.out.println("MAIN MENU");
        System.out.println("1. Patient Management");
        System.out.println("2. Doctor Management");
        System.out.println("3. Consultation Management");
        System.out.println("4. Medical Treatment Management");
        System.out.println("5. Pharmacy Management");
        System.out.println("0. Quit");
        int choice = InputUtil.getIntInput(scanner, "Enter choice: ");
        System.out.println();
        return choice;
    }
} 