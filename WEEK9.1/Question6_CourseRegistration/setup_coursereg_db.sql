-- SQL Setup Script for Question 6: Course Registration
-- Database: college_db

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

-- Insert Sample Registrations
INSERT INTO CourseRegistration (StudentID, StudentName, CourseCode, CourseName, Semester) VALUES
('STU101', 'Rahul Sharma', 'CS101', 'Data Structures & Algorithms', 'Semester 3'),
('STU102', 'Priya Patel', 'CS101', 'Data Structures & Algorithms', 'Semester 3'),
('STU103', 'Ananya Roy', 'CS101', 'Data Structures & Algorithms', 'Semester 3'),
('STU104', 'Vikram Singh', 'IT202', 'Web Application Development', 'Semester 4'),
('STU105', 'Sneha Nair', 'IT202', 'Web Application Development', 'Semester 4'),
('STU106', 'Rohan Gupta', 'AI401', 'Artificial Intelligence & ML', 'Semester 6'),
('STU107', 'Kavita Joshi', 'EC301', 'Digital Signal Processing', 'Semester 5');

-- Verify data
SELECT * FROM CourseRegistration;
