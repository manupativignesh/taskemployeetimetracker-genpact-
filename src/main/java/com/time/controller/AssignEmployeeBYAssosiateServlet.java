package com.time.controller;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.time.dao.AssignEmployeeBYAssosiateDAO;

@WebServlet("/AssignEmployeeByAssociateServlet")
public class AssignEmployeeBYAssosiateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AssignEmployeeBYAssosiateServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Enumeration<String> parameterNames = request.getParameterNames();
        AssignEmployeeBYAssosiateDAO dao = new AssignEmployeeBYAssosiateDAO();
        boolean update = false;

        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            if (paramName.startsWith("employeeName_")) {
                String project = paramName.substring("employeeName_".length());
                String employeeName = request.getParameter(paramName);

                // Save to database
                boolean result = dao.assignEmployee(employeeName, project);
                if (result) {
                    update = true;
                }
            }
        }

        if (update) {
            response.sendRedirect("SuccessfullyUpdated.html");
        } else {
            response.sendRedirect("error.jsp");
        }
    }
}
