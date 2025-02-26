package com.studentmanagement.StudentManagementSystem;

public class Student {
    private int id;
    private String name;
    private int age;
    private String department;
    private String email;

    public Student(int id, String name, int age, String department, String email) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.department = department;
        this.email = email;
    }

    public int getId() { 
    	return id; 
    	}
    public String getName() {
    	return name; 
    	}
    public int getAge() {
    	return age; 
    	}
    public String getDepartment() {
    	return department; 
    	}
    public String getEmail() {
    	return email; 
    	}
    @Override
    public String toString() {
        return "ID: " + id + " | Name: " + name + " | Age: " + age +
               " | Email: " + email + " | Department: " + department;
    }

}
