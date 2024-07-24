package com.time.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.ListEmployeeForProjectsDAO; // Ensure this matches your package and class name
import com.time.model.ListEmployeeForProjects; // Ensure this matches your package and class name

@WebServlet("/ListEmployeeForProjectsServlet")
public class ListEmployeeForProjectsServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ListEmployeeForProjectsServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ListEmployeeForProjectsDAO dao = new ListEmployeeForProjectsDAO(); // Ensure this class exists
        try {
            List<ListEmployeeForProjects> employeeList = dao.list(); // Ensure the list() method exists
            request.setAttribute("employeeList", employeeList);
            request.getRequestDispatcher("ListEmployeeForProjects.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving employee data.");
            request.getRequestDispatcher("error.jsp").forward(request, response); // Forward to an error page with a meaningful message
        }
    }

    // Optional: Implement or remove the doGet method as needed
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Handle GET requests if needed
        response.getWriter().append("ListEmployeeForProjectsServlet at: ").append(request.getContextPath());
    }
}
