import java.util.*;

public class check
{
    void validator(String input)
    {
        int value = input.lastIndexOf('.');

        if(value != -1)
        {
            String extension = input.substring(value + 1);

            if(extension.equalsIgnoreCase("pdf") ||
               extension.equalsIgnoreCase("docx") ||
               extension.equalsIgnoreCase("zip"))
            {
                System.out.println("Accepted");
            }
            else
            {
                System.out.println("Rejected — invalid file type");
            }
        }
        else
        {
            System.out.println("Rejected — invalid file type");
        }
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);

        System.out.println("Enter filename:");
        String input = sc.nextLine();

        check obj = new check();
        obj.validator(input);

        sc.close();
    }
}