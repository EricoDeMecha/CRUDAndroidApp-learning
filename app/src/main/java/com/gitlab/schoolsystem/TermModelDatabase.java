package com.gitlab.schoolsystem;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {TermModel.class}, version = 2)
public abstract class TermModelDatabase extends RoomDatabase {
    private static TermModelDatabase  instance;
    public abstract TermModelDao termModelDao();

    public static synchronized TermModelDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    TermModelDatabase.class, "ss_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCallback)
                    .build();
        }
        return instance;
    }
    private static RoomDatabase.Callback roomCallback = new RoomDatabase.Callback(){
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };
    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void>{
        private TermModelDao termModelDao;
        private PopulateDbAsyncTask(TermModelDatabase db){
            termModelDao = db.termModelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            termModelDao.insert(new TermModel("Term 1", "2022-01-01", "2023-01-01"));
            termModelDao.insert(new TermModel("Term 2", "2022-02-01", "2023-02-01"));
            termModelDao.insert(new TermModel("Term 3", "2022-03-01", "2023-03-01"));
            termModelDao.insert(new TermModel("Term 4", "2022-04-01", "2023-04-01"));
            return null;
        }
    }
}
