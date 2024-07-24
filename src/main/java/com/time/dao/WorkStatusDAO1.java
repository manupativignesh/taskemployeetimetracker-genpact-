package com.time.dao;

import com.time.model.WorkStatus;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class WorkStatusDAO1 {

    public List<WorkStatus> getWorkStatusByProject(String projectName) {
        List<WorkStatus> workStatusList = new ArrayList<>();
        String query = "SELECT * FROM work_status WHERE project = ?";
        
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            
            stmt.setString(1, projectName);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    WorkStatus workStatus = new WorkStatus(
                        rs.getInt("employee_id"),
                        rs.getString("employee_name"),
                        rs.getString("work"),
                        rs.getString("total_time"),
                        rs.getTimestamp("start_time"),
                        rs.getTimestamp("end_time")
                    );
                    workStatusList.add(workStatus);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workStatusList;
    }

    private Connection getConnection() throws SQLException {
        // Implement your database connection here
        // Example:
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_tracker", "root", "1234");
    }
}
