class Area
{
double calculate(double side)
{
return side*side;
}
double calculate(double length,double width)
{
return length*width;
}
double calculate(float radius)
{
return 3.14*radius*radius;
}
public static void main(String[] args)
{
Area a=new Area();
System.out.println("Area of Square : "+a.calculate(5.0));
System.out.println("Area of Rectangle : "+a.calculate(10.0,5.0));
System.out.println("Area of Circle : "+a.calculate(7.0f));
}
}