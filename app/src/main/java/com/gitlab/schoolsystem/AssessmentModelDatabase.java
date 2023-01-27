package com.gitlab.schoolsystem;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {AssessmentModel.class}, version = 3)
public abstract class AssessmentModelDatabase extends RoomDatabase {
    private static AssessmentModelDatabase instance;
    public abstract AssessmentModelDao assessmentModelDao();

    public static synchronized AssessmentModelDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AssessmentModelDatabase.class, "ss_database")
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
        private AssessmentModelDao assessmentModelDao;
        private PopulateDbAsyncTask(AssessmentModelDatabase db){
            assessmentModelDao = db.assessmentModelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            assessmentModelDao.insert(new AssessmentModel("Assessment 1", "2022-02-01"));
            assessmentModelDao.insert(new AssessmentModel("Assessment 2", "2022-03-01"));
            assessmentModelDao.insert(new AssessmentModel("Assessment 3", "2022-04-01"));
            assessmentModelDao.insert(new AssessmentModel("Assessment 4", "2022-05-01"));
            assessmentModelDao.insert(new AssessmentModel("Assessment 5", "2022-06-01"));
            return null;
        }
    }
}
