import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 * Question 5: Store Product Information Management using JDBC and MySQL.
 * Demonstrates:
 * 1. Insert a new product.
 * 2. Retrieve a product using Product ID.
 * 3. Update its quantity.
 * 4. Display all products whose quantity is below 10.
 * Uses Connection, PreparedStatement, and ResultSet appropriately.
 */
public class ProductManagement {

    // Default Database Connection Configurations
    private static String dbUrl = "jdbc:mysql://localhost:3306/store_db?useSSL=false&allowPublicKeyRetrieval=true&serverTimezone=UTC";
    private static String dbUser = "root";
    private static String dbPassword = "";

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("==================================================");
        System.out.println("      STORE PRODUCT MANAGEMENT SYSTEM (JDBC)      ");
        System.out.println("==================================================");

        // Connection setup prompt
        System.out.print("Use default connection (localhost:3306/store_db, user: root)? (y/n): ");
        String choice = scanner.nextLine().trim();
        if (choice.equalsIgnoreCase("n")) {
            System.out.print("Enter MySQL host & port (default localhost:3306): ");
            String host = scanner.nextLine().trim();
            if (host.isEmpty()) host = "localhost:3306";

            System.out.print("Enter Database Name (default store_db): ");
            String db = scanner.nextLine().trim();
            if (db.isEmpty()) db = "store_db";

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

        // Test database connection and ensure table exists
        try (Connection testConn = getConnection()) {
            System.out.println("[SUCCESS] Connected to MySQL database successfully.");
            ensureTableExists(testConn);
        } catch (SQLException e) {
            System.err.println("[ERROR] Database connection failed: " + e.getMessage());
            System.err.println("Tip: Ensure MySQL server is running, the database 'store_db' exists, and credentials are correct.");
            System.err.println("You can run 'setup_product_db.sql' in MySQL to create the database and table.");
            System.out.println("\nExiting program.");
            return;
        }

        // Menu loop
        while (true) {
            System.out.println("\n--------------------------------------------------");
            System.out.println("                   MAIN MENU                      ");
            System.out.println("--------------------------------------------------");
            System.out.println("1. Insert a New Product");
            System.out.println("2. Retrieve Product by Product ID");
            System.out.println("3. Update Product Quantity");
            System.out.println("4. Display Products with Quantity Below 10 (Low Stock)");
            System.out.println("5. Display All Products");
            System.out.println("6. Exit");
            System.out.print("Enter your choice (1-6): ");

            String input = scanner.nextLine().trim();
            switch (input) {
                case "1":
                    insertProduct(scanner);
                    break;
                case "2":
                    retrieveProductById(scanner);
                    break;
                case "3":
                    updateProductQuantity(scanner);
                    break;
                case "4":
                    displayLowStockProducts();
                    break;
                case "5":
                    displayAllProducts();
                    break;
                case "6":
                    System.out.println("\nThank you for using Store Product Management. Goodbye!");
                    scanner.close();
                    return;
                default:
                    System.out.println("Invalid choice! Please enter a number from 1 to 6.");
            }
        }
    }

    /**
     * Establishes a Connection using DriverManager.
     */
    private static Connection getConnection() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException ignored) {
        }
        return DriverManager.getConnection(dbUrl, dbUser, dbPassword);
    }

    /**
     * Ensures table Product exists.
     */
    private static void ensureTableExists(Connection conn) {
        String sql = "CREATE TABLE IF NOT EXISTS Product (" +
                "ProductID INT PRIMARY KEY, " +
                "ProductName VARCHAR(100) NOT NULL, " +
                "Price DOUBLE NOT NULL, " +
                "Quantity INT NOT NULL)";
        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Notice: Could not verify/create table automatically: " + e.getMessage());
        }
    }

    /**
     * 1. Insert a new product into the Product table.
     */
    private static void insertProduct(Scanner scanner) {
        System.out.println("\n--- 1. Insert New Product ---");
        try {
            System.out.print("Enter Product ID (Integer): ");
            int productId = Integer.parseInt(scanner.nextLine().trim());

            System.out.print("Enter Product Name: ");
            String name = scanner.nextLine().trim();
            if (name.isEmpty()) {
                System.out.println("[ERROR] Product Name cannot be empty.");
                return;
            }

            System.out.print("Enter Price (e.g. 499.00): ");
            double price = Double.parseDouble(scanner.nextLine().trim());
            if (price < 0) {
                System.out.println("[ERROR] Price cannot be negative.");
                return;
            }

            System.out.print("Enter Quantity in Stock: ");
            int quantity = Integer.parseInt(scanner.nextLine().trim());
            if (quantity < 0) {
                System.out.println("[ERROR] Quantity cannot be negative.");
                return;
            }

            String sql = "INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES (?, ?, ?, ?)";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, productId);
                pstmt.setString(2, name);
                pstmt.setDouble(3, price);
                pstmt.setInt(4, quantity);

                int rowsAffected = pstmt.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("[SUCCESS] Product '" + name + "' added successfully!");
                }
            } catch (SQLException e) {
                System.err.println("[DATABASE ERROR] Failed to insert product: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid numeric input. Please enter valid numbers.");
        }
    }

    /**
     * 2. Retrieve a product using its Product ID.
     */
    private static void retrieveProductById(Scanner scanner) {
        System.out.println("\n--- 2. Retrieve Product by Product ID ---");
        try {
            System.out.print("Enter Product ID to search: ");
            int productId = Integer.parseInt(scanner.nextLine().trim());

            String sql = "SELECT ProductID, ProductName, Price, Quantity FROM Product WHERE ProductID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement pstmt = conn.prepareStatement(sql)) {

                pstmt.setInt(1, productId);
                try (ResultSet rs = pstmt.executeQuery()) {
                    if (rs.next()) {
                        System.out.println("\n[PRODUCT FOUND]");
                        printTableHeader();
                        printProductRow(rs);
                        printTableFooter();
                    } else {
                        System.out.println("[NOT FOUND] No product found with Product ID: " + productId);
                    }
                }
            } catch (SQLException e) {
                System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid Product ID. Must be an integer.");
        }
    }

    /**
     * 3. Update product quantity.
     */
    private static void updateProductQuantity(Scanner scanner) {
        System.out.println("\n--- 3. Update Product Quantity ---");
        try {
            System.out.print("Enter Product ID: ");
            int productId = Integer.parseInt(scanner.nextLine().trim());

            // Check if product exists
            String checkSql = "SELECT ProductID, ProductName, Quantity FROM Product WHERE ProductID = ?";
            try (Connection conn = getConnection();
                 PreparedStatement checkStmt = conn.prepareStatement(checkSql)) {

                checkStmt.setInt(1, productId);
                try (ResultSet rs = checkStmt.executeQuery()) {
                    if (!rs.next()) {
                        System.out.println("[NOT FOUND] No product exists with Product ID: " + productId);
                        return;
                    }

                    String name = rs.getString("ProductName");
                    int currentQty = rs.getInt("Quantity");

                    System.out.println("Product: '" + name + "' | Current Quantity: " + currentQty);
                    System.out.print("Enter New Quantity: ");
                    int newQty = Integer.parseInt(scanner.nextLine().trim());

                    if (newQty < 0) {
                        System.out.println("[ERROR] Quantity cannot be negative.");
                        return;
                    }

                    String updateSql = "UPDATE Product SET Quantity = ? WHERE ProductID = ?";
                    try (PreparedStatement updateStmt = conn.prepareStatement(updateSql)) {
                        updateStmt.setInt(1, newQty);
                        updateStmt.setInt(2, productId);

                        int rows = updateStmt.executeUpdate();
                        if (rows > 0) {
                            System.out.println("[SUCCESS] Quantity for Product ID " + productId +
                                    " ('" + name + "') updated from " + currentQty + " to " + newQty + ".");
                        }
                    }
                }
            } catch (SQLException e) {
                System.err.println("[DATABASE ERROR] Failed to update product quantity: " + e.getMessage());
            }

        } catch (NumberFormatException e) {
            System.out.println("[ERROR] Invalid numeric input. Please enter valid integer numbers.");
        }
    }

    /**
     * 4. Display all products whose quantity is below 10.
     */
    private static void displayLowStockProducts() {
        System.out.println("\n--- 4. Products with Quantity Below 10 (Low Stock Alert) ---");
        String sql = "SELECT ProductID, ProductName, Price, Quantity FROM Product WHERE Quantity < 10 ORDER BY Quantity ASC";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                printProductRow(rs);
            }

            if (found) {
                printTableFooter();
                System.out.println("[ALERT] The above items are running low on stock and need reordering!");
            } else {
                System.out.println("[OK] No products currently have stock below 10 units.");
            }

        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
        }
    }

    /**
     * 5. Display all products in the catalog.
     */
    private static void displayAllProducts() {
        System.out.println("\n--- All Products in Inventory ---");
        String sql = "SELECT ProductID, ProductName, Price, Quantity FROM Product ORDER BY ProductID";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            boolean found = false;
            while (rs.next()) {
                if (!found) {
                    printTableHeader();
                    found = true;
                }
                printProductRow(rs);
            }

            if (found) {
                printTableFooter();
            } else {
                System.out.println("[INFO] Product inventory is empty.");
            }

        } catch (SQLException e) {
            System.err.println("[DATABASE ERROR] Query execution failed: " + e.getMessage());
        }
    }

    // Helper formatting methods for console tables
    private static void printTableHeader() {
        System.out.println("+-----------+-----------------------------------+------------+----------+");
        System.out.printf("| %-9s | %-33s | %-10s | %-8s |\n", "ProductID", "Product Name", "Price (Rs)", "Quantity");
        System.out.println("+-----------+-----------------------------------+------------+----------+");
    }

    private static void printProductRow(ResultSet rs) throws SQLException {
        int id = rs.getInt("ProductID");
        String name = rs.getString("ProductName");
        if (name.length() > 33) name = name.substring(0, 30) + "...";
        double price = rs.getDouble("Price");
        int qty = rs.getInt("Quantity");

        System.out.printf("| %-9d | %-33s | %10.2f | %8d |\n", id, name, price, qty);
    }

    private static void printTableFooter() {
        System.out.println("+-----------+-----------------------------------+------------+----------+");
    }
}
