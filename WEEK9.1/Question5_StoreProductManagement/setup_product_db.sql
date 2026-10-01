-- SQL Setup Script for Question 5: Store Product Information
-- Database: store_db

CREATE DATABASE IF NOT EXISTS store_db;
USE store_db;

DROP TABLE IF EXISTS Product;

CREATE TABLE Product (
    ProductID INT PRIMARY KEY,
    ProductName VARCHAR(100) NOT NULL,
    Price DOUBLE NOT NULL,
    Quantity INT NOT NULL
);

-- Insert Sample Products
INSERT INTO Product (ProductID, ProductName, Price, Quantity) VALUES
(201, 'Wireless Optical Mouse', 650.00, 25),
(202, 'Mechanical Gaming Keyboard', 2800.00, 5),   -- Low stock (< 10)
(203, 'USB-C Fast Charging Cable', 299.00, 42),
(204, 'Portable SSD 1TB', 6499.00, 8),            -- Low stock (< 10)
(205, 'Bluetooth Over-Ear Headphones', 1999.00, 3),-- Low stock (< 10)
(206, 'Ergonomic Laptop Stand', 1250.00, 15);

-- Verify data
SELECT * FROM Product;
