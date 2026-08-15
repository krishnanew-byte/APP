import java.util.*;
public class Employee
{
    String name;
    int empId;
    double salary;
    void getDetails()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter Employee Name:");
        name = sc.nextLine();
        System.out.println("Enter Employee ID:");
        empId = sc.nextInt();
        System.out.println("Enter Salary:");
        salary = sc.nextDouble();
        sc.nextLine(); 
    }
    void displayDetails()
    {
        System.out.println("Employee Name : " + name);
        System.out.println("Employee ID   : " + empId);
        System.out.println("Salary        : " + salary);
    }
    public static void main(String args[])
    {
        Employee emp1 = new Employee();
        Employee emp2 = new Employee();
        System.out.println("Enter details of Employee ");
        emp1.getDetails();
        emp2.getDetails();
        emp1.displayDetails();
        emp2.displayDetails();
    }
}