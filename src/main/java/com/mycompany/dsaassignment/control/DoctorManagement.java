package com.mycompany.dsaassignment.control;

import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Doctor;
import com.mycompany.dsaassignment.entity.Room;
import com.mycompany.dsaassignment.boundary.DoctorUI;
import com.mycompany.dsaassignment.dao.DoctorDAO;
import java.time.LocalTime;
import java.time.DayOfWeek;

public class DoctorManagement {
    private ListADT<Doctor> doctorList = new ArrayListADT<>();
    private ListADT<Room> roomList = new ArrayListADT<>();
    private DoctorDAO doctorDAO = new DoctorDAO();
    private DoctorUI doctorUI = new DoctorUI();

    public DoctorManagement() {
        doctorList = doctorDAO.retrieveFromFile();
        initializeRooms();
    }

    private void initializeRooms() {
        // Initialize some default rooms
        roomList.add(new Room("R001", "Consultation Room 1", "CONSULTATION", LocalTime.of(8, 0), LocalTime.of(20, 0)));
        roomList.add(new Room("R002", "Consultation Room 2", "CONSULTATION", LocalTime.of(8, 0), LocalTime.of(20, 0)));
        roomList.add(new Room("R003", "Treatment Room 1", "TREATMENT", LocalTime.of(8, 0), LocalTime.of(20, 0)));
        roomList.add(new Room("R004", "Emergency Room", "EMERGENCY", LocalTime.of(0, 0), LocalTime.of(23, 59)));
    }

    public void runDoctorManagement() {
        int choice = 0;
        do {
            choice = doctorUI.getMenuChoice();
            switch(choice) {
                case 0:
                    doctorUI.displayMessage("Returning to main menu.");
                    break;
                case 1:
                    addNewDoctor();
                    break;
                case 2:
                    viewAllDoctors();
                    break;
                case 3:
                    updateDoctor();
                    break;
                case 4:
                    deleteDoctor();
                    break;
                case 5:
                    viewDoctorSchedule();
                    break;
                case 6:
                    viewAvailabilityTable();
                    break;
                default:
                    doctorUI.displayMessage("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void addNewDoctor() {
        Doctor newDoctor = doctorUI.inputDoctorDetails();
        doctorList.add(newDoctor);
        doctorDAO.saveToFile(doctorList);
        doctorUI.displayMessage("Doctor added successfully.");
    }

    public void viewAllDoctors() {
        String outputStr = getAllDoctors();
        doctorUI.displayDoctorsTable(outputStr);
    }

    public String getAllDoctors() {
        String outputStr = "";
        for (int i = 0; i < doctorList.size(); i++) {
            outputStr += doctorList.get(i) + "\n";
        }
        return outputStr;
    }

    public void updateDoctor() {
        String doctorId = doctorUI.inputDoctorId();
        Doctor doctor = findDoctorById(doctorId);
        
        if (doctor != null) {
            doctorUI.displayMessage("Current doctor details:");
            doctorUI.displayMessage(doctor.toString());
            
            // Update fields
            doctor.setName(doctorUI.inputDoctorName());
            doctor.setSpecialization(doctorUI.inputSpecialization());
            doctor.setContact(doctorUI.inputContact());
            doctor.setAvailableDays(doctorUI.inputAvailableDays());
            doctor.setStartTime(doctorUI.inputStartTime());
            doctor.setEndTime(doctorUI.inputEndTime());
            
            doctorDAO.saveToFile(doctorList);
            doctorUI.displayMessage("Doctor updated successfully.");
        } else {
            doctorUI.displayMessage("Doctor not found.");
        }
    }

    public void deleteDoctor() {
        String doctorId = doctorUI.inputDoctorId();
        Doctor doctor = findDoctorById(doctorId);
        
        if (doctor != null) {
            doctorList.remove(doctor);
            doctorDAO.saveToFile(doctorList);
            doctorUI.displayMessage("Doctor deleted successfully.");
        } else {
            doctorUI.displayMessage("Doctor not found.");
        }
    }

    public void viewDoctorSchedule() {
        String doctorId = doctorUI.inputDoctorId();
        Doctor doctor = findDoctorById(doctorId);
        
        if (doctor != null) {
            doctorUI.displayMessage("Doctor Schedule for: " + doctor.getName());
            doctorUI.displayMessage("Specialization: " + doctor.getSpecialization());
            doctorUI.displayMessage("Working Hours: " + doctor.getStartTime() + " - " + doctor.getEndTime());
            doctorUI.displayMessage("Available Days:");
            
            DayOfWeek[] days = doctor.getAvailableDays();
            for (DayOfWeek day : days) {
                doctorUI.displayMessage("- " + day.toString());
            }
            
            doctorUI.displayMessage("Status: " + (doctor.isAvailable() ? "AVAILABLE" : "UNAVAILABLE"));
        } else {
            doctorUI.displayMessage("Doctor not found.");
        }
    }

    public void viewAvailabilityTable() {
        String outputStr = getAvailabilityTable();
        doctorUI.displayAvailabilityTable(outputStr);
    }

    public String getAvailabilityTable() {
        String outputStr = "";
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            String schedule = doctor.getStartTime() + "-" + doctor.getEndTime();
            String status = doctor.isAvailable() ? "AVAILABLE" : "UNAVAILABLE";
            
            outputStr += String.format("%-10s %-20s %-15s %-12s %-20s\n", 
                                     doctor.getDoctorId(), doctor.getName(), 
                                     doctor.getSpecialization(), status, schedule);
        }
        return outputStr;
    }

    public String getRoomAvailabilityTable() {
        String outputStr = "";
        for (int i = 0; i < roomList.size(); i++) {
            Room room = roomList.get(i);
            outputStr += room.toString() + "\n";
        }
        return outputStr;
    }

    private Doctor findDoctorById(String doctorId) {
        for (int i = 0; i < doctorList.size(); i++) {
            Doctor doctor = doctorList.get(i);
            if (doctor.getDoctorId().equals(doctorId)) {
                return doctor;
            }
        }
        return null;
    }
} 