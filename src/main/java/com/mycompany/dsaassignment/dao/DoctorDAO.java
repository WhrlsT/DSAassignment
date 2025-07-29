package com.mycompany.dsaassignment.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Doctor;
import java.io.*;
import java.util.List;

public class DoctorDAO {
    private static final String FILE_PATH = "doctors.json";
    private ObjectMapper objectMapper;

    public DoctorDAO() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public ListADT<Doctor> retrieveFromFile() {
        ListADT<Doctor> doctorList = new ArrayListADT<>();
        
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                List<Doctor> doctors = objectMapper.readValue(
                    file, 
                    new TypeReference<List<Doctor>>() {}
                );
                
                for (Doctor doctor : doctors) {
                    doctorList.add(doctor);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing doctor data found. Starting with empty list.");
        } catch (IOException e) {
            System.out.println("Error reading doctor data: " + e.getMessage());
        }
        
        return doctorList;
    }

    public void saveToFile(ListADT<Doctor> doctorList) {
        try {
            List<Doctor> doctors = new java.util.ArrayList<>();
            for (int i = 0; i < doctorList.size(); i++) {
                doctors.add(doctorList.get(i));
            }
            
            objectMapper.writerWithDefaultPrettyPrinter()
                       .writeValue(new File(FILE_PATH), doctors);
            
        } catch (IOException e) {
            System.out.println("Error saving doctor data: " + e.getMessage());
        }
    }
} 