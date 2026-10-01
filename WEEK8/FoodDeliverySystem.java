class OrderProcessing extends Thread {

    public OrderProcessing() {
        setName("OrderProcessing");
        setPriority(10);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Thread: " + getName() +
                " | Priority: " + getPriority() +
                " | Activity: Processing customer order"
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class DeliveryTracking extends Thread {

    public DeliveryTracking() {
        setName("DeliveryTracking");
        setPriority(7);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Thread: " + getName() +
                " | Priority: " + getPriority() +
                " | Activity: Tracking delivery location"
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class Notification extends Thread {

    public Notification() {
        setName("Notification");
        setPriority(4);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Thread: " + getName() +
                " | Priority: " + getPriority() +
                " | Activity: Sending order notification"
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class FoodDeliverySystem {

    public static void main(String[] args) {

        OrderProcessing order = new OrderProcessing();
        DeliveryTracking delivery = new DeliveryTracking();
        Notification notification = new Notification();

        order.start();
        delivery.start();
        notification.start();
    }
}