package com.gitlab.schoolsystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type Course model.
 */
public class CourseModel implements Parcelable {
    private String course_title, course_start_date, course_end_date;
    private CourseStatus course_status;
    private InstructorModel course_instructor;

    /**
     * Instantiates a new Course model.
     *
     * @param course_title      the course title
     * @param course_start_date the course start date
     * @param course_end_date   the course end date
     * @param course_status     the course status
     * @param course_instructor the course instructor
     */
    public CourseModel(String course_title, String course_start_date, String course_end_date, CourseStatus course_status,  InstructorModel course_instructor) {
        this.course_title = course_title;
        this.course_start_date = course_start_date;
        this.course_end_date = course_end_date;
        this.course_instructor = course_instructor;
        this.course_status = course_status;
    }

    /**
     * Instantiates a new Course model.
     *
     * @param in the in
     */
    protected CourseModel(Parcel in) {
        course_title = in.readString();
        course_start_date = in.readString();
        course_end_date = in.readString();
        course_status = (CourseStatus) in.readSerializable();
        course_instructor = in.readParcelable(InstructorModel.class.getClassLoader());
    }

    /**
     * The constant CREATOR.
     */
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

    /**
     * Gets course title.
     *
     * @return the course title
     */
    public String getCourse_title() {
        return course_title;
    }

    /**
     * Sets course title.
     *
     * @param course_title the course title
     */
    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    /**
     * Gets course start date.
     *
     * @return the course start date
     */
    public String getCourse_start_date() {
        return course_start_date;
    }

    /**
     * Sets course start date.
     *
     * @param course_start_date the course start date
     */
    public void setCourse_start_date(String course_start_date) {
        this.course_start_date = course_start_date;
    }

    /**
     * Gets course end date.
     *
     * @return the course end date
     */
    public String getCourse_end_date() {
        return course_end_date;
    }

    /**
     * Sets course end date.
     *
     * @param course_end_date the course end date
     */
    public void setCourse_end_date(String course_end_date) {
        this.course_end_date = course_end_date;
    }

    /**
     * Gets course instructor.
     *
     * @return the course instructor
     */
    public InstructorModel getCourse_instructor() {
        return course_instructor;
    }

    /**
     * Sets course instructor.
     *
     * @param course_instructor the course instructor
     */
    public void setCourse_instructor(InstructorModel course_instructor) {
        this.course_instructor = course_instructor;
    }

    /**
     * Gets course status.
     *
     * @return the course status
     */
    public CourseStatus getCourse_status() {
        return course_status;
    }

    /**
     * Sets course status.
     *
     * @param course_status the course status
     */
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
