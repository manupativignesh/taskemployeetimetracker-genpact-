package com.time.controller;

import java.io.IOException;
import java.sql.
SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.ViewProjectDAO; // Ensure this matches your actual package
import com.time.model.Project; // Ensure this matches your actual package

@SuppressWarnings("unused")
@WebServlet("/ViewProjectServlet")
public class ViewProjectServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewProjectServlet() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ViewProjectDAO dao = new ViewProjectDAO(); // Ensure this class exists and matches your package
        List<Project> projects = dao.getProjects(); // Ensure this method matches your DAO
		request.setAttribute("projectList", projects);
		request.getRequestDispatcher("viewProjects.jsp").forward(request, response);
    }
}
