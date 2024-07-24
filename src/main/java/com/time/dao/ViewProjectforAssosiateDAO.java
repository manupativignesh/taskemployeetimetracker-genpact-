package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.time.model.Project; // Ensure the correct package name

public class ViewProjectforAssosiateDAO {

    public List<Project> viewAssosiate(String managerName) throws SQLException {
        List<Project> projects = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_tracker", "root", "1234");

            String sql = "SELECT * FROM projects_list WHERE manager_name = ?";
            ps = conn.prepareStatement(sql);
            ps.setString(1, managerName);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String projectName = rs.getString("project_name");
                String projectDescription = rs.getString("description");
                String languages = rs.getString("languages");
                String status = rs.getString("status");

                Project project = new Project(id, projectName, projectDescription, languages, status);
                projects.add(project);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exception for missing JDBC driver
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return projects;
    }
}
