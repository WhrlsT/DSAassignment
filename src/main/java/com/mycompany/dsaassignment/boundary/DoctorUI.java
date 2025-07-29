package com.mycompany.dsaassignment.boundary;

import com.mycompany.dsaassignment.entity.Doctor;
import com.mycompany.dsaassignment.utility.InputUtil;
import java.util.Scanner;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class DoctorUI {
    private Scanner scanner = new Scanner(System.in);
    private DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");

    public int getMenuChoice() {
        System.out.println("\nDOCTOR MANAGEMENT MENU");
        System.out.println("1. Add new doctor");
        System.out.println("2. View all doctors");
        System.out.println("3. Update doctor");
        System.out.println("4. Delete doctor");
        System.out.println("5. View doctor schedule");
        System.out.println("6. View availability table");
        System.out.println("0. Return to main menu");
        return InputUtil.getIntInput(scanner, "Enter choice: ");
    }

    public void displayDoctorsTable(String outputStr) {
        System.out.println("\n=== DOCTORS TABLE ===");
        System.out.println("ID\t\tName\t\tSpecialization\tContact");
        System.out.println("--------------------------------------------------------");
        System.out.println(outputStr);
    }

    public void displayAvailabilityTable(String outputStr) {
        System.out.println("\n=== DOCTOR & ROOM AVAILABILITY TABLE ===");
        System.out.println("ID\t\tName\t\tSpecialization\tStatus\t\tSchedule");
        System.out.println("--------------------------------------------------------");
        System.out.println(outputStr);
    }

    public String inputDoctorId() {
        System.out.print("Enter doctor ID: ");
        return scanner.nextLine();
    }

    public String inputDoctorName() {
        System.out.print("Enter doctor name: ");
        return scanner.nextLine();
    }

    public String inputSpecialization() {
        System.out.print("Enter specialization: ");
        return scanner.nextLine();
    }

    public String inputContact() {
        System.out.print("Enter contact number: ");
        return scanner.nextLine();
    }

    public DayOfWeek[] inputAvailableDays() {
        System.out.println("Select available days (comma-separated):");
        System.out.println("1. MONDAY, 2. TUESDAY, 3. WEDNESDAY, 4. THURSDAY, 5. FRIDAY, 6. SATURDAY, 7. SUNDAY");
        System.out.print("Enter choices (e.g., 1,2,3,4,5): ");
        String input = scanner.nextLine();
        String[] choices = input.split(",");
        DayOfWeek[] days = new DayOfWeek[choices.length];
        
        for (int i = 0; i < choices.length; i++) {
            int dayNum = Integer.parseInt(choices[i].trim());
            switch (dayNum) {
                case 1: days[i] = DayOfWeek.MONDAY; break;
                case 2: days[i] = DayOfWeek.TUESDAY; break;
                case 3: days[i] = DayOfWeek.WEDNESDAY; break;
                case 4: days[i] = DayOfWeek.THURSDAY; break;
                case 5: days[i] = DayOfWeek.FRIDAY; break;
                case 6: days[i] = DayOfWeek.SATURDAY; break;
                case 7: days[i] = DayOfWeek.SUNDAY; break;
                default: days[i] = DayOfWeek.MONDAY;
            }
        }
        return days;
    }

    public LocalTime inputStartTime() {
        System.out.print("Enter start time (HH:mm): ");
        String timeStr = scanner.nextLine();
        try {
            return LocalTime.parse(timeStr, timeFormatter);
        } catch (Exception e) {
            System.out.println("Invalid time format. Using 08:00.");
            return LocalTime.of(8, 0);
        }
    }

    public LocalTime inputEndTime() {
        System.out.print("Enter end time (HH:mm): ");
        String timeStr = scanner.nextLine();
        try {
            return LocalTime.parse(timeStr, timeFormatter);
        } catch (Exception e) {
            System.out.println("Invalid time format. Using 20:00.");
            return LocalTime.of(20, 0);
        }
    }

    public Doctor inputDoctorDetails() {
        String doctorId = inputDoctorId();
        String name = inputDoctorName();
        String specialization = inputSpecialization();
        String contact = inputContact();
        DayOfWeek[] availableDays = inputAvailableDays();
        LocalTime startTime = inputStartTime();
        LocalTime endTime = inputEndTime();
        
        return new Doctor(doctorId, name, specialization, contact, availableDays, startTime, endTime);
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }
} 