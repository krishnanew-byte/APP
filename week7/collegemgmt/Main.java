package collegemgmt;

import collegemgmt.student.Student;
import collegemgmt.course.Course;

public class Main {
    public static void main(String[] args) {
        Student s1 = new Student(101, "Alice", "alice@example.com", 3.8);
        Course c1 = new Course(201, "Mathematics", 3);

        System.out.println("=== Student Information ===");
        s1.displayInfo();
        System.out.println("\n=== Course Information ===");
        c1.displayInfo();
    }
}