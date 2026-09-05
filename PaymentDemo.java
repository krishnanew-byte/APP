interface Payment {
    void pay(double amount);
}

class CreditCardPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Credit Card");
    }
}

class UPIPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using UPI");
    }
}

class NetBankingPayment implements Payment {
    public void pay(double amount) {
        System.out.println("Paid Rs." + amount + " using Net Banking");
    }
}

public class PaymentDemo {
    public static void main(String[] args) {
        Payment p;

        p = new CreditCardPayment();
        p.pay(5000);

        p = new UPIPayment();
        p.pay(2500);

        p = new NetBankingPayment();
        p.pay(7500);
    }
}