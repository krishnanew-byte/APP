class BankAccount{
private double balance;
String accountNumber;
String holderName;
BankAccount(String acct,String name,double bal){
accountNumber=acct;
holderName=name;
balance=bal;
}
void deposit(double amt){balance+=amt;}
void withdraw(double amt){if(amt<=balance)balance-=amt;}
void display(){System.out.println(accountNumber+" "+holderName+" "+balance);}
}