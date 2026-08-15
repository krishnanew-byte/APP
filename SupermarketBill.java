import java.util.Scanner;

public class SupermarketBill {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        double[] prices = new double[5];
        double total = 0;

        for (int i = 0; i < 5; i++) {
            System.out.print("Enter price of product " + (i + 1) + ": ");

            prices[i] = sc.nextDouble();

            total = total + prices[i];
        }

        System.out.println("Total Bill = ₹" + total);

        if (total > 5000) {
            System.out.println("10% Discount Applicable");
            total = total -(0.10*total);
            System.out.println("Total amount is "+total);
        } else { 
            System.out.println("No Discount");
        }

        sc.close();
    }
}