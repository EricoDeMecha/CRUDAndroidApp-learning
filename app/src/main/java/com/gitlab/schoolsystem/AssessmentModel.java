package com.gitlab.schoolsystem;

public class AssessmentModel {
    public String name, due_date;

    public AssessmentModel(String name, String due_date) {
        this.name = name;
        this.due_date = due_date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDue_date() {
        return due_date;
    }

    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
