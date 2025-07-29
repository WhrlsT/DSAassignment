package com.mycompany.dsaassignment.control;

import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Patient;
import com.mycompany.dsaassignment.entity.Queue;
import com.mycompany.dsaassignment.boundary.PatientUI;
import com.mycompany.dsaassignment.boundary.QueueUI;
import com.mycompany.dsaassignment.dao.PatientDAO;
import com.mycompany.dsaassignment.dao.QueueDAO;
import java.time.LocalDateTime;

public class PatientManagement {
    private ListADT<Patient> patientList = new ArrayListADT<>();
    private ListADT<Queue> queueList = new ArrayListADT<>();
    private PatientDAO patientDAO = new PatientDAO();
    private QueueDAO queueDAO = new QueueDAO();
    private PatientUI patientUI = new PatientUI();
    private QueueUI queueUI = new QueueUI();

    public PatientManagement() {
        patientList = patientDAO.retrieveFromFile();
        queueList = queueDAO.retrieveFromFile();
    }

    public void runPatientManagement() {
        int choice = 0;
        do {
            choice = patientUI.getMenuChoice();
            switch(choice) {
                case 0:
                    patientUI.displayMessage("Returning to main menu.");
                    break;
                case 1:
                    addNewPatient();
                    break;
                case 2:
                    viewAllPatients();
                    break;
                case 3:
                    updatePatient();
                    break;
                case 4:
                    deletePatient();
                    break;
                case 5:
                    manageQueue();
                    break;
                default:
                    patientUI.displayMessage("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void addNewPatient() {
        Patient newPatient = patientUI.inputPatientDetails();
        patientList.add(newPatient);
        patientDAO.saveToFile(patientList);
        patientUI.displayMessage("Patient added successfully.");
    }

    public void viewAllPatients() {
        String outputStr = getAllPatients();
        patientUI.displayPatientsTable(outputStr);
    }

    public String getAllPatients() {
        String outputStr = "";
        for (int i = 0; i < patientList.size(); i++) {
            outputStr += patientList.get(i) + "\n";
        }
        return outputStr;
    }

    public void updatePatient() {
        String patientId = patientUI.inputPatientId();
        Patient patient = findPatientById(patientId);
        
        if (patient != null) {
            patientUI.displayMessage("Current patient details:");
            patientUI.displayMessage(patient.toString());
            
            // Update fields
            patient.setName(patientUI.inputPatientName());
            patient.setIcOrPassport(patientUI.inputIcOrPassport());
            patient.setContact(patientUI.inputContact());
            patient.setEmail(patientUI.inputEmail());
            
            patientDAO.saveToFile(patientList);
            patientUI.displayMessage("Patient updated successfully.");
        } else {
            patientUI.displayMessage("Patient not found.");
        }
    }

    public void deletePatient() {
        String patientId = patientUI.inputPatientId();
        Patient patient = findPatientById(patientId);
        
        if (patient != null) {
            patientList.remove(patient);
            patientDAO.saveToFile(patientList);
            patientUI.displayMessage("Patient deleted successfully.");
        } else {
            patientUI.displayMessage("Patient not found.");
        }
    }

    public void manageQueue() {
        int choice = 0;
        do {
            choice = queueUI.getQueueMenuChoice();
            switch(choice) {
                case 0:
                    queueUI.displayMessage("Returning to patient menu.");
                    break;
                case 1:
                    addToQueue();
                    break;
                case 2:
                    viewQueue();
                    break;
                case 3:
                    updateQueueStatus();
                    break;
                case 4:
                    removeFromQueue();
                    break;
                case 5:
                    editQueueArrangement();
                    break;
                default:
                    queueUI.displayMessage("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void addToQueue() {
        Queue newQueue = queueUI.inputQueueDetails();
        queueList.add(newQueue);
        queueDAO.saveToFile(queueList);
        queueUI.displayMessage("Patient added to queue successfully.");
    }

    public void viewQueue() {
        String outputStr = getAllQueues();
        queueUI.displayQueueTable(outputStr);
    }

    public String getAllQueues() {
        String outputStr = "";
        for (int i = 0; i < queueList.size(); i++) {
            outputStr += queueList.get(i) + "\n";
        }
        return outputStr;
    }

    public void updateQueueStatus() {
        String queueId = queueUI.inputQueueId();
        Queue queue = findQueueById(queueId);
        
        if (queue != null) {
            queue.setStatus(queueUI.inputQueueStatus());
            queueDAO.saveToFile(queueList);
            queueUI.displayMessage("Queue status updated successfully.");
        } else {
            queueUI.displayMessage("Queue not found.");
        }
    }

    public void removeFromQueue() {
        String queueId = queueUI.inputQueueId();
        Queue queue = findQueueById(queueId);
        
        if (queue != null) {
            queueList.remove(queue);
            queueDAO.saveToFile(queueList);
            queueUI.displayMessage("Patient removed from queue successfully.");
        } else {
            queueUI.displayMessage("Queue not found.");
        }
    }

    public void editQueueArrangement() {
        queueUI.displayMessage("Current queue arrangement:");
        viewQueue();
        
        String queueId = queueUI.inputQueueId();
        Queue queue = findQueueById(queueId);
        
        if (queue != null) {
            int newNumber = queueUI.inputQueueNumber();
            queue.setQueueNumber(newNumber);
            queueDAO.saveToFile(queueList);
            queueUI.displayMessage("Queue arrangement updated successfully.");
        } else {
            queueUI.displayMessage("Queue not found.");
        }
    }

    private Patient findPatientById(String patientId) {
        for (int i = 0; i < patientList.size(); i++) {
            Patient patient = patientList.get(i);
            if (patient.getPatientId().equals(patientId)) {
                return patient;
            }
        }
        return null;
    }

    private Queue findQueueById(String queueId) {
        for (int i = 0; i < queueList.size(); i++) {
            Queue queue = queueList.get(i);
            if (queue.getQueueId().equals(queueId)) {
                return queue;
            }
        }
        return null;
    }
} 