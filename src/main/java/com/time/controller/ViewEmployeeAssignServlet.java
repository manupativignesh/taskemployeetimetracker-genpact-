package com.time.controller;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.ViewEmployeeAssignDAO;  // Ensure the correct package name
import com.time.model.AssignedWork;  // Ensure the correct package name

@WebServlet("/ViewEmployeeAssignServlet")
public class ViewEmployeeAssignServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(ViewEmployeeAssignServlet.class.getName());

    public ViewEmployeeAssignServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ViewEmployeeAssignDAO dao = new ViewEmployeeAssignDAO();
        try {
            List<AssignedWork> assignedWorks = dao.retrieve();
            LOGGER.log(Level.INFO, "Assigned Works: {0}", assignedWorks);
            request.setAttribute("assignedWorks", assignedWorks);
            request.getRequestDispatcher("/viewAssignedWork.jsp").forward(request, response);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, "Error retrieving assigned works", e);
            response.sendRedirect("error.jsp");  // Redirect to an error page in case of failure
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
