package com.mycompany.dsaassignment.entity;

import java.time.LocalTime;

public class Room {
    private String roomId;
    private String roomName;
    private String roomType; // "CONSULTATION", "TREATMENT", "EMERGENCY"
    private boolean isAvailable;
    private LocalTime startTime;
    private LocalTime endTime;
    private String assignedDoctorId;

    public Room(String roomId, String roomName, String roomType, LocalTime startTime, LocalTime endTime) {
        this.roomId = roomId;
        this.roomName = roomName;
        this.roomType = roomType;
        this.startTime = startTime;
        this.endTime = endTime;
        this.isAvailable = true;
        this.assignedDoctorId = null;
    }

    // Getters and Setters
    public String getRoomId() { return roomId; }
    public void setRoomId(String roomId) { this.roomId = roomId; }
    
    public String getRoomName() { return roomName; }
    public void setRoomName(String roomName) { this.roomName = roomName; }
    
    public String getRoomType() { return roomType; }
    public void setRoomType(String roomType) { this.roomType = roomType; }
    
    public boolean isAvailable() { return isAvailable; }
    public void setAvailable(boolean available) { isAvailable = available; }
    
    public LocalTime getStartTime() { return startTime; }
    public void setStartTime(LocalTime startTime) { this.startTime = startTime; }
    
    public LocalTime getEndTime() { return endTime; }
    public void setEndTime(LocalTime endTime) { this.endTime = endTime; }
    
    public String getAssignedDoctorId() { return assignedDoctorId; }
    public void setAssignedDoctorId(String assignedDoctorId) { this.assignedDoctorId = assignedDoctorId; }

    @Override
    public String toString() {
        return String.format("%-8s %-15s %-12s %-10s %-8s-%-8s %-12s", 
                           roomId, roomName, roomType, 
                           isAvailable ? "AVAILABLE" : "OCCUPIED",
                           startTime.toString(), endTime.toString(),
                           assignedDoctorId != null ? assignedDoctorId : "NONE");
    }
} 