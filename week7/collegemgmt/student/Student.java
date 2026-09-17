package collegemgmt.student;

public class Student {
    private int studentId;
    private String name;
    private String email;
    private double gpa;

    public Student(int studentId, String name, String email, double gpa) {
        this.studentId = studentId;
        this.name = name;
        this.email = email;
        this.gpa = gpa;
    }

    public void displayInfo() {
        System.out.println("Student ID: " + studentId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("GPA: " + gpa);
    }
}