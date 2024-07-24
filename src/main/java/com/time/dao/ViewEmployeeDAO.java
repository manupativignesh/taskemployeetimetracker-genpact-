package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.time.model.Employee; // Ensure correct package

public class ViewEmployeeDAO {

    public List<Employee> getEmployee() throws SQLException {
        List<Employee> employees = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_tracker", "root", "1234");

            String sql = "SELECT * FROM employees"; // Adjust if necessary
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                String role = rs.getString("role");
                // Add other fields as necessary
                Employee employee = new Employee(id, name, role);
                employees.add(employee);
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            // Handle exception for missing JDBC driver
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (ps != null) try { ps.close(); } catch (SQLException e) { e.printStackTrace(); }
            if (conn != null) try { conn.close(); } catch (SQLException e) { e.printStackTrace(); }
        }

        return employees;
    }
}
