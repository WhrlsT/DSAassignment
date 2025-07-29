package com.mycompany.dsaassignment.dao;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Queue;
import java.io.*;
import java.util.List;

public class QueueDAO {
    private static final String FILE_PATH = "queue.json";
    private ObjectMapper objectMapper;

    public QueueDAO() {
        objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        objectMapper.enableDefaultTyping(ObjectMapper.DefaultTyping.NON_FINAL);
    }

    public ListADT<Queue> retrieveFromFile() {
        ListADT<Queue> queueList = new ArrayListADT<>();
        
        try {
            File file = new File(FILE_PATH);
            if (file.exists() && file.length() > 0) {
                List<Queue> queues = objectMapper.readValue(
                    file, 
                    new TypeReference<List<Queue>>() {}
                );
                
                for (Queue queue : queues) {
                    queueList.add(queue);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("No existing queue data found. Starting with empty list.");
        } catch (IOException e) {
            System.out.println("Error reading queue data: " + e.getMessage());
        }
        
        return queueList;
    }

    public void saveToFile(ListADT<Queue> queueList) {
        try {
            List<Queue> queues = new java.util.ArrayList<>();
            for (int i = 0; i < queueList.size(); i++) {
                queues.add(queueList.get(i));
            }
            
            objectMapper.writerWithDefaultPrettyPrinter()
                       .writeValue(new File(FILE_PATH), queues);
            
        } catch (IOException e) {
            System.out.println("Error saving queue data: " + e.getMessage());
        }
    }
} 