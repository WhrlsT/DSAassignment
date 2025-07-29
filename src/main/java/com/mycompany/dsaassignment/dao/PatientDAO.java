package com.mycompany.dsaassignment.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Patient;
import java.io.*;
import java.util.List;

public class PatientDAO {
    private static final String FILE_PATH = "patients.json";
    private ObjectMapper objectMapper;

    public PatientDAO() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public ListADT<Patient> retrieveFromFile() {
        ListADT<Patient> patientList = new ArrayListADT<>();
        
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                List<Patient> patients = objectMapper.readValue(
                    file, 
                    new TypeReference<List<Patient>>() {}
                );
                
                for (Patient patient : patients) {
                    patientList.add(patient);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing patient data found. Starting with empty list.");
        } catch (IOException e) {
            System.out.println("Error reading patient data: " + e.getMessage());
        }
        
        return patientList;
    }

    public void saveToFile(ListADT<Patient> patientList) {
        try {
            List<Patient> patients = new java.util.ArrayList<>();
            for (int i = 0; i < patientList.size(); i++) {
                patients.add(patientList.get(i));
            }
            
            objectMapper.writerWithDefaultPrettyPrinter()
                       .writeValue(new File(FILE_PATH), patients);
            
        } catch (IOException e) {
            System.out.println("Error saving patient data: " + e.getMessage());
        }
    }
} 