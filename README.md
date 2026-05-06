# Student Management System

A Java-based Student Management System with GUI using Java Swing and MySQL.

## Features
- Add new students
- View all students
- Update student details
- Delete students
- Search students by name
- Search students by department
- View students sorted by name
- Input validation for all fields
- Beautiful GUI with colored buttons and table

## Technologies Used
- Java (JDK 8)
- Java Swing (GUI)
- MySQL
- JDBC (Java Database Connectivity)
- Maven
- Git & GitHub

## Architecture
Follows DAO (Data Access Object) design pattern:
- `Main.java` - Console UI Layer
- `StudentGUI.java` - GUI Layer
- `StudentDAO.java` - Data Access Layer
- `DBConnection.java` - Database Connection Layer
- `Student.java` - Model Class

## Database Setup
1. Install MySQL
2. Create database:
\```sql
CREATE DATABASE student_db;
\```
3. Create table:
\```sql
CREATE TABLE students (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100),
    age INT,
    email VARCHAR(100),
    department VARCHAR(100)
);
\```


## Screenshots

 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/678ff218-5140-4b8c-9b6f-71f83de23d9d" />
 
 Search By Name:

 
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/d97b794b-52d1-41cf-a766-83afb61fe01e" />

 
 Add Student:
 
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/f4d8667f-123d-41f4-999d-81782c28dacb" />
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/4e39ae05-995e-4e44-a959-fb9f3d47ab20" />

 
 Update Student:
 
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/a3a51323-f709-45e0-b94f-ccdca8a214a8" />
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/aa0ed549-ffdc-4a32-87d4-09d83e05f1da" />


 Delete Student:
 
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/b7faed37-696a-41ee-83ae-0b6a6eea577d" />
 <img width="1920" height="1080" alt="image" src="https://github.com/user-attachments/assets/0e693319-e0c9-4ee8-b253-1950f67a8bdd" />

## How to Run
1. Clone this repository
2. Open in Eclipse as Maven project
3. Update `DBConnection.java` with your MySQL password
4. Run `StudentGUI.java` for GUI version
5. Run `Main.java` for console version

## Connect with Me
- GitHub: github.com/2300040100
