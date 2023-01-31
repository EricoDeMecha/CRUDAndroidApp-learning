package com.gitlab.schoolsystem;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class NoteModelRepository {
    private static final String DATABASE = "ss_database";
    private NoteModelDao noteModelDao;
    private LiveData<List<NoteModel>> notes;
    public NoteModelRepository(Application application){
        AppDatabase database = Room.databaseBuilder(application, AppDatabase.class, DATABASE).build();
        noteModelDao = database.noteModelDao();
        notes = noteModelDao.getAllNotes();
    }
    public void insert(NoteModel Note){
        new InsertNoteModelAsyncTask(noteModelDao).execute(Note);
    }
    public void update(NoteModel Note){
        new UpdateNoteModelAsyncTask(noteModelDao).execute(Note);
    }
    public void delete(NoteModel Note){
        new DeleteNoteModelAsyncTask(noteModelDao).execute(Note);
    }
    public void deleteAll(){
        new DeleteNoteModelAsyncTask(noteModelDao).execute();
    }
    public LiveData<List<NoteModel>> getAllNotes(){
        return notes;
    }
    public LiveData<List<NoteModel>>  getNotesByCourseName(String term_name){
        return noteModelDao.getNotesByCourseName(term_name);
    }
    private static class InsertNoteModelAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private NoteModelDao noteModelDao;
        private InsertNoteModelAsyncTask(NoteModelDao noteModelDao){
            this.noteModelDao = noteModelDao;
        }
        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteModelDao.insert(noteModels[0]);
            return null;
        }
    }
    private static class UpdateNoteModelAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private NoteModelDao noteModelDao;
        private UpdateNoteModelAsyncTask(NoteModelDao noteModelDao){
            this.noteModelDao = noteModelDao;
        }
        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            noteModelDao.update(noteModels[0]);
            return null;
        }
    }
    private static class DeleteNoteModelAsyncTask extends AsyncTask<NoteModel, Void, Void>{
        private NoteModelDao NoteModelDao;
        private DeleteNoteModelAsyncTask(NoteModelDao noteModelDao){
            this.NoteModelDao = noteModelDao;
        }
        @Override
        protected Void doInBackground(NoteModel... noteModels) {
            NoteModelDao.delete(noteModels[0]);
            return null;
        }
    }
    private static class DeleteAllNoteModelAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteModelDao noteModelDao;
        private DeleteAllNoteModelAsyncTask(NoteModelDao noteModelDao){
            this.noteModelDao = noteModelDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteModelDao.deleteAllNotes();
            return null;
        }
    }
}
