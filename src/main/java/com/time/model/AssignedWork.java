package com.time.model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class AssignedWork {
    private String projectId;
    private String employeeId;
    private String employeeName;
    private String role;
    private String project;
    private LocalDate taskDate;
    private String startTime;
    private String endTime;
    private BigDecimal duration;
    private String taskCategory;
    private String description;

    // Constructors
    public AssignedWork() {}

    public AssignedWork(String projectId, String employeeId, String employeeName, String role, String project, LocalDate taskDate, String startTime, String endTime, BigDecimal duration, String taskCategory, String description) {
        this.projectId = projectId;
        this.employeeId = employeeId;
        this.employeeName = employeeName;
        this.role = role;
        this.project = project;
        this.taskDate = taskDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.duration = duration;
        this.taskCategory = taskCategory;
        this.description = description;
    }

    // Getters and Setters
    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getProject() {
        return project;
    }

    public void setProject(String project) {
        this.project = project;
    }

    public LocalDate getTaskDate() {
        return taskDate;
    }

    public void setTaskDate(LocalDate taskDate) {
        this.taskDate = taskDate;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public BigDecimal getDuration() {
        return duration;
    }

    public void setDuration(BigDecimal duration) {
        this.duration = duration;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
