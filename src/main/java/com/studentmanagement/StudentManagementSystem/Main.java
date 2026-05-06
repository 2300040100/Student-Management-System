package com.studentmanagement.StudentManagementSystem;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int choice;

        do {
            System.out.println("---- STUDENT MANAGEMENT SYSTEM -----");
            System.out.println("1. Add Student");
            System.out.println("2. View All Students");
            System.out.println("3. Update Student");
            System.out.println("4. Delete Student");
            System.out.println("5. Exit");
            System.out.println("6. Search Student");
            System.out.println("7. Search by Department");
            System.out.println("8. View Students Sorted by Name");
            System.out.print("Enter your choice: ");

            choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
            case 1:
                System.out.print("Enter Name: ");
                String name = scanner.nextLine();
                if (name.trim().isEmpty()) {
                    System.out.println("Error: Name cannot be empty!");
                    break;
                }

                System.out.print("Enter Age: ");
                int age;
                try {
                    age = Integer.parseInt(scanner.nextLine().trim());
                    if (age <= 0 || age > 100) {
                        System.out.println("Error: Age must be between 1 and 100!");
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Error: Age must be a number!");
                    break;
                }

                System.out.print("Enter Email: ");
                String email = scanner.nextLine();
                if (!email.contains("@") || !email.contains(".")) {
                    System.out.println("Error: Invalid email format!");
                    break;
                }

                System.out.print("Enter Department: ");
                String department = scanner.nextLine();
                if (department.trim().isEmpty()) {
                    System.out.println("Error: Department cannot be empty!");
                    break;
                }

                StudentDAO.addStudent(name, age, email, department);
                break;

                case 2:
                    List<Student> students = StudentDAO.getAllStudents();
                    if (students.isEmpty()) {
                        System.out.println("No students found.");
                    } else {
                        for (Student s : students) {
                            System.out.println(s);
                        }
                    }
                    break;

                case 3:
                    System.out.print("Enter Student ID to Update: ");
                    int updateId;
                    try {
                        updateId = Integer.parseInt(scanner.nextLine().trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Error: ID must be a number!");
                        break;
                    }

                    System.out.print("Enter New Name: ");
                    String newName = scanner.nextLine();
                    if (newName.trim().isEmpty()) {
                        System.out.println("Error: Name cannot be empty!");
                        break;
                    }

                    System.out.print("Enter New Age: ");
                    int newAge;
                    try {
                        newAge = Integer.parseInt(scanner.nextLine().trim());
                        if (newAge <= 0 || newAge > 100) {
                            System.out.println("Error: Age must be between 1 and 100!");
                            break;
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Error: Age must be a number!");
                        break;
                    }

                    System.out.print("Enter New Email: ");
                    String newEmail = scanner.nextLine();
                    if (!newEmail.contains("@") || !newEmail.contains(".")) {
                        System.out.println("Error: Invalid email format!");
                        break;
                    }

                    System.out.print("Enter New Department: ");
                    String newDepartment = scanner.nextLine();
                    if (newDepartment.trim().isEmpty()) {
                        System.out.println("Error: Department cannot be empty!");
                        break;
                    }

                    StudentDAO.updateStudent(updateId, newName, newAge, newEmail, newDepartment);
                    break;

                case 4:
                    System.out.print("Enter Student ID to Delete: ");
                    int deleteId = scanner.nextInt();
                    StudentDAO.deleteStudent(deleteId);
                    break;

                case 5:
                    System.out.println("Exiting...");
                    break;
                
                case 6:
                    System.out.print("Enter Name to Search: ");
                    String searchName = scanner.next();
                    StudentDAO.searchStudent(searchName);
                    break;
                 
                case 7:
                    System.out.print("Enter Department to Search: ");
                    String searchDept = scanner.nextLine();
                    if (searchDept.trim().isEmpty()) {
                        System.out.println("Error: Department cannot be empty!");
                        break;
                    }
                    StudentDAO.searchByDepartment(searchDept);
                    break;
                   
                case 8:
                    System.out.println("Students sorted by name:");
                    StudentDAO.getStudentsSortedByName();
                    break;

                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        } while (choice != 7);

        scanner.close();
    }
    
}
