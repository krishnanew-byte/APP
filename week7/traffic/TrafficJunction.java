package traffic;

public class TrafficJunction extends Thread {
    private String status;

    public TrafficJunction(String name, String status) {
        setName(name);
        this.status = status;
    }

    @Override
    public void run() {
        for (int i = 1; i <= 3; i++) {
            System.out.println(getName() + " - Traffic Status: " + status + " (Iteration " + i + ")");
            try {
                Thread.sleep(1000 * i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        TrafficJunction t1 = new TrafficJunction("JunctionA", "Heavy traffic");
        TrafficJunction t2 = new TrafficJunction("JunctionB", "Moderate traffic");
        TrafficJunction t3 = new TrafficJunction("JunctionC", "Light traffic");

        t1.start();
        t2.start();
        t3.start();
    }
}