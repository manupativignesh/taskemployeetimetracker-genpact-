package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.time.model.WorkStatus;

public class WorkStatusDAO {
    private static final String URL = "jdbc:mysql://localhost:3306/employee_tracker";
    private static final String USER = "root";
    private static final String PASSWORD = "1234";

    // Method to get the database connection
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Method to close resources
    private void closeResources(ResultSet rs, PreparedStatement ps, Connection conn) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Consider logging the error
            }
        }
        if (ps != null) {
            try {
                ps.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Consider logging the error
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace(); // Consider logging the error
            }
        }
    }

    // Method to get work status by project name
    public List<WorkStatus> getWorkStatusByProject(String projectName) {
        List<WorkStatus> workStatusList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = getConnection();

            // Use a parameterized query to prevent SQL injection
            String query = "SELECT employee_id, work, total_time FROM work_status WHERE project_name = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, projectName);

            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                int employeeId = resultSet.getInt("employee_id");
                String work = resultSet.getString("work");
                String totalTime = resultSet.getString("total_time"); // Adjust based on actual data type

                // Construct a WorkStatus object and add it to the list
                WorkStatus workStatus = new WorkStatus(employeeId, work, totalTime, null, null, null);
                workStatusList.add(workStatus);
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Replace with proper logging if needed
        } finally {
            // Close resources in the reverse order of their opening
            closeResources(resultSet, preparedStatement, connection);
        }

        return workStatusList;
    }
}
