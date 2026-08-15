import java.util.Scanner;

public class Attendance {
        public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[] attendance = new int[7];
        int present = 0;

        for (int i = 0; i < 7; i++) {

            System.out.print(
                "Day " + (i + 1) +
                " - Enter 1 for Present, 0 for Absent: "
            );

            attendance[i] = sc.nextInt();

            if (attendance[i] == 1) {
                present++;
            }
        }

        double percentage = (present / 7.0) * 100;

        System.out.println(
            "Attendance Percentage = " + percentage + "%"
        );

        if (percentage >= 75) {

            System.out.println(
                "Attendance.status = ELIGIBLE Let's gooo 🚀"
            );

        } else {

            System.out.println(
                "Attendance.status = NOT_ELIGIBLE Skill issue 💀"
            );
        }

        sc.close();
    }
}