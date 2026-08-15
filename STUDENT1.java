import java.util.*;

public class STUDENT1 {
    String name;
    int rollno;
 void getinfo() {
        Scanner sc = new Scanner(System.in);
            System.out.println("Enter name:");
        name = sc.nextLine();
 System.out.println("Enter roll number:");
        rollno = sc.nextInt();
    }
 void display() {
        System.out.println("Name of the student is: " + name);
        System.out.println("Roll number of the student is: " + rollno);
    }public static void main(String args[]) {
        STUDENT1 obj = new STUDENT1();
      obj.getinfo();
        obj.display();
    }
}