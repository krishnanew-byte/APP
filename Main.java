import student.Student;
import course.Course;

public class Main {
    public static void main(String[] args) {

        Student s = new Student("Krishna", 101);
        Course c = new Course("Java Programming", 501);

        System.out.println("COLLEGE MANAGEMENT SYSTEM");

        s.displayStudent();
        c.displayCourse();
    }
}