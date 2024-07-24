package com.time.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AssignEmployeeBYAssosiateDAO {
	public boolean isassgn(String name, String project) {
		Connection conn=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		boolean update=false;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/employee_tracker","root","1234");
			String sql="INSERT INTO employee (name, projectname) VALUES(?,?)";
			ps=conn.prepareStatement(sql);
			ps.setString(1, name);
			ps.setString(2, project);
			int rowinserted =ps.executeUpdate();
			if(rowinserted>0) {
				update=true;
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		finally {
			try {
				if(rs!=null)rs.close();
				if(ps!=null)ps.close();
				if(conn!=null)conn.close();
			}catch (Exception e) {
				e.printStackTrace();
			}
		}
		return update;
		
	}

	public boolean assignEmployee(String employeeName, String project) {
		// TODO Auto-generated method stub
		return false;
	}

}
