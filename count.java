import java.util.*;

public class count
{
    void vccount(String text)
    {
        String upper_text = text.toUpperCase();
        int l = upper_text.length();
        int vowel_count = 0, c_count = 0;

        for(int i = 0; i < l; i++)
        {
            char ch = upper_text.charAt(i);

            if(ch == 'A' || ch == 'E' || ch == 'I' || ch == 'O' || ch == 'U')
            {
                vowel_count++;
            }
            else
            {
                c_count++;
            }
        }

        System.out.println("Number of vowels is " + vowel_count);
        System.out.println("Number of consonants is " + c_count);
    }

    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        String text;

        System.out.println("Enter a string");
        text = sc.nextLine();

        count obj = new count();
        obj.vccount(text);
    }
}