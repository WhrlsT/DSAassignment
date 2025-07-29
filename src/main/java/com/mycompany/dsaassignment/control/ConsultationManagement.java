package com.mycompany.dsaassignment.control;

import com.mycompany.dsaassignment.adt.ListADT;
import com.mycompany.dsaassignment.adt.ArrayListADT;
import com.mycompany.dsaassignment.entity.Consultation;
import com.mycompany.dsaassignment.boundary.ConsultationUI;
import com.mycompany.dsaassignment.dao.ConsultationDAO;
import java.time.LocalDateTime;

public class ConsultationManagement {
    private ListADT<Consultation> consultationList = new ArrayListADT<>();
    private ConsultationDAO consultationDAO = new ConsultationDAO();
    private ConsultationUI consultationUI = new ConsultationUI();

    public ConsultationManagement() {
        consultationList = consultationDAO.retrieveFromFile();
    }

    public void runConsultationManagement() {
        int choice = 0;
        do {
            choice = consultationUI.getMenuChoice();
            switch(choice) {
                case 0:
                    consultationUI.displayMessage("Returning to main menu.");
                    break;
                case 1:
                    addNewConsultation();
                    break;
                case 2:
                    viewAllConsultations();
                    break;
                case 3:
                    updateConsultation();
                    break;
                case 4:
                    deleteConsultation();
                    break;
                case 5:
                    scheduleFollowUp();
                    break;
                default:
                    consultationUI.displayMessage("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public void addNewConsultation() {
        Consultation newConsultation = consultationUI.inputConsultationDetails();
        consultationList.add(newConsultation);
        consultationDAO.saveToFile(consultationList);
        consultationUI.displayMessage("Consultation added successfully.");
    }

    public void viewAllConsultations() {
        String outputStr = getAllConsultations();
        consultationUI.displayConsultationsTable(outputStr);
    }

    public String getAllConsultations() {
        String outputStr = "";
        for (int i = 0; i < consultationList.size(); i++) {
            outputStr += consultationList.get(i) + "\n";
        }
        return outputStr;
    }

    public void updateConsultation() {
        String consultationId = consultationUI.inputConsultationId();
        Consultation consultation = findConsultationById(consultationId);
        
        if (consultation != null) {
            consultationUI.displayMessage("Current consultation details:");
            consultationUI.displayMessage(consultation.toString());
            
            // Update fields
            consultation.setDiagnosis(consultationUI.inputDiagnosis());
            consultation.setTreatment(consultationUI.inputTreatment());
            consultation.setNotes(consultationUI.inputNotes());
            consultation.setStatus(consultationUI.inputStatus());
            
            consultationDAO.saveToFile(consultationList);
            consultationUI.displayMessage("Consultation updated successfully.");
        } else {
            consultationUI.displayMessage("Consultation not found.");
        }
    }

    public void deleteConsultation() {
        String consultationId = consultationUI.inputConsultationId();
        Consultation consultation = findConsultationById(consultationId);
        
        if (consultation != null) {
            consultationList.remove(consultation);
            consultationDAO.saveToFile(consultationList);
            consultationUI.displayMessage("Consultation deleted successfully.");
        } else {
            consultationUI.displayMessage("Consultation not found.");
        }
    }

    public void scheduleFollowUp() {
        String consultationId = consultationUI.inputConsultationId();
        Consultation consultation = findConsultationById(consultationId);
        
        if (consultation != null) {
            consultationUI.displayMessage("Scheduling follow-up for consultation: " + consultationId);
            LocalDateTime followUpDateTime = consultationUI.inputDateTime();
            consultation.setFollowUpDateTime(followUpDateTime);
            consultation.setRequiresFollowUp(true);
            consultation.setFollowUpReason("Follow-up appointment scheduled");
            
            consultationDAO.saveToFile(consultationList);
            consultationUI.displayMessage("Follow-up appointment scheduled successfully.");
        } else {
            consultationUI.displayMessage("Consultation not found.");
        }
    }

    private Consultation findConsultationById(String consultationId) {
        for (int i = 0; i < consultationList.size(); i++) {
            Consultation consultation = consultationList.get(i);
            if (consultation.getConsultationId().equals(consultationId)) {
                return consultation;
            }
        }
        return null;
    }
} 