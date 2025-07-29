package com.mycompany.dsaassignment.utility;

import java.util.Scanner;

public class InputUtil {
    public static int getIntInput(Scanner scanner, String prompt) {
        int value;
        while (true) {
            System.out.print(prompt);
            try {
                value = scanner.nextInt();
                scanner.nextLine(); // consume newline
                break;
            } catch (java.util.InputMismatchException e) {
                System.out.println("Invalid input. Please enter a number.");
                scanner.nextLine(); // consume invalid input
            }
        }
        return value;
    }
} 