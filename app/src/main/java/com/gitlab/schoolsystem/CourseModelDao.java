package com.gitlab.schoolsystem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CourseModelDao {
    @Insert
    void insert(CourseModel course);

    @Update
    void update(CourseModel course);

    @Delete
    void delete(CourseModel course);

    @Query("DELETE FROM course_table")
    void deleteAllCourses();

    @Query("SELECT * FROM course_table ORDER BY id DESC")
    LiveData<List<CourseModel>> getAllCourses();

    @Query("SELECT * FROM course_table WHERE term_name = :term_name")
    LiveData<List<CourseModel>> getCoursesByTermName(String  term_name);
}
