abstract class Shape{
abstract double calculateArea();
}
class Circle extends Shape{
double r;
Circle(double radius){r=radius;}
double calculateArea(){return Math.PI*r*r;}
}
class Rectangle extends Shape{
double l,w;
Rectangle(double length,double width){l=length;w=width;}
double calculateArea(){return l*w;}
}
class Triangle extends Shape{
double b,h;
Triangle(double base,double height){b=base;h=height;}
double calculateArea(){return 0.5*b*h;}
}
class Main{
public static void main(String[] args){
java.util.Scanner sc=new java.util.Scanner(System.in);
String choice=sc.next();
Shape s=null;
if(choice.equals("circle")){
double r=sc.nextDouble();
s=new Circle(r);
}else if(choice.equals("rectangle")){
double l=sc.nextDouble();
double w=sc.nextDouble();
s=new Rectangle(l,w);
}else if(choice.equals("triangle")){
double b=sc.nextDouble();
double h=sc.nextDouble();
s=new Triangle(b,h);
}
System.out.println(s.calculateArea());
}
}