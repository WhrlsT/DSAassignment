package com.mycompany.dsaassignment.entity;

import java.time.LocalDateTime;

public class Consultation {
    private String consultationId;
    private String patientId;
    private String doctorId;
    private LocalDateTime consultationDateTime;
    private String diagnosis;
    private String treatment;
    private String notes;
    private String status; // "SCHEDULED", "IN_PROGRESS", "COMPLETED", "CANCELLED"
    private LocalDateTime followUpDateTime;
    private String followUpReason;
    private boolean requiresFollowUp;

    public Consultation(String consultationId, String patientId, String doctorId, 
                       LocalDateTime consultationDateTime, String diagnosis, String treatment, 
                       String notes, String status) {
        this.consultationId = consultationId;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.consultationDateTime = consultationDateTime;
        this.diagnosis = diagnosis;
        this.treatment = treatment;
        this.notes = notes;
        this.status = status;
        this.requiresFollowUp = false;
    }

    // Getters and Setters
    public String getConsultationId() { return consultationId; }
    public void setConsultationId(String consultationId) { this.consultationId = consultationId; }
    
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }
    
    public LocalDateTime getConsultationDateTime() { return consultationDateTime; }
    public void setConsultationDateTime(LocalDateTime consultationDateTime) { this.consultationDateTime = consultationDateTime; }
    
    public String getDiagnosis() { return diagnosis; }
    public void setDiagnosis(String diagnosis) { this.diagnosis = diagnosis; }
    
    public String getTreatment() { return treatment; }
    public void setTreatment(String treatment) { this.treatment = treatment; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public LocalDateTime getFollowUpDateTime() { return followUpDateTime; }
    public void setFollowUpDateTime(LocalDateTime followUpDateTime) { this.followUpDateTime = followUpDateTime; }
    
    public String getFollowUpReason() { return followUpReason; }
    public void setFollowUpReason(String followUpReason) { this.followUpReason = followUpReason; }
    
    public boolean isRequiresFollowUp() { return requiresFollowUp; }
    public void setRequiresFollowUp(boolean requiresFollowUp) { this.requiresFollowUp = requiresFollowUp; }

    @Override
    public String toString() {
        return String.format("%-12s %-10s %-10s %-20s %-12s", 
                           consultationId, patientId, doctorId, 
                           consultationDateTime.toString().substring(0, 16), status);
    }
} 