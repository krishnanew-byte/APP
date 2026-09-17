package hospitalmgmt;

import hospitalmgmt.doctor.Doctor;
import hospitalmgmt.patient.Patient;

public class Main {
    public static void main(String[] args) {
        Doctor d1 = new Doctor(1, "Dr. Smith", "Cardiologist", 200.0);
        Doctor d2 = new Doctor(2, "Dr. Jones", "Neurologist", 250.0);

        Patient p1 = new Patient(101, "John", "Heart disease", 30);
        Patient p2 = new Patient(102, "Mary", "Brain tumor", 45);
        Patient p3 = new Patient(103, "Bob", "Migraine", 28);

        if (p1.getDisease().contains("Heart") || p1.getDisease().contains("Card")) {
            p1.setTreatingDoctor(d1);
        }
        if (p2.getDisease().contains("Brain") || p2.getDisease().contains("Neuro")) {
            p2.setTreatingDoctor(d2);
        }
        if (p3.getDisease().contains("Migraine") || p3.getDisease().contains("Neuro")) {
            p3.setTreatingDoctor(d2);
        }

        System.out.println("=== Patient Details ===");
        System.out.println("Patient 1:");
        p1.displayInfo();
        System.out.println("Treating Doctor:");
        if (p1.getTreatingDoctor() != null) {
            p1.getTreatingDoctor().displayInfo();
        }
        System.out.println();

        System.out.println("Patient 2:");
        p2.displayInfo();
        System.out.println("Treating Doctor:");
        if (p2.getTreatingDoctor() != null) {
            p2.getTreatingDoctor().displayInfo();
        }
        System.out.println();

        System.out.println("Patient 3:");
        p3.displayInfo();
        System.out.println("Treating Doctor:");
        if (p3.getTreatingDoctor() != null) {
            p3.getTreatingDoctor().displayInfo();
        }
        System.out.println();

        System.out.println("=== Total Consultation Fees ===");
        double totalD1 = 0, totalD2 = 0;
        int countD1 = 0, countD2 = 0;

        if (p1.getTreatingDoctor() == d1) {
            totalD1 += d1.getConsultationFee();
            countD1++;
        }
        if (p2.getTreatingDoctor() == d2) {
            totalD2 += d2.getConsultationFee();
            countD2++;
        }
        if (p3.getTreatingDoctor() == d2) {
            totalD2 += d2.getConsultationFee();
            countD2++;
        }

        System.out.println("Dr. Smith (Cardiologist): Total fee collected = " + totalD1 + " (" + countD1 + " patient(s))");
        System.out.println("Dr. Jones (Neurologist): Total fee collected = " + totalD2 + " (" + countD2 + " patient(s))");
    }
}