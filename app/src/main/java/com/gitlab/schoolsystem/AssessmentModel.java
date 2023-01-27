package com.gitlab.schoolsystem;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "assessment_table")
public class AssessmentModel {
    @PrimaryKey(autoGenerate = true)
    int id;

    @ForeignKey(entity = CourseModel.class, parentColumns = "course_title", childColumns = "course_title", onDelete = CASCADE)
    private String course_title;

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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }
}
