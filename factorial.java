import java.util.*;
public class factorial
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        int n,i,f=0;
        System.out.println("enter a number");
        n=sc.nextInt();
        for(i=1;i<=n;i++)
        {
            n=n*i;
            f=f+n;
        }
        System.out.println("Factorial is " +f);
    }
}
