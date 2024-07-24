package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.time.model.Project;

public class ViewProjectDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public List<Project> getAllProjects() throws SQLException {
        List<Project> projects = new ArrayList<>();
        String sql = "SELECT * FROM projects"; // Adjust the query to match your table structure
        
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Project project = new Project(
                    rs.getInt("id"),
                    rs.getString("projectName"),
                    rs.getString("projectDescription"),
                    rs.getString("status")
                );
                projects.add(project);
            }
        }
        return projects;
    }

	public List<Project> getProjects() {
		// TODO Auto-generated method stub
		return null;
	}
}
