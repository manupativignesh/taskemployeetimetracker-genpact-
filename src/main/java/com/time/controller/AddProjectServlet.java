package com.time.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.AddProjectDAO;  // Make sure to use the correct package


@WebServlet("/AddProjectServlet")
public class AddProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddProjectServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Retrieve parameters from the request
        String projectName = request.getParameter("projectName");
        String projectDescription = request.getParameter("projectDescription");
        String programmingLanguages = request.getParameter("programmingLanguages");
        String status = request.getParameter("Status");
        String associate = request.getParameter("Associate"); // Corrected typo

        // Validate parameters
        if (projectName == null || projectDescription == null || programmingLanguages == null || status == null || associate == null) {
            request.setAttribute("error", "All fields are required.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return;
        }

        // Create DAO object and perform the operation
        AddProjectDAO projectDAO = new AddProjectDAO();
        boolean projectAdded = projectDAO.addProject(projectName, projectDescription, programmingLanguages, status, associate);

        // Handle the result
        if (projectAdded) {
            request.getRequestDispatcher("ProjectAddedSuccessfully.jsp").forward(request, response);
        } else {
            request.setAttribute("error", "Project could not be added.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }
}
