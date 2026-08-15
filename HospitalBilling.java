import java.util.Scanner;
class Patient{
String patientName;
double consultationFee;

Patient(String patientName,double consultationFee){
this.patientName=patientName;
this.consultationFee=consultationFee;
}

double calculateFinalAmount(double fee){
double discount;
if(fee>=2000)
discount=fee*0.10;
else
discount=fee*0.05;
return fee-discount;
}

double calculateDiscount(double fee){
if(fee>=2000)
return fee*0.10;
else
return fee*0.05;
}

void display(){
double discount=calculateDiscount(consultationFee);
double finalAmount=calculateFinalAmount(consultationFee);
System.out.println("Patient Name: "+patientName);
System.out.println("Original Fee: ₹"+consultationFee);
System.out.println("Discount: ₹"+discount);
System.out.println("Final Amount: ₹"+finalAmount);
System.out.println("--------------------");
}
}

public class HospitalBilling{
public static void main(String[] args){
Scanner sc=new Scanner(System.in);
Patient[] patients=new Patient[5];

for(int i=0;i<5;i++){
System.out.print("Enter Patient Name: ");
String name=sc.nextLine();
System.out.print("Enter Consultation Fee: ");
double fee=sc.nextDouble();
sc.nextLine();
patients[i]=new Patient(name,fee);
}

System.out.println("\nHOSPITAL BILLING DETAILS");
for(int i=0;i<5;i++)
patients[i].display();

sc.close();
}
}