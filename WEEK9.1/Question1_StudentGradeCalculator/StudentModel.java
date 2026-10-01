/**
 * Model component of the Student Grade Calculator MVC application.
 * Stores student information, marks, and performs result/grade calculations.
 */
public class StudentModel {
    private String studentName;
    private double subject1;
    private double subject2;
    private double subject3;
    private double totalMarks;
    private double averageMarks;
    private String grade;

    public StudentModel() {
        this.studentName = "";
        this.subject1 = 0.0;
        this.subject2 = 0.0;
        this.subject3 = 0.0;
        this.totalMarks = 0.0;
        this.averageMarks = 0.0;
        this.grade = "";
    }

    /**
     * Performs calculations for total, average, and determines the grade based on criteria:
     * Average Marks Grade
     * >= 90           A
     * 75 - 89         B
     * 60 - 74         C
     * 50 - 59         D
     * < 50            F
     */
    public void calculateResult() {
        this.totalMarks = subject1 + subject2 + subject3;
        this.averageMarks = totalMarks / 3.0;

        if (averageMarks >= 90.0) {
            this.grade = "A";
        } else if (averageMarks >= 75.0) {
            this.grade = "B";
        } else if (averageMarks >= 60.0) {
            this.grade = "C";
        } else if (averageMarks >= 50.0) {
            this.grade = "D";
        } else {
            this.grade = "F";
        }
    }

    // Getters and Setters
    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public double getSubject1() {
        return subject1;
    }

    public void setSubject1(double subject1) {
        this.subject1 = subject1;
    }

    public double getSubject2() {
        return subject2;
    }

    public void setSubject2(double subject2) {
        this.subject2 = subject2;
    }

    public double getSubject3() {
        return subject3;
    }

    public void setSubject3(double subject3) {
        this.subject3 = subject3;
    }

    public double getTotalMarks() {
        return totalMarks;
    }

    public double getAverageMarks() {
        return averageMarks;
    }

    public String getGrade() {
        return grade;
    }
}
