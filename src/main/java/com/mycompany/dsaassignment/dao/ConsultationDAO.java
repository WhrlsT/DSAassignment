package com.mycompany.dsaassignment.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Consultation;
import java.io.*;
import java.util.List;

public class ConsultationDAO {
    private static final String FILE_PATH = "consultations.json";
    private ObjectMapper objectMapper;

    public ConsultationDAO() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public ListADT<Consultation> retrieveFromFile() {
        ListADT<Consultation> consultationList = new ArrayListADT<>();
        
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                List<Consultation> consultations = objectMapper.readValue(
                    file, 
                    new TypeReference<List<Consultation>>() {}
                );
                
                for (Consultation consultation : consultations) {
                    consultationList.add(consultation);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing consultation data found. Starting with empty list.");
        } catch (IOException e) {
            System.out.println("Error reading consultation data: " + e.getMessage());
        }
        
        return consultationList;
    }

    public void saveToFile(ListADT<Consultation> consultationList) {
        try {
            // Convert ListADT to regular List for Jackson
            List<Consultation> consultations = new java.util.ArrayList<>();
            for (int i = 0; i < consultationList.size(); i++) {
                consultations.add(consultationList.get(i));
            }
            
            // Write to file with pretty formatting
            objectMapper.writerWithDefaultPrettyPrinter()
                       .writeValue(new File(FILE_PATH), consultations);
            
        } catch (IOException e) {
            System.out.println("Error saving consultation data: " + e.getMessage());
        }
    }
} 