import java.util.*;
public class even
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n;
        System.out.println("enter a number");
        n=sc.nextInt();
        if(n%2==0)
            System.out.println("number is even");
        else
            System.out.println("number is odd");
    }
}