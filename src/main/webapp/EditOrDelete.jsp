<%@ page import="java.util.List" %>
<%@ page import="com.time.model.Work" %>
<%@ page import="com.time.dao.EditOrDeleteDAO" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Edit or Delete Work</title>
    <style>
        /* Your CSS here */
    </style>
</head>
<body>
    <div class="container">
        <h2>Edit or Delete Work</h2>
        <table>
            <thead>
                <tr>
                    <th>Work ID</th>
                    <th>Work Description</th>
                    <th>Start Time</th>
                    <th>End Time</th>
                    <th>Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                    List<Work> workDetailsList = (List<Work>) request.getAttribute("works");
                    if (workDetailsList != null && !workDetailsList.isEmpty()) {
                        for (Work work : workDetailsList) {
                %>
                    <tr>
                        <td><%= work.getId() %></td>
                        <td>
                            <form action="EditOrDeleteWorkServlet" method="post" style="display: flex; gap: 10px;">
                                <input type="hidden" name="id" value="<%= work.getId() %>">
                                <input type="hidden" name="action" value="update">
                                <input type="text" name="work" value="<%= work.getWork() %>" required>
                                <input type="text" name="startTime" value="<%= work.getStartTime() %>" required>
                                <input type="text" name="endTime" value="<%= work.getEndTime() %>" required>
                                <button type="submit">Update</button>
                            </form>
                            <form action="EditOrDeleteWorkServlet" method="post" style="display: inline;">
                                <input type="hidden" name="id" value="<%= work.getId() %>">
                                <input type="hidden" name="action" value="delete">
                                <button type="submit">Delete</button>
                            </form>
                        </td>
                    </tr>
                <% 
                        }
                    } else {
                %>
                    <tr>
                        <td colspan="5">No work details found for the specified project.</td>
                    </tr>
                <% 
                    }
                %>
            </tbody>
        </table>
    </div>
    <button onclick="window.location.href='EmployeeDashboard.html'">Back to Dashboard</button>
</body>
</html>
