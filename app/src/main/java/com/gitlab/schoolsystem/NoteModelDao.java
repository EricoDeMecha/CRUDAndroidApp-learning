package com.gitlab.schoolsystem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface NoteModelDao {
    @Insert
    void insert(NoteModel note);

    @Update
    void update(NoteModel note);

    @Delete
    void delete(NoteModel note);

    @Query("DELETE FROM note_table")
    void deleteAllNotes();

    @Query("SELECT * FROM note_table ORDER BY id DESC")
    LiveData<List<NoteModel>> getAllNotes();

    @Query("SELECT * FROM note_table WHERE course_title = :course_name")
    LiveData<List<NoteModel>> getNotesByCourseName(String course_name);
}
