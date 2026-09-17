package hospitalmgmt.patient;

import hospitalmgmt.doctor.Doctor;

public class Patient {
    private int patientId;
    private String name;
    private String disease;
    private int age;
    private Doctor treatingDoctor;

    public Patient(int patientId, String name, String disease, int age) {
        this.patientId = patientId;
        this.name = name;
        this.disease = disease;
        this.age = age;
        this.treatingDoctor = null;
    }

    public int getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    public String getDisease() {
        return disease;
    }

    public int getAge() {
        return age;
    }

    public Doctor getTreatingDoctor() {
        return treatingDoctor;
    }

    public void setTreatingDoctor(Doctor doctor) {
        this.treatingDoctor = doctor;
    }

    public void displayInfo() {
        System.out.println("Patient ID: " + patientId);
        System.out.println("Name: " + name);
        System.out.println("Disease: " + disease);
        System.out.println("Age: " + age);
    }
}