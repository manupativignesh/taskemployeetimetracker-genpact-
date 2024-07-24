package com.time.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.ViewEmployeeDAO; // Ensure correct package
import com.time.model.Employee; // Ensure correct package

@WebServlet("/ViewEmployeeServlet")
public class ViewEmployeeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewEmployeeServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewEmployeeDAO dao = new ViewEmployeeDAO();
        List<Employee> employees = null;
        try {
            employees = dao.getEmployee();
            request.setAttribute("employees", employees);
            request.getRequestDispatcher("displayEmployees.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Set an error message or redirect to an error page
            request.setAttribute("errorMessage", "Error retrieving employee data.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
