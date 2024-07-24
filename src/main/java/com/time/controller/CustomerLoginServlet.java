package com.time.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.time.dao.CustomerLoginDAO;
import com.time.model.PasswordUtil;

@WebServlet("/CustomerLoginServlet")
public class CustomerLoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public CustomerLoginServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String employeeId = request.getParameter("employeeId");
        String password = request.getParameter("password");
        
        CustomerLoginDAO dao = new CustomerLoginDAO();
        String storedPasswordHash = dao.getPasswordForUser(username, employeeId);
        boolean isPasswordHashed = dao.isPasswordHashed(storedPasswordHash);

        System.out.println("Stored Password Hash: " + storedPasswordHash);
        System.out.println("Entered Password: " + password);
        System.out.println("Is Password Hashed: " + isPasswordHashed);

        if (isPasswordHashed) {
            String encryptedPassword = PasswordUtil.encryptPassword(password);
            if (storedPasswordHash.equals(encryptedPassword)) {
                createSessionAndRedirect(request, response, username, employeeId, "EmployeeDashboard.html");
            } else {
                response.sendRedirect("Failure.html");
            }
        } else {
            if (storedPasswordHash.equals(password)) {
                createSessionAndRedirect(request, response, username, employeeId, "EmployeeDashboard.html");
            } else {
                response.sendRedirect("Failure.html");
            }
        }
    }

    private void createSessionAndRedirect(HttpServletRequest request, HttpServletResponse response, String username, String employeeId, String redirectUrl) throws IOException {
        HttpSession session = request.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("employeeId", employeeId);
        response.sendRedirect(redirectUrl);
    }
}
