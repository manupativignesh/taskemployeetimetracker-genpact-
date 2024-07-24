package com.time.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtils;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

import com.time.dao.WorkStatusDAO;
import com.time.model.WorkStatus;

@WebServlet("/ViewWorkStatusServlet1")
public class ViewWorkStatusServlet1 extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public ViewWorkStatusServlet1() {
        super();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String projectName = request.getParameter("projectName");

        WorkStatusDAO dao = new WorkStatusDAO();
        List<WorkStatus> workStatusList = dao.getWorkStatusByProject(projectName);

        if (workStatusList == null || workStatusList.isEmpty()) {
            response.getWriter().println("No data found for the project name: " + projectName);
            return;
        }

        DefaultPieDataset dataset = new DefaultPieDataset();
        for (WorkStatus workStatus : workStatusList) {
            try {
                // Ensure totalTime is parsed as a numeric value (Double in this case)
                Double totalTime = Double.parseDouble(workStatus.getTotalTime());
                dataset.setValue("Employee ID: " + workStatus.getEmployeeId() + " - " + workStatus.getWork(),
                        totalTime);
            } catch (NumberFormatException e) {
                e.printStackTrace();
                response.getWriter().println("Error parsing total time for employee ID: " + workStatus.getEmployeeId());
                return;
            }
        }

        JFreeChart chart = ChartFactory.createPieChart(
                "Work Status for Project: " + projectName,
                dataset,
                true,
                true,
                false);

        final PiePlot plot = (PiePlot) chart.getPlot();
        plot.setSectionOutlinesVisible(true);
        plot.setNoDataMessage("No data available");

        // Ensure the images directory exists
        String imagesDirPath = getServletContext().getRealPath("/") + "images";
        File imagesDir = new File(imagesDirPath);
        if (!imagesDir.exists()) {
            imagesDir.mkdirs();
        }

        // Generate the chart image file
        String fileName = "chart.png";
        String filePath = imagesDirPath + File.separator + fileName;
        try {
            ChartUtils.saveChartAsPNG(new File(filePath), chart, 800, 600);
        } catch (IOException e) {
            e.printStackTrace();
            response.getWriter().println("Error generating chart image.");
            return;
        }

        // Set the chart URL in the request
        String chartUrl = request.getContextPath() + "/images/" + fileName;
        request.setAttribute("chartUrl", chartUrl);

        // Forward the request to the JSP page
        request.getRequestDispatcher("viewWorkStatus1.jsp").forward(request, response);
    }
}
