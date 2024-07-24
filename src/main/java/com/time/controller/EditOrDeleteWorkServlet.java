package com.time.controller;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.time.dao.EditOrDeleteDAO; // Correct package
import com.time.model.Work; // Correct package
import java.util.List;

@WebServlet("/EditOrDeleteWorkServlet")
public class EditOrDeleteWorkServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        EditOrDeleteDAO dao = new EditOrDeleteDAO();
        List<Work> workDetailsList = dao.getAllWorks(); // Ensure this method exists and is correct
        request.setAttribute("works", workDetailsList);
        request.getRequestDispatcher("/editOrDeleteWork.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));
        EditOrDeleteDAO dao = new EditOrDeleteDAO();
        String message = "";

        try {
            if ("update".equals(action)) {
                String workDescription = request.getParameter("workDescription");
                String startTimeStr = request.getParameter("startTime");
                String endTimeStr = request.getParameter("endTime");

                Timestamp startTime = null;
                Timestamp endTime = null;

                try {
                    // Assuming startTime and endTime are in the format "yyyy-MM-dd HH:mm:ss"
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    startTime = new Timestamp(dateFormat.parse(startTimeStr).getTime());
                    endTime = new Timestamp(dateFormat.parse(endTimeStr).getTime());
                } catch (ParseException e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Invalid date format. Please use 'yyyy-MM-dd HH:mm:ss'.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }

                // Assuming `updateWork` method exists in DAO
                dao.updateWork(id, workDescription, startTime, endTime);
                message = "Successfully updated.";
            } else if ("delete".equals(action)) {
                // Assuming `deleteWork` method exists in DAO
                dao.deleteWork(id);
                message = "Successfully deleted.";
            } else {
                message = "Invalid action.";
            }

        } catch (Exception e) {
            e.printStackTrace();
            message = "An error occurred: " + e.getMessage();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("/operationResult.jsp").forward(request, response);
    }
}
