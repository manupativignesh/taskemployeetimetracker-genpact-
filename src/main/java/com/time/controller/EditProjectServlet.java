package com.time.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.ViewProjectDAO; // Ensure this matches the actual package and class name
import com.time.model.Project; // Ensure this matches the actual package and class name

@WebServlet("/EditProjectServlet")
public class EditProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public EditProjectServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Optional: You can add logic here if needed, or just remove this method if not used
        response.getWriter().append("EditProjectServlet at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ViewProjectDAO obj = new ViewProjectDAO(); // Ensure this matches the actual class name
        try {
            List<Project> projects = obj.getAllProjects(); // Ensure this method exists and is correct
            request.setAttribute("projectList", projects);
            request.getRequestDispatcher("editProjects.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "An error occurred while retrieving projects.");
            request.getRequestDispatcher("error.jsp").forward(request, response); // Forward to an error page
        }
    }
}
