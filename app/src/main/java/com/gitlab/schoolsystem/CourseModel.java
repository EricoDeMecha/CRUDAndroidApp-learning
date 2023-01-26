package com.gitlab.schoolsystem;

import android.os.Parcel;
import android.os.Parcelable;

public class CourseModel implements Parcelable {
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

    protected CourseModel(Parcel in) {
        course_title = in.readString();
        course_start_date = in.readString();
        course_end_date = in.readString();
        course_status = (CourseStatus) in.readSerializable();
        course_instructor = in.readParcelable(InstructorModel.class.getClassLoader());
    }
    public static final Creator<CourseModel> CREATOR = new Creator<CourseModel>() {
        @Override
        public CourseModel createFromParcel(Parcel in) {
            return new CourseModel(in);
        }

        @Override
        public CourseModel[] newArray(int size) {
            return new CourseModel[size];
        }
    };

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(course_title);
        dest.writeString(course_start_date);
        dest.writeString(course_end_date);
        dest.writeSerializable(course_status);
        dest.writeParcelable(course_instructor, flags);
    }
}
