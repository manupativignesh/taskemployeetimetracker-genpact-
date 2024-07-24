package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.time.model.AssignedWork;

public class ViewEmployeeAssignDAO {

    public List<AssignedWork> retrieve() throws Exception {
        List<AssignedWork> assignedWorks = new ArrayList<>();
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_tracker", "root", "1234");
            String sql = "SELECT * FROM assigned_work"; // Update with your table name
            ps = conn.prepareStatement(sql);
            rs = ps.executeQuery();
            
            while (rs.next()) {
                AssignedWork work = new AssignedWork(
                    rs.getString("project_id"),
                    rs.getString("employee_id"),
                    rs.getString("employee_name"),
                    rs.getString("role"),
                    rs.getString("project"),
                    rs.getDate("task_date").toLocalDate(),
                    rs.getString("start_time"),
                    rs.getString("end_time"),
                    rs.getBigDecimal("duration"),
                    rs.getString("task_category"),
                    rs.getString("description")
                );
                assignedWorks.add(work);
            }
        } finally {
            if (rs != null) rs.close();
            if (ps != null) ps.close();
            if (conn != null) conn.close();
        }
        
        return assignedWorks;
    }
}
