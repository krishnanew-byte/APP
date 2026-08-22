abstract class Product{
int id;
String name;
double price;
Product(int i,String n,double p){id=i;name=n;price=p;}
double getPrice(){return price;}
}
class Electronics extends Product{
Electronics(int i,String n,double p){super(i,n,p);}
double calculateDiscount(){return price*0.1;}
}
class Clothing extends Product{
Clothing(int i,String n,double p){super(i,n,p);}
double calculateDiscount(){return price*0.2;}
}
class Books extends Product{
Books(int i,String n,double p){super(i,n,p);}
double calculateDiscount(){return price*0.05;}
}
class Main{
public static void main(String[] args){
Product p=new Electronics(1,"Phone",1000);
double finalPrice=p.getPrice()-p.calculateDiscount();
System.out.println(finalPrice);
}
}