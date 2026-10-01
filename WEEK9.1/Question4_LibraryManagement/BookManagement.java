import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Question 4: Library Book Management using JDBC and MySQL.
 * Demonstrates:
 * 1. Insert details of a new book.
 * 2. Search for a book using its Book ID.
 * 3. Display all available books.
 * 4. Change availability status of a selected book after it is issued.
 */
public class BookManagement {

    // Default Database Connection Configurations
    private static String dbUrl = "jdbc:mysql://localhost:3306/library_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String dbUser = "root";
    private static String dbPassword = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("       LIBRARY BOOK MANAGEMENT SYSTEM (JDBC)      ");
        System.out.println("==================================================");

        // Optional prompt to customize DB connection or accept defaults
        System.out.print("Use default connection (localhost:3306/library_db, user: root)? (y/n): ");
        String choice = scanner.nextLine().trim();
        if (choice.equalsIgnoreCase("n")) {
            System.out.print("Enter MySQL host & port (default localhost:3306): ");
            String host = scanner.nextLine().trim();
            if (host.isEmpty()) host = "localhost:3306";

            System.out.print("Enter Database Name (default library_db): ");
            String db = scanner.nextLine().trim();
            if (db.isEmpty()) db = "library_db";

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

        // Test connection and auto-create table if needed
        try (Connection testConn = getConnection()) {
            System.out.println("[SUCCESS] Connected to MySQL database successfully.");
            ensureTableExists(testConn);
        } catch (SQLException e) {
            System.err.println("[ERROR] Could not connect to database: " + e.getMessage());
            System.err.println("Tip: Ensure MySQL server is running, the database 'library_db' exists, and the credentials are correct.");
            System.err.println("You can run 'setup_book_db.sql' in MySQL to create the database and table.");
            System.out.println("\nExiting program.");
            return;
        }

        // Main Menu Loop
        while (true) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("                   MAIN MENU                      ");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Insert Details of a New Book");
            System.out.println("2. Search for a Book by Book ID");
            System.out.println("3. Display All Available Books");
            System.out.println("4. Change Availability Status (Issue / Return)");
            System.out.println("5. Display All Books");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    insertBook(scanner);
                    break;
                case "2":
                    searchBookById(scanner);
                    break;
                case "3":
                    displayAvailableBooks();
                    break;
                case "4":
                    changeAvailabilityStatus(scanner);
                    break;
                case "5":
                    displayAllBooks();
                    break;
                case "6":
                    System.out.println("\nThank you for using the Library Book Management System. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please select an option from 1 to 6.");
            }
        }
    }

    /**
     * Establishes and returns a database connection using DriverManager.
     */
    private static Connection getConnection() throws SQLException {
        try {
            // Explicitly load MySQL driver class if present
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
            // Driver may be loaded automatically by JDBC 4.0+ SPI
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    /**
     * Ensures table Book exists in the database.
     */
    private static void ensureTableExists(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS Book (" +
                "BookID INT PRIMARY KEY, " +
                "Title VARCHAR(150) NOT NULL, " +
                "Author VARCHAR(100) NOT NULL, " +
                "Price DOUBLE NOT NULL, " +
                "Availability VARCHAR(20) NOT NULL DEFAULT 'Available')";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Notice: Could not verify/create table automatically: " + e.getMessage());
        }
    }

    /**
     * 1. Insert details of a new book.
     */
    private static void insertBook(Scanner scanner) {
        System.out.println("\n--- 1. Insert New Book Details ---");
        try {
            System.out.print("Enter Book ID (Integer): ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Book Title: ");
            String title = scanner.nextLine().trim();
            if (title.isEmpty()) {
                System.out.println("[ERROR] Title cannot be empty.");
                return;
            }

            System.out.print("Enter Author Name: ");
            String author = scanner.nextLine().trim();
            if (author.isEmpty()) {
                System.out.println("[ERROR] Author cannot be empty.");
                return;
            }

            System.out.print("Enter Price (e.g. 599.50): ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            if (price < 0) {
                System.out.println("[ERROR] Price cannot be negative.");
                return;
            }

            System.out.print("Enter Availability Status (Available/Issued) [Default: Available]: ");
            String availability = scanner.nextLine().trim();
            if (availability.isEmpty()) {
                availability = "Available";
            }

            String sql = "INSERT INTO Book (BookID, Title, Author, Price, Availability) VALUES (?, ?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, bookId);
                pstmt.setString(2, title);
                pstmt.setString(3, author);
                pstmt.setDouble(4, price);
                pstmt.setString(5, availability);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("[SUCCESS] Book inserted successfully into the database!");
                }
            } catch (SQLException e) {
                System.err.println("[DATABASE ERROR] Failed to insert book: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid numeric input. Please enter valid numbers for Book ID and Price.");
        }
    }

    /**
     * 2. Search for a book using its Book ID.
     */
    private static void searchBookById(Scanner scanner) {
        System.out.println("\n--- 2. Search Book by ID ---");
        try {
            System.out.print("Enter Book ID to search: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());

            String sql = "SELECT BookID, Title, Author, Price, Availability FROM Book WHERE BookID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, bookId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("\n[BOOK FOUND]");
                        printTableHeader();
                        printBookRow(rs);
                        printTableFooter();
                    } else {
                        System.out.println("[NOT FOUND] No book found with Book ID: " + bookId);
                    }
                }
            } catch (SQLException e) {
                System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid Book ID. Must be an integer.");
        }
    }

    /**
     * 3. Display all available books.
     */
    private static void displayAvailableBooks() {
        System.out.println("\n--- 3. Display All Available Books ---");
        String sql = "SELECT BookID, Title, Author, Price, Availability FROM Book WHERE LOWER(Availability) = 'available'";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                printBookRow(rs);
            }

            if (found) {
                printTableFooter();
            } else {
                System.out.println("[INFO] No books are currently available.");
            }

        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
        }
    }

    /**
     * 4. Change the availability status of a selected book after it is issued.
     */
    private static void changeAvailabilityStatus(Scanner scanner) {
        System.out.println("\n--- 4. Change Availability Status ---");
        try {
            System.out.print("Enter Book ID: ");
            int bookId = Integer.parseInt(scanner.nextLine().trim());

            // First check if the book exists and its current status
            String selectSql = "SELECT BookID, Title, Availability FROM Book WHERE BookID = ?";
            String currentStatus = null;
            String title = null;

            try (Connection conn = getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(selectSql)) {

                checkStmt.setInt(1, bookId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (rs.next()) {
                        title = rs.getString("Title");
                        currentStatus = rs.getString("Availability");
                    } else {
                        System.out.println("[NOT FOUND] No book exists with Book ID: " + bookId);
                        return;
                    }
                }

                System.out.println("Book Found: '" + title + "' | Current Status: " + currentStatus);
                System.out.println("Select new status:");
                System.out.println("  1. Issued");
                System.out.println("  2. Available");
                System.out.println("  3. Custom Status");
                System.out.print("Enter option (1/2/3): ");
                String opt = scanner.nextLine().trim();

                String newStatus;
                if (opt.equals("1")) {
                    newStatus = "Issued";
                } else if (opt.equals("2")) {
                    newStatus = "Available";
                } else if (opt.equals("3")) {
                    System.out.print("Enter custom status: ");
                    newStatus = scanner.nextLine().trim();
                    if (newStatus.isEmpty()) {
                        System.out.println("[ERROR] Status cannot be empty.");
                        return;
                    }
                } else {
                    System.out.println("[ERROR] Invalid choice.");
                    return;
                }

                String updateSql = "UPDATE Book SET Availability = ? WHERE BookID = ?";
                try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                    updateStmt.setString(1, newStatus);
                    updateStmt.setInt(2, bookId);

                    int rows = updateStmt.executeUpdate();
                    if (rows > 0) {
                        System.out.println("[SUCCESS] Availability status of Book ID " + bookId +
                                " successfully updated from '" + currentStatus + "' to '" + newStatus + "'.");
                    }
                }
            } catch (SQLException e) {
                System.err.println("[DATABASE ERROR] Failed to update availability: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid Book ID. Must be an integer.");
        }
    }

    /**
     * 5. Display all books in the catalog.
     */
    private static void displayAllBooks() {
        System.out.println("\n--- All Books in Library ---");
        String sql = "SELECT BookID, Title, Author, Price, Availability FROM Book ORDER BY BookID";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                printBookRow(rs);
            }

            if (found) {
                printTableFooter();
            } else {
                System.out.println("[INFO] The library catalog is empty.");
            }

        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
        }
    }

    // Helper formatting methods for console tables
    private static void printTableHeader() {
        System.out.println("+--------+---------------------------------------+----------------------+------------+--------------+");
        System.out.printf("| %-6s | %-37s | %-20s | %-10s | %-12s |\n", "BookID", "Title", "Author", "Price (Rs)", "Availability");
        System.out.println("+--------+---------------------------------------+----------------------+------------+--------------+");
    }

    private static void printBookRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("BookID");
        String title = rs.getString("Title");
        if (title.length() > 37) title = title.substring(0, 34) + "...";
        String author = rs.getString("Author");
        if (author.length() > 20) author = author.substring(0, 17) + "...";
        double price = rs.getDouble("Price");
        String availability = rs.getString("Availability");

        System.out.printf("| %-6d | %-37s | %-20s | %10.2f | %-12s |\n", id, title, author, price, availability);
    }

    private static void printTableFooter() {
        System.out.println("+--------+---------------------------------------+----------------------+------------+--------------+");
    }
}
