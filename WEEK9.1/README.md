# Advanced Programming Practice (APP) - Week 9.1 Lab Solutions

This repository contains complete, production-grade solutions for all 6 problems assigned in **Week 9.1**, strictly adhering to the specified architectures (**MVC** for Swing applications and **JDBC / MySQL** for database applications).

---

## Directory Structure

```text
WEEK9.1/
├── Question1_StudentGradeCalculator/       # Question 1: Java Swing MVC
│   ├── StudentModel.java                   # Model: calculates total, average, and assigns grade
│   ├── StudentView.java                    # View: Swing UI (JFrame, JTextField, JButton, etc.)
│   ├── StudentController.java              # Controller: handles events and input validation
│   └── StudentGradeCalculator.java         # Driver class containing main()
│
├── Question2_VehicleServiceCost/           # Question 2: Java Swing MVC
│   ├── VehicleServiceCostModel.java        # Model: pricing & service cost calculations
│   ├── VehicleServiceCostView.java         # View: Swing UI with JRadioButton, JCheckBox, etc.
│   ├── VehicleServiceCostController.java   # Controller: selection processing and updates
│   └── VehicleServiceApp.java              # Driver class containing main()
│
├── Question3_EmployeeManagementPortal/     # Question 3: Java Swing MVC
│   ├── EmployeePortalModel.java            # Model: authentication & employee directory
│   ├── EmployeePortalView.java             # View: Login & Main window with JMenuBar, CardLayout
│   ├── EmployeePortalController.java       # Controller: login validation & menu navigation
│   └── EmployeeManagementApp.java          # Driver class containing main()
│
├── Question4_LibraryManagement/            # Question 4: Java JDBC & MySQL
│   ├── BookManagement.java                 # Interactive JDBC program (Insert, Search, Display, Update)
│   └── setup_book_db.sql                   # Database setup script for library_db.Book
│
├── Question5_StoreProductManagement/       # Question 5: Java JDBC & MySQL
│   ├── ProductManagement.java              # JDBC program (Insert, Retrieve, Update, Low Stock < 10)
│   └── setup_product_db.sql                # Database setup script for store_db.Product
│
├── Question6_CourseRegistration/           # Question 6: Java JDBC & MySQL
│   ├── CourseRegistrationQuery.java        # JDBC program: PreparedStatement & ResultSet query
│   └── setup_coursereg_db.sql              # Database setup script for college_db.CourseRegistration
│
├── all_databases_setup.sql                 # Combined SQL script to initialize Q4, Q5, and Q6 databases
└── README.md                               # Complete documentation and execution guide
```

---

## Detailed Question Overview & Features

### 1. Student Grade Calculator (Swing MVC)
- **Problem Statement**: Input Student Name and marks in 3 subjects. On clicking **Calculate Result**, compute total marks, average marks, and assign a grade:
  - Average $\ge 90$: **A**
  - $75 - 89$: **B**
  - $60 - 74$: **C**
  - $50 - 59$: **D**
  - $< 50$: **F**
- **Architecture**:
  - `StudentModel`: Stores student state, computes total and average, determines grade according to criteria.
  - `StudentView`: Builds Swing UI (`JFrame`, `JTextField`, `JButton`, `JLabel`) with styled panels.
  - `StudentController`: Validates mark boundaries ($0 \le \text{marks} \le 100$) and coordinates communication between Model and View.
  - `StudentGradeCalculator`: Driver launcher.

### 2. Vehicle Service Cost Estimator (Swing MVC)
- **Problem Statement**: Automobile service cost estimator. Inputs include Vehicle Registration Number, Vehicle Type (**Two Wheeler** or **Car**), and service checkboxes:
  - General Service: ₹1,000
  - Oil Change: ₹800
  - Brake Service: ₹1,200
  - Battery Check: ₹500
- **Architecture**:
  - `VehicleServiceCostModel`: Manages service pricing and sums selected service costs.
  - `VehicleServiceCostView`: Uses `JFrame`, `JTextField`, `JRadioButton`, `ButtonGroup`, `JCheckBox`, `JButton`, and `JLabel` with detailed summary breakdown.
  - `VehicleServiceCostController`: Processes checkbox selections and updates the View.
  - `VehicleServiceApp`: Driver launcher.

### 3. Employee Management Portal (Swing MVC)
- **Problem Statement**:
  - Initial **Login Window** with `Username` and `Password` (`JPasswordField`). Valid credentials: `admin` / `admin123`. Validates and displays messages using `JOptionPane`.
  - On successful login, displays **Main Window** with `JMenuBar`:
    - **Employee**: `Add Employee`, `View Employee`
    - **Tools**: `Change Password`
    - **Exit**: `Logout`, `Exit Application`
  - When **Add Employee** is selected: inputs for Employee ID, Employee Name, Department.
  - When **Change Password** is selected: inputs for Old Password, New Password, Confirm Password with match validation.
- **Architecture**:
  - `EmployeePortalModel`: Manages admin credentials, password updates, and employee records list.
  - `EmployeePortalView`: Provides Login Frame, Main Window with MenuBar, and clean CardLayout forms.
  - `EmployeePortalController`: Manages login routing, employee registration, table updates, and password changes.
  - `EmployeeManagementApp`: Driver launcher.

### 4. Library Book Management (JDBC & MySQL)
- **Table**: `Book (BookID, Title, Author, Price, Availability)`
- **Operations**:
  1. Insert details of a new book.
  2. Search for a book using its Book ID.
  3. Display all available books.
  4. Change availability status of a selected book after it is issued.
  5. Display all books in catalog.
- **Implementation**: `BookManagement.java` using `DriverManager`, `PreparedStatement`, and `ResultSet`.

### 5. Store Product Information (JDBC & MySQL)
- **Table**: `Product (ProductID, ProductName, Price, Quantity)`
- **Operations**:
  1. Insert a new product.
  2. Retrieve a product using Product ID.
  3. Update its quantity.
  4. Display all products whose quantity is below 10 (Low Stock alert).
  5. Display all products.
- **Implementation**: `ProductManagement.java` using `Connection`, `PreparedStatement`, and `ResultSet`.

### 6. Course Registration Query (JDBC & MySQL)
- **Table**: `CourseRegistration (StudentID, StudentName, CourseCode, CourseName, Semester)`
- **Operations**:
  - Accepts a `Course Code` (e.g. `CS101`, `IT202`) from the user.
  - Displays details of all students registered for that course.
  - If no students are registered, displays an informative message.
- **Implementation**: `CourseRegistrationQuery.java` specifically demonstrating:
  $$\text{MySQL Connection} \longrightarrow \text{PreparedStatement} \longrightarrow \text{executeQuery()} \longrightarrow \text{ResultSet Processing}$$

---

## Database Setup Instructions (Questions 4, 5, 6)

You can set up all three databases in one step using the MySQL Command Line Client or MySQL Workbench:

```bash
# In MySQL command line:
mysql -u root -p < all_databases_setup.sql
```

Or execute individual SQL files:
- Question 4: `Question4_LibraryManagement/setup_book_db.sql`
- Question 5: `Question5_StoreProductManagement/setup_product_db.sql`
- Question 6: `Question6_CourseRegistration/setup_coursereg_db.sql`

---

## How to Compile and Run

### Running Swing Applications (Questions 1, 2, 3)

#### Question 1:
```bash
cd Question1_StudentGradeCalculator
javac *.java
java StudentGradeCalculator
```

#### Question 2:
```bash
cd Question2_VehicleServiceCost
javac *.java
java VehicleServiceApp
```

#### Question 3:
```bash
cd Question3_EmployeeManagementPortal
javac *.java
java EmployeeManagementApp
```

---

### Running JDBC Applications (Questions 4, 5, 6)

When executing JDBC programs, include the MySQL Connector JAR in the classpath (`-cp`).

*Example with MySQL connector in current directory or system path:*

#### Question 4:
```bash
cd Question4_LibraryManagement
javac BookManagement.java
java -cp ".;mysql-connector-j-8.3.0.jar" BookManagement
```

#### Question 5:
```bash
cd Question5_StoreProductManagement
javac ProductManagement.java
java -cp ".;mysql-connector-j-8.3.0.jar" ProductManagement
```

#### Question 6:
```bash
cd Question6_CourseRegistration
javac CourseRegistrationQuery.java
java -cp ".;mysql-connector-j-8.3.0.jar" CourseRegistrationQuery
```
