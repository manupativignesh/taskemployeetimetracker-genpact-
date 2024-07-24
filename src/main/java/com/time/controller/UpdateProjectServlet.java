package com.time.controller;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.time.dao.UpdateProjectDao;

@WebServlet("/UpdateProjectServlet")
public class UpdateProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Retrieve parameters
        String[] projects = request.getParameterValues("projects");
        String[] descriptions = request.getParameterValues("descriptions");
        String[] languages = request.getParameterValues("languages");
        String[] status = request.getParameterValues("status");
        String[] managerNames = request.getParameterValues("manager_names");

        // Check if parameters are not null and have the same length
        if (projects == null || descriptions == null || languages == null || status == null || managerNames == null ||
            projects.length != descriptions.length || projects.length != languages.length || projects.length != status.length || projects.length != managerNames.length) {
            response.sendRedirect("error.jsp?message=Invalid input parameters.");
            return;
        }

        UpdateProjectDao dao = new UpdateProjectDao();
        boolean isAllUpdated = true;
        try {
            for (int i = 0; i < projects.length; i++) {
                boolean isUpdated = dao.updateProject(projects[i], descriptions[i], languages[i], status[i], managerNames[i]);
                if (!isUpdated) {
                    isAllUpdated = false;
                    break;
                }
            }
            if (isAllUpdated) {
                response.sendRedirect("EditedSuccess.html");
            } else {
                response.sendRedirect("error.jsp?message=Some projects could not be updated.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            response.sendRedirect("error.jsp?message=Database error occurred.");
        }
    }
}
