-- SQL Setup Script for Question 4: Library Book Management
-- Database: library_db

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

-- Insert Sample Books
INSERT INTO Book (BookID, Title, Author, Price, Availability) VALUES
(101, 'Introduction to Java Programming', 'Y. Daniel Liang', 750.00, 'Available'),
(102, 'Effective Java', 'Joshua Bloch', 890.50, 'Available'),
(103, 'Clean Code', 'Robert C. Martin', 620.00, 'Issued'),
(104, 'Design Patterns: Elements of Reusable Object-Oriented Software', 'Erich Gamma', 950.00, 'Available'),
(105, 'Database System Concepts', 'Abraham Silberschatz', 820.00, 'Issued');

-- Verify data
SELECT * FROM Book;
