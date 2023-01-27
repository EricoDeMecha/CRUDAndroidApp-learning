package com.gitlab.schoolsystem;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {NoteModel.class}, version = 4)
public abstract class NoteModelDatabase extends RoomDatabase {
    private static NoteModelDatabase instance;
    public abstract NoteModelDao noteModelDao();

    public static synchronized NoteModelDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NoteModelDatabase.class, "ss_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static Callback roomCallback = new Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private NoteModelDao noteModelDao;
        private PopulateDbAsyncTask(NoteModelDatabase db){
            noteModelDao = db.noteModelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            noteModelDao.insert(new NoteModel("Title1", "Body1"));
            noteModelDao.insert(new NoteModel("Title2", "Body2"));
            noteModelDao.insert(new NoteModel("Title3", "Body3"));
            noteModelDao.insert(new NoteModel("Title4", "Body4"));
            noteModelDao.insert(new NoteModel("Title5", "Body5"));
            return null;
        }
    }
}
