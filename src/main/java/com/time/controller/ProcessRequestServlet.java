package com.time.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.time.dao.ProcessRequestDAO; // Ensure this matches your actual package structure

@WebServlet("/ProcessRequestServlet")
public class ProcessRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters from the form
        String project = request.getParameter("projectName");
        String name = request.getParameter("employeeName");
        String id = request.getParameter("employeeId");
        String skill = request.getParameter("skill");

        // Retrieve username from session
        HttpSession session = request.getSession(false); // Do not create a new session if none exists
        if (session != null) {
            String sessionUsername = (String) session.getAttribute("username");
            System.out.println("Session Username: " + sessionUsername); // Debugging line

            // Check if session username matches the entered username
            if (sessionUsername != null && sessionUsername.equals(name)) {
                // Username matches, process the request
                ProcessRequestDAO dao = new ProcessRequestDAO();
                boolean update = dao.request(project, name, id, skill); // Ensure this method exists and works
                if (update) {
                    response.sendRedirect("Requestsent.html"); // Ensure this page exists
                } else {
                    response.sendRedirect("error.jsp"); // Ensure this page exists
                }
            } else {
                // Redirect to an error page or handle unauthorized access
                response.sendRedirect("error.jsp"); // Ensure this page exists
            }
        } else {
            // Redirect to login page or handle session timeout
            response.sendRedirect("Login.html"); // Ensure this page exists
        }
    }
}
