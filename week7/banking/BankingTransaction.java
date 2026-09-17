package banking;

public class BankingTransaction implements Runnable {
    private String activity;
    private int delay;

    public BankingTransaction(String activity, int delay) {
        this.activity = activity;
        this.delay = delay;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + activity + " (Attempt " + i + ")");
            try {
                Thread.sleep(delay);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        BankingTransaction t1 = new BankingTransaction("Transaction Processing", 500);
        BankingTransaction t2 = new BankingTransaction("Balance Updating", 800);
        BankingTransaction t3 = new BankingTransaction("SMS Notification", 1200);

        Thread thread1 = new Thread(t1);
        thread1.setName("TxThread");

        Thread thread2 = new Thread(t2);
        thread2.setName("BalanceThread");

        Thread thread3 = new Thread(t3);
        thread3.setName("SMSThread");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}