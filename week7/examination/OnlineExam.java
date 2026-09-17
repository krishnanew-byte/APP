package examination;

public class OnlineExam implements Runnable {
    private String activity;
    private int interval;

    public OnlineExam(String activity, int interval) {
        this.activity = activity;
        this.interval = interval;
    }

    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            System.out.println(Thread.currentThread().getName() + " - " + activity);
            try {
                Thread.sleep(interval * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        OnlineExam task1 = new OnlineExam("Displaying remaining time", 1);
        OnlineExam task2 = new OnlineExam("Auto-saving answers", 2);
        OnlineExam task3 = new OnlineExam("Checking network connection", 3);

        Thread thread1 = new Thread(task1);
        thread1.setName("Thread-A");

        Thread thread2 = new Thread(task2);
        thread2.setName("Thread-B");

        Thread thread3 = new Thread(task3);
        thread3.setName("Thread-C");

        thread1.start();
        thread2.start();
        thread3.start();
    }
}