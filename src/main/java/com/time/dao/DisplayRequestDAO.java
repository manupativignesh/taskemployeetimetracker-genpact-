package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.time.model.Request;

public class DisplayRequestDAO {
    // Database connection parameters
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/employee_tracker";
    private static final String JDBC_USER = "root";
    private static final String JDBC_PASSWORD = "1234";
    
    public List<Request> getAllRequests() throws SQLException {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        List<Request> requestList = new ArrayList<>();
        
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            
            // Establish a connection to the database
            conn = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
            
            // SQL query to fetch all requests
            String sql = "SELECT * FROM requests";
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            // Process the result set and create Request objects
            while (rs.next()) {
                String project = rs.getString("project");
                String employeeName = rs.getString("employee_name");
                String employeeId = rs.getString("employee_id");
                String skills = rs.getString("skills");
                
                Request request = new Request(project, employeeName, employeeId, skills);
                requestList.add(request);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new SQLException("Error retrieving requests", e);
        } finally {
            // Close resources in the reverse order of their creation
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return requestList;
    }
}
