package com.time.controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.time.dao.EditOrDeleteDAO;
import com.time.model.Work;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/EditOrDeleteServlet")
public class EditOrDeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(EditOrDeleteServlet.class.getName());

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession(false); // Use false to not create a new session if it doesn't exist

        if (session != null) {
            String employeeIdStr = (String) session.getAttribute("employeeId");

            if (employeeIdStr != null) {
                try {
                    int employeeId = Integer.parseInt(employeeIdStr);
                    EditOrDeleteDAO dao = new EditOrDeleteDAO();
                    List<Work> works = dao.getWorksByEmployeeAndProject(employeeId);

                    request.setAttribute("works", works);
                    request.getRequestDispatcher("EditOrDelete.jsp").forward(request, response);
                } catch (NumberFormatException e) {
                    LOGGER.log(Level.SEVERE, "Invalid employee ID format: {0}", employeeIdStr);
                    response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID format.");
                } catch (Exception e) {
                    LOGGER.log(Level.SEVERE, "Error retrieving works for employee ID: {0}", employeeIdStr);
                    response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error retrieving work details.");
                }
            } else {
                LOGGER.log(Level.WARNING, "Employee ID not found in session.");
                response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Employee ID not found in session.");
            }
        } else {
            LOGGER.log(Level.WARNING, "Session not found.");
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Session not found.");
        }
    }
}
