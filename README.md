# Student Management System

A Java-based Student Management System with CRUD operations using MySQL.

## Features
- Add new students
- View all students
- Update student details
- Delete students
- Search students by name

## Technologies Used
- Java (JDK 8)
- MySQL
- JDBC (Java Database Connectivity)
- Maven

## Database Setup
1. Install MySQL
2. Create a database named `student_db`
3. Run this query:
```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    email VARCHAR(100),
    department VARCHAR(100)
);
```

## How to Run
1. Clone this repository
2. Open in Eclipse as Maven project
3. Update `DBConnection.java` with your MySQL username and password
4. Run `Main.java`

## Screenshots
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a8959e04-c842-41f6-bc8d-29bd1a21894f" />
<img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/0ab7fc2e-9af7-4d08-bd19-5e0e0e874298" />
