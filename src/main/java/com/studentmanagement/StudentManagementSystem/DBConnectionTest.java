package com.studentmanagement.StudentManagementSystem;

import java.sql.Connection;

public class DBConnectionTest {
	public static void main(String[] args) {
        Connection con = DBConnection.getConnection();
        if (con != null) {
            System.out.println("Connection successful!");
        } else {
            System.out.println("Failed to connect to database.");
        }
    }

}
