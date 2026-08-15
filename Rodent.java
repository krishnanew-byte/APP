import java.util.*;
public class Rodent
{
    String name;
    int age;
    void getDetails()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("enter name and age");
        name=sc.nextLine();
        age=sc.nextInt();
    }
    void displayDetails()
    {
        System.out.println("Name is " +name);
        System.out.println("Age is " +age);
    }
    public static void main(String args[])
    {
     Rodent rod = new Rodent();
     Rodent obj = new Rodent();
     rod.getDetails();
     rod.displayDetails();
     obj.getDetails();
     obj.displayDetails();
    }
}

    