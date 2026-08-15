import java.util.*;
public class Rectangle
{
    float length, breadth, area;
    void getDetails()
    {
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter the length of the rectangle:");
        length = sc.nextFloat();
        System.out.println("Enter the breadth of the rectangle:");
        breadth = sc.nextFloat();
    }
    void calculate()
    {
        area = length * breadth;
    }
    void display()
    {
        System.out.println("The area of the rectangle is " + area);
    }
    public static void main(String args[])
    {
        Rectangle obj = new Rectangle();
        obj.getDetails();
        obj.calculate();
        obj.display();    }
}