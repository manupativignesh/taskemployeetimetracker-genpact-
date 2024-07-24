package com.time.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.time.dao.ViewProjectforAssosiateDAO; // Ensure correct package name
import com.time.model.Project; // Ensure correct package name

@WebServlet("/ViewProjectforAssosiateServlet")
public class ViewProjectforAssosiateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewProjectforAssosiateServlet() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null) {
            String username = (String) session.getAttribute("username");
            if (username != null) {
                ViewProjectforAssosiateDAO dao = new ViewProjectforAssosiateDAO();
                try {
                    List<Project> projectList = dao.viewAssosiate(username);
                    request.setAttribute("projectList", projectList);
                    request.getRequestDispatcher("ViewProjectsforAssosiate.jsp").forward(request, response);
                } catch (SQLException e) {
                    e.printStackTrace();
                    // Redirect to an error page or set an error message
                    request.setAttribute("errorMessage", "Error retrieving project data.");
                    request.getRequestDispatcher("error.jsp").forward(request, response);
                }
            } else {
                response.sendRedirect("Login.html");
            }
        } else {
            response.sendRedirect("Login.html");
        }
    }
}