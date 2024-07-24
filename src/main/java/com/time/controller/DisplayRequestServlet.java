package com.time.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.DisplayRequestDAO;
import com.time.model.Request;

@WebServlet("/DisplayRequestServlet") // Corrected servlet name capitalization
public class DisplayRequestServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
  
    public DisplayRequestServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DisplayRequestDAO displayRequestDAO = new DisplayRequestDAO();
        List<Request> requests = null;
        
        try {
            requests = displayRequestDAO.getAllRequests(); // Assumed method name
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("error", "Unable to retrieve requests due to database error.");
            request.getRequestDispatcher("error.jsp").forward(request, response);
            return; // Exit if there is an error
        }
        
        request.setAttribute("requests", requests);
        request.getRequestDispatcher("displayRequests.jsp").forward(request, response);
    }
}
