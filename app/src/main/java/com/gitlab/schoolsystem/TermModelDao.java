package com.gitlab.schoolsystem;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TermModelDao {
    @Insert
    void insert(TermModel term);

    @Update
    void update(TermModel term);

    @Delete
    void delete(TermModel term);

    @Query("DELETE FROM term_table")
    void deleteAllTerms();

    @Query("SELECT * FROM term_table ORDER BY id DESC")
    LiveData<List<TermModel>> getALLNotes();
}
