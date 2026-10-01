import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Question 6: Course Registration Query System using JDBC and MySQL.
 * Accepts a Course Code from the user and displays the details of all students
 * registered for that course. If no students are registered, displays an appropriate message.
 *
 * Demonstrates:
 * MySQL connection -> PreparedStatement -> executeQuery() -> ResultSet processing.
 */
public class CourseRegistrationQuery {

    // Default Database Connection Configurations
    private static String dbUrl = "jdbc:mysql://localhost:3306/college_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String dbUser = "root";
    private static String dbPassword = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("     COURSE REGISTRATION QUERY SYSTEM (JDBC)      ");
        System.out.println("==================================================");

        // Connection prompt
        System.out.print("Use default connection (localhost:3306/college_db, user: root)? (y/n): ");
        String choice = scanner.nextLine().trim();
        if (choice.equalsIgnoreCase("n")) {
            System.out.print("Enter MySQL host & port (default localhost:3306): ");
            String host = scanner.nextLine().trim();
            if (host.isEmpty()) host = "localhost:3306";

            System.out.print("Enter Database Name (default college_db): ");
            String db = scanner.nextLine().trim();
            if (db.isEmpty()) db = "college_db";

            System.out.print("Enter MySQL Username (default root): ");
            String u = scanner.nextLine().trim();
            if (!u.isEmpty()) dbUser = u;

            System.out.print("Enter MySQL Password: ");
            dbPassword = scanner.nextLine();

            dbUrl = "jdbc:mysql://" + host + "/" + db + "?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
        } else {
            System.out.print("Enter password for MySQL user 'root' (leave empty if none): ");
            dbPassword = scanner.nextLine();
        }

        // Verify database connection
        try (Connection testConn = getConnection()) {
            System.out.println("[SUCCESS] Connected to MySQL database successfully.");
            ensureTableExists(testConn);
        } catch (SQLException e) {
            System.err.println("[ERROR] Database connection failed: " + e.getMessage());
            System.err.println("Tip: Ensure MySQL server is running, the database 'college_db' exists, and credentials are correct.");
            System.err.println("You can run 'setup_coursereg_db.sql' in MySQL to create the database and table.");
            System.out.println("\nExiting program.");
            return;
        }

        // Query execution loop
        while (true) {
            System.out.println("\n--------------------------------------------------");
            System.out.print("Enter Course Code to search (e.g. CS101, IT202, EC301, AI401) or 'exit' to quit: ");
            String courseCode = scanner.nextLine().trim();

            if (courseCode.equalsIgnoreCase("exit") || courseCode.equalsIgnoreCase("quit")) {
                System.out.println("Exiting Course Registration Query System. Goodbye!");
                break;
            }

            if (courseCode.isEmpty()) {
                System.out.println("[WARNING] Please enter a valid Course Code.");
                continue;
            }

            // Execute the query
            queryStudentsByCourseCode(courseCode);
        }

        scanner.close();
    }

    /**
     * Step 1: Establishes MySQL connection using DriverManager.
     */
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    /**
     * Ensures table CourseRegistration exists.
     */
    private static void ensureTableExists(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS CourseRegistration (" +
                "StudentID VARCHAR(20) NOT NULL, " +
                "StudentName VARCHAR(100) NOT NULL, " +
                "CourseCode VARCHAR(20) NOT NULL, " +
                "CourseName VARCHAR(100) NOT NULL, " +
                "Semester VARCHAR(10) NOT NULL, " +
                "PRIMARY KEY (StudentID, CourseCode))";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Notice: Could not verify/create table automatically: " + e.getMessage());
        }
    }

    /**
     * Core routine testing:
     * MySQL connection -> PreparedStatement -> executeQuery() -> ResultSet processing
     */
    private static void queryStudentsByCourseCode(String courseCode) {
        // Step 2: Prepare the parameterized SQL query
        String sql = "SELECT StudentID, StudentName, CourseCode, CourseName, Semester " +
                     "FROM CourseRegistration " +
                     "WHERE UPPER(CourseCode) = UPPER(?) " +
                     "ORDER BY StudentID ASC";

        // Step 1 & 2: Obtain Connection and PreparedStatement
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            // Bind the Course Code parameter
            pstmt.setString(1, courseCode);

            // Step 3: Execute query and obtain ResultSet
            try (ResultSet rs = pstmt.executeQuery()) {

                // Step 4: ResultSet processing
                boolean foundAny = false;
                int count = 0;
                String matchedCourseName = "";

                while (rs.next()) {
                    if (!foundAny) {
                        foundAny = true;
                        matchedCourseName = rs.getString("CourseName");
                        System.out.println("\n[QUERY RESULTS FOR COURSE: " + courseCode.toUpperCase() + " (" + matchedCourseName + ")]");
                        printTableHeader();
                    }
                    printStudentRow(rs);
                    count++;
                }

                if (foundAny) {
                    printTableFooter();
                    System.out.println("Total students registered for " + courseCode.toUpperCase() + ": " + count);
                } else {
                    // Display appropriate message if no students are registered
                    System.out.println("\n[INFO] No students are registered for the entered Course Code: '" + courseCode + "'.");
                    System.out.println("Please check the Course Code or register students for this course.");
                }
            }

        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
        }
    }

    // Helper formatting methods for console tables
    private static void printTableHeader() {
        System.out.println("+------------+----------------------+-------------+-----------------------------------+------------+");
        System.out.printf("| %-10s | %-20s | %-11s | %-33s | %-10s |\n",
                "Student ID", "Student Name", "Course Code", "Course Name", "Semester");
        System.out.println("+------------+----------------------+-------------+-----------------------------------+------------+");
    }

    private static void printStudentRow(ResultSet rs) throws SQLException {
        String studentId = rs.getString("StudentID");
        String studentName = rs.getString("StudentName");
        if (studentName.length() > 20) studentName = studentName.substring(0, 17) + "...";
        String code = rs.getString("CourseCode");
        String courseName = rs.getString("CourseName");
        if (courseName.length() > 33) courseName = courseName.substring(0, 30) + "...";
        String semester = rs.getString("Semester");

        System.out.printf("| %-10s | %-20s | %-11s | %-33s | %-10s |\n",
                studentId, studentName, code, courseName, semester);
    }

    private static void printTableFooter() {
        System.out.println("+------------+----------------------+-------------+-----------------------------------+------------+");
    }
}
