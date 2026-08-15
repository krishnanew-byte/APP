import java.util.Scanner;
class Employee {
    int employeeId;
    String employeeName;
    double salary;
    Employee(int id, String name, double sal) {
        employeeId = id;
        employeeName = name;
        salary = sal;
    }
    void display() {
        System.out.println("Employee ID : " + employeeId);
        System.out.println("Employee Name : " + employeeName);
        System.out.println("Salary : " + salary);
    }
}
public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Employee ID : ");
        int id = sc.nextInt();
        sc.nextLine();
        System.out.print("Employee Name : ");
        String name = sc.nextLine();
        System.out.print("Salary : ");
        double salary = sc.nextDouble();
        Employee emp = new Employee(id, name, salary);
        emp.display();
        sc.close();
    }
}