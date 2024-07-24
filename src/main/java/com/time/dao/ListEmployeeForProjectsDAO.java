package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.time.model.ListEmployeeForProjects;

public class ListEmployeeForProjectsDAO {

    private static final String URL = "jdbc:mysql://localhost:3306/employee_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    public List<ListEmployeeForProjects> list() throws SQLException {
        List<ListEmployeeForProjects> employees = new ArrayList<>();
        String sql = "SELECT * FROM employee_projects"; // Adjust the query to match your table structure

        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                ListEmployeeForProjects employee = new ListEmployeeForProjects(
                    rs.getInt("id"),
                    rs.getString("employeeName"),
                    rs.getString("projectName"),
                    rs.getString("role"),
                    rs.getString("status")
                );
                employees.add(employee);
            }
        }
        return employees;
    }
}
