abstract class Employee{
String name;
int id;
double salary;
Employee(String n,int i){
name=n;
id=i;
}
double calculateSalary(){return salary;}
void display(){System.out.println(name+" "+id+" "+calculateSalary());}
}
class Professor extends Employee{
Professor(String n,int i,double s){super(n,i);salary=s;}
double calculateSalary(){return salary;}
}
class LabAssistant extends Employee{
LabAssistant(String n,int i,double s){super(n,i);salary=s;}
double calculateSalary(){return salary;}
}
class AdministrativeStaff extends Employee{
AdministrativeStaff(String n,int i,double s){super(n,i);salary=s;}
double calculateSalary(){return salary;}
}