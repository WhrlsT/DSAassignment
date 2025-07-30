package com.mycompany.dsaassignment.control;

import com.mycompany.dsaassignment.boundary.ClinicMaintenanceUI;

public class ClinicMaintenance {
    private ClinicMaintenanceUI ui = new ClinicMaintenanceUI();
    private PatientManagement patientManagement = new PatientManagement();
    private DoctorManagement doctorManagement = new DoctorManagement();
    private ConsultationManagement consultationManagement = new ConsultationManagement();

    public void runClinicMaintenance() {
        ui.printWelcome();
        int choice;
        do {
            choice = ui.getMenuChoice();
            switch (choice) {
                case 1:
                    System.out.println("Patient Management selected.");
                    patientManagement.runPatientManagement();
                    break;
                case 2:
                    System.out.println("Doctor Management selected.");
                    doctorManagement.runDoctorManagement();
                    break;
                case 3:
                    System.out.println("Consultation Management selected.");
                    consultationManagement.runConsultationManagement();
                    break;
                case 4:
                    System.out.println("Medical Treatment Management selected.");
                    // TODO: Implement Medical Treatment Management
                    break;
                case 5:
                    System.out.println("Pharmacy Management selected.");
                    // TODO: Implement Pharmacy Management
                    break;
                case 0:
                    System.out.println("Exiting system. Goodbye!");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 0);
    }

    public static void main(String[] args) {
        ClinicMaintenance clinicMaintenance = new ClinicMaintenance();
        clinicMaintenance.runClinicMaintenance();
    }
}