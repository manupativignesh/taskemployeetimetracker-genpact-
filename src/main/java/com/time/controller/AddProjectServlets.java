package com.time.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.AddProjectDAO;

@WebServlet("/AddProjectServlet")
public class AddProjectServlets extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    public AddProjectServlets() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String project = request.getParameter("projectName");
        String description = request.getParameter("projectDescription");
        String languages = request.getParameter("programmingLanguages");
        String status = request.getParameter("Status");
        String associate = request.getParameter("Associate");

        AddProjectDAO obj = new AddProjectDAO();
        boolean projectUpdated = obj.addProject(project, description, languages, status, associate);

        if (projectUpdated) {
            request.getRequestDispatcher("ProjectAddedSuccessfully.jsp").forward(request, response);
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
