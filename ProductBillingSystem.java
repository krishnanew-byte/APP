import java.util.Scanner;
class Product {
    int id;
    String name;
    double price;
    int quantity;
Product(int i, String n, double p, int q) {
        id = i;
        name = n;
        price = p;
        quantity = q;
    }
}
public class ProductBillingSystem {
    public static void main(String[] args) {
     Scanner sc = new Scanner(System.in);
       Product[] p = new Product[5];
      for (int i = 0; i < 5; i++) {
     System.out.println("Enter Product " + (i + 1) + " details:");
     System.out.print("ID: ");
            int id = sc.nextInt();  
             System.out.print("Name: ");
            String name = sc.next();
      System.out.print("Price: ");
            double price = sc.nextDouble();
 System.out.print("Quantity: ");
            int quantity = sc.nextInt();
p[i] = new Product(id, name, price, quantity);
 System.out.println();
        }
        System.out.println("----- BILL DETAILS -----");
        for (int i = 0; i < 5; i++) {
            double total = p[i].price * p[i].quantity;
            double discount;
            if (total >= 5000) {
                discount = total * 10 / 100;
            } else {
                discount = total * 5 / 100;
            }
            double finalPrice = total - discount;
            System.out.println("Product ID: " + p[i].id);
            System.out.println("Name: " + p[i].name);
            System.out.println("Total Price: Rs." + total);
            System.out.println("Discount: Rs." + discount);
            System.out.println("Final Price: Rs." + finalPrice);
            System.out.println();
        }
        sc.close();
    }
}