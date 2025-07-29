package com.mycompany.dsaassignment.entity;

import java.time.LocalDateTime;

public class Queue {
    private String queueId;
    private String patientId;
    private int queueNumber;
    private LocalDateTime queueDateTime;
    private String status; // "WAITING", "IN_PROGRESS", "COMPLETED", "CANCELLED"
    private String priority; // "NORMAL", "URGENT", "EMERGENCY"

    public Queue(String queueId, String patientId, int queueNumber, LocalDateTime queueDateTime, String status, String priority) {
        this.queueId = queueId;
        this.patientId = patientId;
        this.queueNumber = queueNumber;
        this.queueDateTime = queueDateTime;
        this.status = status;
        this.priority = priority;
    }

    // Getters and Setters
    public String getQueueId() { return queueId; }
    public void setQueueId(String queueId) { this.queueId = queueId; }
    
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }
    
    public int getQueueNumber() { return queueNumber; }
    public void setQueueNumber(int queueNumber) { this.queueNumber = queueNumber; }
    
    public LocalDateTime getQueueDateTime() { return queueDateTime; }
    public void setQueueDateTime(LocalDateTime queueDateTime) { this.queueDateTime = queueDateTime; }
    
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    
    public String getPriority() { return priority; }
    public void setPriority(String priority) { this.priority = priority; }

    @Override
    public String toString() {
        return String.format("%-8s %-10s %-8d %-20s %-12s %-10s", 
                           queueId, patientId, queueNumber, 
                           queueDateTime.toString().substring(0, 16), status, priority);
    }
} 