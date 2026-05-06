package com.studentmanagement.StudentManagementSystem;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StudentDAO {

    // CREATE Operation - Insert Student
    public static void addStudent(String name, int age, String email, String department) {
        String query = "INSERT INTO students (name, age, email, department) VALUES (?, ?, ?, ?)";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, email);
            pst.setString(4, department);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student added successfully!");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ Operation - Get All Students
    public static List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String query = "SELECT * FROM students";

        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                Student student = new Student(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("age"),
                        rs.getString("email"),
                        rs.getString("department")
                );
                students.add(student);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    // UPDATE Operation - Update Student
    public static void updateStudent(int id, String name, int age, String email, String department) {
        String query = "UPDATE students SET name=?, age=?, email=?, department=? WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setString(1, name);
            pst.setInt(2, age);
            pst.setString(3, email);
            pst.setString(4, department);
            pst.setInt(5, id);

            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student updated successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE Operation - Delete Student
    public static void deleteStudent(int id) {
        String query = "DELETE FROM students WHERE id=?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {

            pst.setInt(1, id);
            int rowsAffected = pst.executeUpdate();
            if (rowsAffected > 0) {
                System.out.println("Student deleted successfully!");
            } else {
                System.out.println("Student not found.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // SEARCH Operation - Search Student by Name
    public static void searchStudent(String name) {
        String query = "SELECT * FROM students WHERE name LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("---------------------------");
            }
            if (!found) {
                System.out.println("No student found with name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    // SEARCH by Department
    public static void searchByDepartment(String department) {
        String query = "SELECT * FROM students WHERE department LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + department + "%");
            ResultSet rs = pst.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("---------------------------");
            }
            if (!found) {
                System.out.println("No students found in department: " + department);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
 // VIEW students sorted by name
    public static void getStudentsSortedByName() {
        String query = "SELECT * FROM students ORDER BY name ASC";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            boolean found = false;
            while (rs.next()) {
                found = true;
                System.out.println("ID: " + rs.getInt("id"));
                System.out.println("Name: " + rs.getString("name"));
                System.out.println("Age: " + rs.getInt("age"));
                System.out.println("Email: " + rs.getString("email"));
                System.out.println("Department: " + rs.getString("department"));
                System.out.println("---------------------------");
            }
            if (!found) {
                System.out.println("No students found.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

