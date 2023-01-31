package com.gitlab.schoolsystem;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {TermModel.class, CourseModel.class, AssessmentModel.class, NoteModel.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract TermModelDao termModelDao();
    public abstract CourseModelDao courseModelDao();
    public abstract AssessmentModelDao assessmentModelDao();
    public abstract  NoteModelDao  noteModelDao();
}
