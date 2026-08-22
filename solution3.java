abstract class Payment{
abstract void makePayment();
}
class Upipayment extends Payment{
void makePayment(){System.out.println("Paid via UPI");}
}
class CreditCardPayment extends Payment{
void makePayment(){System.out.println("Paid via Credit Card");}
}
class NetBankingPayment extends Payment{
void makePayment(){System.out.println("Paid via Net Banking");}
}
class Main{
public static void main(String[] args){
java.util.Scanner sc=new java.util.Scanner(System.in);
String choice=sc.next();
Payment p=null;
if(choice.equals("upi"))p=new Upipayment();
else if(choice.equals("credit"))p=new CreditCardPayment();
else if(choice.equals("net"))p=new NetBankingPayment();
p.makePayment();
}
}