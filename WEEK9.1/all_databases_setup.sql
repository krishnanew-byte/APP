-- =====================================================================
-- WEEK 9.1 LAB EXERCISES: COMPREHENSIVE MYSQL SETUP SCRIPT
-- Contains database and table creation with sample data for:
-- Question 4: Library Book Management (library_db.Book)
-- Question 5: Store Product Information (store_db.Product)
-- Question 6: Course Registration (college_db.CourseRegistration)
-- =====================================================================

-- ---------------------------------------------------------------------
-- QUESTION 4: LIBRARY MANAGEMENT
-- ---------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS library_db;
USE library_db;

DROP TABLE IF EXISTS Book;
CREATE TABLE Book (
    BookID INT PRIMARY KEY,
    Title VARCHAR(150) NOT NULL,
    Author VARCHAR(100) NOT NULL,
    Price DOUBLE NOT NULL,
    Availability VARCHAR(20) NOT NULL DEFAULT 'Available'
);

INSERT INTO Book (BookID, Title, Author, Price, Availability) VALUES
(101, 'Introduction to Java Programming', 'Y. Daniel Liang', 750.00, 'Available'),
(102, 'Effective Java', 'Joshua Bloch', 890.50, 'Available'),
(103, 'Clean Code', 'Robert C. Martin', 620.00, 'Issued'),
(104, 'Design Patterns', 'Erich Gamma', 950.00, 'Available'),
(105, 'Database System Concepts', 'Abraham Silberschatz', 820.00, 'Issued');

-- ---------------------------------------------------------------------
-- QUESTION 5: STORE PRODUCT INFORMATION
-- ---------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS store_db;
USE store_db;

DROP TABLE IF EXISTS Product;
CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Price DOUBLE NOT NULL,
    Quantity INT NOT NULL
);

INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES
(201, 'Wireless Optical Mouse', 650.00, 25),
(202, 'Mechanical Gaming Keyboard', 2800.00, 5),   -- Quantity < 10
(203, 'USB-C Fast Charging Cable', 299.00, 42),
(204, 'Portable SSD 1TB', 6499.00, 8),            -- Quantity < 10
(205, 'Bluetooth Over-Ear Headphones', 1999.00, 3),-- Quantity < 10
(206, 'Ergonomic Laptop Stand', 1250.00, 15);

-- ---------------------------------------------------------------------
-- QUESTION 6: COURSE REGISTRATION
-- ---------------------------------------------------------------------
CREATE DATABASE IF NOT EXISTS college_db;
USE college_db;

DROP TABLE IF EXISTS CourseRegistration;
CREATE TABLE CourseRegistration (
    StudentID VARCHAR(20) NOT NULL,
    StudentName VARCHAR(100) NOT NULL,
    CourseCode VARCHAR(20) NOT NULL,
    CourseName VARCHAR(100) NOT NULL,
    Semester VARCHAR(10) NOT NULL,
    PRIMARY KEY (StudentID, CourseCode)
);

INSERT INTO CourseRegistration (StudentID, StudentName, CourseCode, CourseName, Semester) VALUES
('STU101', 'Rahul Sharma', 'CS101', 'Data Structures & Algorithms', 'Semester 3'),
('STU102', 'Priya Patel', 'CS101', 'Data Structures & Algorithms', 'Semester 3'),
('STU103', 'Ananya Roy', 'CS101', 'Data Structures & Algorithms', 'Semester 3'),
('STU104', 'Vikram Singh', 'IT202', 'Web Application Development', 'Semester 4'),
('STU105', 'Sneha Nair', 'IT202', 'Web Application Development', 'Semester 4'),
('STU106', 'Rohan Gupta', 'AI401', 'Artificial Intelligence & ML', 'Semester 6'),
('STU107', 'Kavita Joshi', 'EC301', 'Digital Signal Processing', 'Semester 5');

-- =====================================================================
-- Setup Completed Successfully!
-- =====================================================================
