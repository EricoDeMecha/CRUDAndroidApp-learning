package com.gitlab.schoolsystem;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class NoteModelViewModel extends AndroidViewModel {
    private NoteModelRepository noteModelRepository;
    private LiveData<List<NoteModel>> allNotes;
    public NoteModelViewModel(@NonNull Application application) {
        super(application);
        noteModelRepository = new NoteModelRepository(application);
        allNotes = noteModelRepository.getAllNotes();
    }
    public void insert(NoteModel note){
        noteModelRepository.insert(note);
    }
    public void update(NoteModel note){
        noteModelRepository.update(note);
    }
    public void delete(NoteModel note){
        noteModelRepository.delete(note);
    }
    public void deleteAllNotes(){
        noteModelRepository.deleteAll();
    }
    public LiveData<List<NoteModel>> getAllNotes(){
        return allNotes;
    }
    public LiveData<List<NoteModel>> getNotesByCourseName(String course_name){
        return noteModelRepository.getNotesByCourseName(course_name);
    }
}
