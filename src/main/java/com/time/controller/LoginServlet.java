package com.time.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

import com.time.dao.LoginDAO; // Ensure this matches your actual package

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public LoginServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        LoginDAO dao = new LoginDAO(); // Ensure this class exists and matches your package
        String role = dao.isValidate(username, password);

        if (role != null) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username);
            session.setAttribute("role", role);

            if ("admin".equalsIgnoreCase(role)) {
                response.sendRedirect("AdminSuccessDashboard.html"); // Ensure this path is correct
            } else if ("Associate".equalsIgnoreCase(role)) {
                response.sendRedirect("AssociateSuccessDashboard.html"); // Ensure this path is correct
            } else {
                response.sendRedirect("Failure.html"); // Redirect to failure page
            }
        } else {
            response.sendRedirect("Failure.html"); // Redirect to failure page
        }
    }
}
