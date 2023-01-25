package com.gitlab.schoolsystem;

public class CourseModel {
    private String course_title, course_start_date, course_end_date;
    private CourseStatus course_status;
    private InstructorModel course_instructor;

    public CourseModel(String course_title, String course_start_date, String course_end_date, CourseStatus course_status,  InstructorModel course_instructor) {
        this.course_title = course_title;
        this.course_start_date = course_start_date;
        this.course_end_date = course_end_date;
        this.course_instructor = course_instructor;
        this.course_status = course_status;
    }

    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    public String getCourse_start_date() {
        return course_start_date;
    }

    public void setCourse_start_date(String course_start_date) {
        this.course_start_date = course_start_date;
    }

    public String getCourse_end_date() {
        return course_end_date;
    }

    public void setCourse_end_date(String course_end_date) {
        this.course_end_date = course_end_date;
    }

    public InstructorModel getCourse_instructor() {
        return course_instructor;
    }

    public void setCourse_instructor(InstructorModel course_instructor) {
        this.course_instructor = course_instructor;
    }

    public CourseStatus getCourse_status() {
        return course_status;
    }

    public void setCourse_status(CourseStatus course_status) {
        this.course_status = course_status;
    }
}
