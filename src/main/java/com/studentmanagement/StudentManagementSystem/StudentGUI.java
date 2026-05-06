package com.studentmanagement.StudentManagementSystem;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class StudentGUI {
    private JFrame frame;
    private JTable table;
    private DefaultTableModel tableModel;
    private JTextField nameField, ageField, emailField, deptField, searchField, idField;

    public StudentGUI() {
        frame = new JFrame("Student Management System");
        frame.setSize(900, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Title
        JLabel title = new JLabel("Student Management System", JLabel.CENTER);
        title.setFont(new Font("Arial", Font.BOLD, 20));
        title.setBackground(new Color(52, 152, 219));
        title.setOpaque(true);
        title.setForeground(Color.WHITE);
        title.setPreferredSize(new Dimension(900, 50));
        frame.add(title, BorderLayout.NORTH);

        // Input Panel
        JPanel inputPanel = new JPanel(new GridLayout(6, 2, 10, 10));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Student Details"));
        inputPanel.setPreferredSize(new Dimension(300, 250));

        inputPanel.add(new JLabel("  ID (for update/delete):"));
        idField = new JTextField();
        inputPanel.add(idField);

        inputPanel.add(new JLabel("  Name:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        inputPanel.add(new JLabel("  Age:"));
        ageField = new JTextField();
        inputPanel.add(ageField);

        inputPanel.add(new JLabel("  Email:"));
        emailField = new JTextField();
        inputPanel.add(emailField);

        inputPanel.add(new JLabel("  Department:"));
        deptField = new JTextField();
        inputPanel.add(deptField);

        inputPanel.add(new JLabel("  Search (name/dept):"));
        searchField = new JTextField();
        inputPanel.add(searchField);

        // Button Panel
        JPanel buttonPanel = new JPanel(new GridLayout(8, 1, 5, 5));
        buttonPanel.setBorder(BorderFactory.createTitledBorder("Actions"));

        JButton addBtn = new JButton("Add Student");
        JButton viewBtn = new JButton("View All");
        JButton updateBtn = new JButton("Update Student");
        JButton deleteBtn = new JButton("Delete Student");
        JButton searchNameBtn = new JButton("Search by Name");
        JButton searchDeptBtn = new JButton("Search by Dept");
        JButton sortBtn = new JButton("Sort by Name");
        JButton clearBtn = new JButton("Clear Fields");

        // Button Colors
        addBtn.setBackground(new Color(46, 204, 113));
        addBtn.setForeground(Color.WHITE);
        viewBtn.setBackground(new Color(52, 152, 219));
        viewBtn.setForeground(Color.WHITE);
        updateBtn.setBackground(new Color(230, 126, 34));
        updateBtn.setForeground(Color.WHITE);
        deleteBtn.setBackground(new Color(231, 76, 60));
        deleteBtn.setForeground(Color.WHITE);
        searchNameBtn.setBackground(new Color(155, 89, 182));
        searchNameBtn.setForeground(Color.WHITE);
        searchDeptBtn.setBackground(new Color(155, 89, 182));
        searchDeptBtn.setForeground(Color.WHITE);
        sortBtn.setBackground(new Color(52, 73, 94));
        sortBtn.setForeground(Color.WHITE);
        clearBtn.setBackground(new Color(149, 165, 166));
        clearBtn.setForeground(Color.WHITE);

        buttonPanel.add(addBtn);
        buttonPanel.add(viewBtn);
        buttonPanel.add(updateBtn);
        buttonPanel.add(deleteBtn);
        buttonPanel.add(searchNameBtn);
        buttonPanel.add(searchDeptBtn);
        buttonPanel.add(sortBtn);
        buttonPanel.add(clearBtn);

        // Left Panel combining input + buttons
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.add(inputPanel, BorderLayout.CENTER);
        leftPanel.add(buttonPanel, BorderLayout.SOUTH);
        leftPanel.setPreferredSize(new Dimension(300, 600));
        frame.add(leftPanel, BorderLayout.WEST);

        // Table
        String[] columns = {"ID", "Name", "Age", "Email", "Department"};
        tableModel = new DefaultTableModel(columns, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        table.getTableHeader().setBackground(new Color(52, 152, 219));
        table.getTableHeader().setForeground(Color.WHITE);
        table.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Student Records"));
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button Actions
        addBtn.addActionListener(e -> addStudent());
        viewBtn.addActionListener(e -> viewAllStudents());
        updateBtn.addActionListener(e -> updateStudent());
        deleteBtn.addActionListener(e -> deleteStudent());
        searchNameBtn.addActionListener(e -> searchByName());
        searchDeptBtn.addActionListener(e -> searchByDept());
        sortBtn.addActionListener(e -> sortByName());
        clearBtn.addActionListener(e -> clearFields());

        frame.setLocationRelativeTo(null);
        frame.setVisible(true);

        // Load students on startup
        viewAllStudents();
    }

    private void addStudent() {
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String email = emailField.getText().trim();
        String dept = deptField.getText().trim();

        if (name.isEmpty() || ageText.isEmpty() || email.isEmpty() || dept.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (!email.contains("@") || !email.contains(".")) {
            JOptionPane.showMessageDialog(frame, "Invalid email format!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        int age;
        try {
            age = Integer.parseInt(ageText);
            if (age <= 0 || age > 100) {
                JOptionPane.showMessageDialog(frame, "Age must be between 1 and 100!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "Age must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        StudentDAO.addStudent(name, age, email, dept);
        JOptionPane.showMessageDialog(frame, "Student added successfully!");
        clearFields();
        viewAllStudents();
    }

    private void viewAllStudents() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM students";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("department")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void updateStudent() {
        String idText = idField.getText().trim();
        String name = nameField.getText().trim();
        String ageText = ageField.getText().trim();
        String email = emailField.getText().trim();
        String dept = deptField.getText().trim();

        if (idText.isEmpty() || name.isEmpty() || ageText.isEmpty() || email.isEmpty() || dept.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please fill all fields including ID!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            int age = Integer.parseInt(ageText);
            StudentDAO.updateStudent(id, name, age, email, dept);
            JOptionPane.showMessageDialog(frame, "Student updated successfully!");
            clearFields();
            viewAllStudents();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "ID and Age must be numbers!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void deleteStudent() {
        String idText = idField.getText().trim();
        if (idText.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter Student ID to delete!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            int id = Integer.parseInt(idText);
            int confirm = JOptionPane.showConfirmDialog(frame, "Are you sure you want to delete student " + id + "?", "Confirm Delete", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                StudentDAO.deleteStudent(id);
                JOptionPane.showMessageDialog(frame, "Student deleted successfully!");
                clearFields();
                viewAllStudents();
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(frame, "ID must be a number!", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void searchByName() {
        String name = searchField.getText().trim();
        if (name.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter name to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tableModel.setRowCount(0);
        String query = "SELECT * FROM students WHERE name LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + name + "%");
            ResultSet rs = pst.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("department")
                });
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "No student found with name: " + name);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void searchByDept() {
        String dept = searchField.getText().trim();
        if (dept.isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Please enter department to search!", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        tableModel.setRowCount(0);
        String query = "SELECT * FROM students WHERE department LIKE ?";
        try (Connection con = DBConnection.getConnection();
             PreparedStatement pst = con.prepareStatement(query)) {
            pst.setString(1, "%" + dept + "%");
            ResultSet rs = pst.executeQuery();
            boolean found = false;
            while (rs.next()) {
                found = true;
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("department")
                });
            }
            if (!found) {
                JOptionPane.showMessageDialog(frame, "No students found in department: " + dept);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void sortByName() {
        tableModel.setRowCount(0);
        String query = "SELECT * FROM students ORDER BY name ASC";
        try (Connection con = DBConnection.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                tableModel.addRow(new Object[]{
                    rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("age"),
                    rs.getString("email"),
                    rs.getString("department")
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void clearFields() {
        idField.setText("");
        nameField.setText("");
        ageField.setText("");
        emailField.setText("");
        deptField.setText("");
        searchField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(StudentGUI::new);
    }
}