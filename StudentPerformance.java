import java.util.*;
import java.util.Scanner;
class Student {
    int roll;
    String name;
    int[] marks = new int[3];
    double attendance;
    Student(int r, String n, int m1, int m2, int m3, double a) {
        roll = r;
        name = n;
        marks[0] = m1;
        marks[1] = m2;
        marks[2] = m3;
        attendance = a;
    }
}
public class StudentPerformance {
    public static void main(String[] args) {
    Scanner sc = new Scanner(System.in);
    Student[] s = new Student[5];
        for (int i = 0; i < 5; i++) {
            System.out.println("Enter details of Student " + (i + 1));
            System.out.print("Roll Number: ");
            int roll = sc.nextInt();
            System.out.print("Name: ");
            String name = sc.next();
            System.out.println("Enter marks in 3 subjects:");
            int m1 = sc.nextInt();
            int m2 = sc.nextInt();
            int m3 = sc.nextInt();
            System.out.print("Attendance: ");
            double attendance = sc.nextDouble();
            s[i] = new Student(roll, name, m1, m2, m3, attendance);
            System.out.println();
        }
        double highestAverage = 0;
        String highestStudent = "";
        System.out.println("----- STUDENT PERFORMANCE -----");
        for (int i = 0; i < 5; i++) {
            int total = 0;
            for (int j = 0; j < 3; j++) {
                total = total + s[i].marks[j];
            }
            double average = total / 3.0;
            String result = (average >= 50) ? "Pass" : "Fail";
            String scholarship =
                    (average >= 75 && s[i].attendance >= 80)
                    ? "Eligible" : "Not Eligible";
            String performance =
                    (average >= 85) ? "Excellent" : "Good";
            System.out.println("Roll Number: " + s[i].roll);
            System.out.println("Name: " + s[i].name);
            System.out.println("Total Marks: " + total);
            System.out.println("Average Marks: " + average);
            System.out.println("Result: " + result);
            System.out.println("Scholarship: " + scholarship);
            System.out.println("Performance: " + performance);
            System.out.println("-----------------------------");
                  if (average > highestAverage) {
                highestAverage = average;
                highestStudent = s[i].name;
            }
        }
        System.out.println("Student with Highest Average:");
        System.out.println("Name: " + highestStudent);
        System.out.println("Average: " + highestAverage);
        sc.close();
    }
}