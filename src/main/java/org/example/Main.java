package org.example;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Display All Students");

        try {
            // This line establishes a connection to the database
            Connection connection= DriverManager.getConnection("jdbc:mysql://localhost:3306/tradb","user","pass");
            // This line creates a statement object
            Statement statement = connection.createStatement();
            // This line executes a SELECT query on the "student" table
            statement.execute("SELECT * from student;");
            // This line retrieves the result set of the query
            ResultSet rs = statement.getResultSet();
            // This list will store the student objects
            List<Student> studentList = new ArrayList<>();
            // This loop iterates over the result set and creates Student objects
            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getNString("name");
                String email = rs.getNString("email");
                Student student = new Student(id, name, email);
                studentList.add(student);
            }
            // Now you can use the studentList as needed, for example:
            for (Student student : studentList) {
                System.out.println("ID: "+ student.getId() + " , Name: " + student.getName() + ", Email: " + student.getEmail());
            }
        } catch (SQLException e) {
            // This line throws a runtime exception if an error occurs
            throw new RuntimeException(e);
        }
    }
}
