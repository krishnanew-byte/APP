import java.util.*;
public class student
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String title,author;
        int price;
        System.out.println("enter title of the book");
        title=sc.nextLine();
        System.out.println("enter author of the book");
        author=sc.nextLine();
        System.out.println("enter price of the book");
        price=sc.nextInt();
        System.out.println("The details of the books are");
        System.out.println("Title of the book is " +title);
        System.out.println("The Author of the book" +author);
        System.out.println("The price of the book" +price);
}
}