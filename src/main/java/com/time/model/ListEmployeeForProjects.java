package com.time.model;

public class ListEmployeeForProjects {
    private int id;
    private String employeeName;
    private String projectName;
    private String role;
    private String status;

    public ListEmployeeForProjects(int id, String employeeName, String projectName, String role, String status) {
        this.id = id;
        this.employeeName = employeeName;
        this.projectName = projectName;
        this.role = role;
        this.status = status;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
