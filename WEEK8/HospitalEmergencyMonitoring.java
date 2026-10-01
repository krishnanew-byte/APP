class EmergencyAlert extends Thread {

    public EmergencyAlert() {
        setName("EmergencyAlert");
        setPriority(Thread.MAX_PRIORITY);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Thread: " + getName() +
                " | Priority: " + getPriority() +
                " | Activity: Handling critical patient alert"
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class VitalMonitor extends Thread {

    public VitalMonitor() {
        setName("VitalMonitor");
        setPriority(Thread.NORM_PRIORITY);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Thread: " + getName() +
                " | Priority: " + getPriority() +
                " | Activity: Monitoring vital signs"
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

class ReportGenerator extends Thread {

    public ReportGenerator() {
        setName("ReportGenerator");
        setPriority(Thread.MIN_PRIORITY);
    }

    public void run() {
        for (int i = 1; i <= 5; i++) {
            System.out.println(
                "Thread: " + getName() +
                " | Priority: " + getPriority() +
                " | Activity: Generating routine report"
            );

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }
}

public class HospitalEmergencyMonitoring {

    public static void main(String[] args) {

        EmergencyAlert emergency = new EmergencyAlert();
        VitalMonitor vital = new VitalMonitor();
        ReportGenerator report = new ReportGenerator();

        emergency.start();
        vital.start();
        report.start();
    }
}