package com.gitlab.schoolsystem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface AssessmentModelDao {
    @Insert
    void insert(AssessmentModel assessment);

    @Update
    void update(AssessmentModel assessment);

    @Delete
    void delete(AssessmentModel assessment);

    @Query("DELETE FROM assessment_table")
    void deleteAllAssessments();

    @Query("SELECT * FROM assessment_table ORDER BY id DESC")
    LiveData<List<AssessmentModel>> getAllAssessments();

    @Query("SELECT * FROM assessment_table WHERE course_title = :course_name")
    LiveData<List<AssessmentModel>> getAssessmentsByCourseName(String course_name);
}
