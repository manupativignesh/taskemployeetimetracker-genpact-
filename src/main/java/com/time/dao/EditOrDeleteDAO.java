package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import com.time.model.Work;

public class EditOrDeleteDAO {

    public List<Work> getWorksByEmployeeAndProject(int employeeId) throws Exception {
        List<Work> works = new ArrayList<>();
        String url = "jdbc:mysql://localhost:3306/employee_tracker";
        String user = "root";
        String password = "1234";
        
        try (Connection conn = DriverManager.getConnection(url, user, password);
             PreparedStatement ps = conn.prepareStatement("SELECT * FROM work WHERE employee_id = ?")) {
            ps.setInt(1, employeeId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Work work = new Work(
                        rs.getInt("id"),
                        rs.getInt("employee_id"),
                        rs.getString("project"),
                        rs.getDate("date").toLocalDate(),
                        rs.getString("description"),
                        rs.getBigDecimal("hours")
                    );
                    works.add(work);
                }
            }
        }
        
        return works;
    }

	public List<Work> getAllWorks() {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateWork(int id, String workDescription, Timestamp startTime, Timestamp endTime) {
		// TODO Auto-generated method stub
		
	}

	public void deleteWork(int id) {
		// TODO Auto-generated method stub
		
	}
}
