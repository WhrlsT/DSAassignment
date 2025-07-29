package com.mycompany.dsaassignment.entity;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class Doctor {
    private String doctorId;
    private String name;
    private String specialization;
    private String contact;
    private DayOfWeek[] availableDays;
    private LocalTime startTime;
    private LocalTime endTime;
    private boolean isAvailable;

    public Doctor(String doctorId, String name, String specialization, String contact, 
                  DayOfWeek[] availableDays, LocalTime startTime, LocalTime endTime) {
        this.doctorId = doctorId;
        this.name = name;
        this.specialization = specialization;
        this.contact = contact;
        this.availableDays = availableDays;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true;
    }

    // Getters and Setters
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public DayOfWeek[] getAvailableDays() { return availableDays; }
    public void setAvailableDays(DayOfWeek[] availableDays) { this.availableDays = availableDays; }
    
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s %-15s", doctorId, name, specialization, contact);
    }
} 