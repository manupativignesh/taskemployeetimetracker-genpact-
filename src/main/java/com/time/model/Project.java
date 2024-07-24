package com.time.model;

public class Project {
    private int id;
    private String projectName;
    private String projectDescription;

    public Project(int id, String projectName, String projectDescription) {
        this.id = id;
        this.projectName = projectName;
        this.projectDescription = projectDescription;
    }

    public Project(int id2, String projectName2, String projectDescription2, String languages, String status) {
		// TODO Auto-generated constructor stub
	}

	public Project(int int1, String string, String string2, String string3) {
		// TODO Auto-generated constructor stub
	}

	public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public String getProjectDescription() {
        return projectDescription;
    }

    public void setProjectDescription(String projectDescription) {
        this.projectDescription = projectDescription;
    }
}
