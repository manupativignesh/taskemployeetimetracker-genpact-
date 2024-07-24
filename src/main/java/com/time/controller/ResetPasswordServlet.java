package com.time.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.time.dao.ResetPasswordDAO;
import com.time.model.PasswordUtil;

@WebServlet("/ResetPasswordServlet")
public class ResetPasswordServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String employeeIdStr = (String) session.getAttribute("employeeId");
        int employeeId;
        try {
            employeeId = Integer.parseInt(employeeIdStr);
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid employee ID.");
            return;
        }

        String currentPassword = request.getParameter("currentPassword");
        String newPassword = request.getParameter("newPassword");
        String confirmPassword = request.getParameter("confirmPassword");

        ResetPasswordDAO dao = new ResetPasswordDAO();
        String storedPassword = dao.getStoredPassword(employeeId);

        if (storedPassword == null) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Could not retrieve stored password.");
            return;
        }

        boolean isPasswordHashed = dao.isPasswordHashed(storedPassword);

        // Encrypt the current password provided by the user
        String encryptedCurrentPassword = PasswordUtil.encryptPassword(currentPassword);

        response.setContentType("text/html");
        response.getWriter().println("<html>");
        response.getWriter().println("<head><title>Password Reset Result</title></head>");
        response.getWriter().println("<body>");

        // Check if the stored password is hashed
        if (isPasswordHashed) {
            if (storedPassword.equals(encryptedCurrentPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    String encryptedNewPassword = PasswordUtil.encryptPassword(newPassword);
                    boolean updateSuccess = dao.updatePassword(employeeId, encryptedNewPassword);
                    if (updateSuccess) {
                        response.getWriter().println("<h1>Your password was successfully updated.</h1>");
                    } else {
                        response.getWriter().println("<h1>Failed to update your password. Please try again.</h1>");
                    }
                } else {
                    response.getWriter().println("<h1>New passwords do not match.</h1>");
                }
            } else {
                response.getWriter().println("<h1>Current password is incorrect.</h1>");
            }
        } else {
            // If the password is not hashed, compare directly
            if (storedPassword.equals(currentPassword)) {
                if (newPassword.equals(confirmPassword)) {
                    String encryptedNewPassword = PasswordUtil.encryptPassword(newPassword);
                    boolean updateSuccess = dao.updatePassword(employeeId, encryptedNewPassword);
                    if (updateSuccess) {
                        response.getWriter().println("<h1>Your password was successfully updated.</h1>");
                    } else {
                        response.getWriter().println("<h1>Failed to update your password. Please try again.</h1>");
                    }
                } else {
                    response.getWriter().println("<h1>New passwords do not match.</h1>");
                }
            } else {
                response.getWriter().println("<h1>Current password is incorrect.</h1>");
            }
        }

        response.getWriter().println("<br><button onclick=\"window.location.href='EmployeeDashboard.html'\">Back to Dashboard</button>");
        response.getWriter().println("</body>");
        response.getWriter().println("</html>");
    }
}
