package com.mycompany.dsaassignment.entity;

public class Patient {
    private String patientId;
    private String name;
    private String icOrPassport;
    private String contact;
    private String email;

    public Patient(String patientId, String name, String icOrPassport, String contact, String email) {
        this.patientId = patientId;
        this.name = name;
        this.icOrPassport = icOrPassport;
        this.contact = contact;
        this.email = email;
    }

    // Getters and Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public String getIcOrPassport() { return icOrPassport; }
    public void setIcOrPassport(String icOrPassport) { this.icOrPassport = icOrPassport; }
    
    public String getContact() { return contact; }
    public void setContact(String contact) { this.contact = contact; }
    
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    @Override
    public String toString() {
        return String.format("%-10s %-20s %-15s %-15s", patientId, name, icOrPassport, contact);
    }
} 