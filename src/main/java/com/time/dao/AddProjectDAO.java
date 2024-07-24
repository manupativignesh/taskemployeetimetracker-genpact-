package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddProjectDAO {
    // Database URL, username, and password
    private static final String DB_URL = "jdbc:mysql://localhost:3306/employee_tracker";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "1234";

    // SQL query for inserting a new project
    private static final String INSERT_PROJECT_SQL = 
        "INSERT INTO list (project, description, languages, status, manager_name) VALUES (?, ?, ?, ?, ?)";

    // Method to add a project
    public boolean addProject(String project, String description, String languages, String status, String associate) {
        boolean updated = false;

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement ps = conn.prepareStatement(INSERT_PROJECT_SQL)) {

            // Set parameters for the PreparedStatement
            ps.setString(1, project);
            ps.setString(2, description);
            ps.setString(3, languages);
            ps.setString(4, status);
            ps.setString(5, associate);  // Corrected index to 5

            // Execute the update and check if a row was inserted
            int rowsInserted = ps.executeUpdate();
            updated = rowsInserted > 0;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return updated;
    }
}
