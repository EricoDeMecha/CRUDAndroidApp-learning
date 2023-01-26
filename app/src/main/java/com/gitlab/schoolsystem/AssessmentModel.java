package com.gitlab.schoolsystem;

/**
 * The type Assessment model.
 */
public class AssessmentModel {
    /**
     * The Name.
     */
    public String name, /**
     * The Due date.
     */
    due_date;

    /**
     * Instantiates a new Assessment model.
     *
     * @param name     the name
     * @param due_date the due date
     */
    public AssessmentModel(String name, String due_date) {
        this.name = name;
        this.due_date = due_date;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets due date.
     *
     * @return the due date
     */
    public String getDue_date() {
        return due_date;
    }

    /**
     * Sets due date.
     *
     * @param due_date the due date
     */
    public void setDue_date(String due_date) {
        this.due_date = due_date;
    }
}
