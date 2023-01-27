package com.gitlab.schoolsystem;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {CourseModel.class}, version = 1)
@TypeConverters({CourseStatusConverter.class})
public abstract class CourseModelDatabase extends RoomDatabase {
    private static CourseModelDatabase instance;
    public abstract CourseModelDao courseModelDao();

    public static synchronized CourseModelDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    CourseModelDatabase.class, "ss_database")
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
        private CourseModelDao courseModelDao;
        private PopulateDbAsyncTask(CourseModelDatabase db){
            courseModelDao = db.courseModelDao();
        }
        @Override
        protected Void doInBackground(Void... voids) {
            courseModelDao.insert(new CourseModel(
                    "Course1",
                    "2020-03-01",
                    "2020-10-01",
                    CourseStatus.INPROGRESS,
                    new InstructorModel("Instructor1", "12345678", "instructor1@gmail.coc")
            ));
            courseModelDao.insert(new CourseModel(
                    "Course2",
                    "2020-03-01",
                    "2020-10-01",
                    CourseStatus.INPROGRESS,
                    new InstructorModel("Instructor2", "12345678", "instructor2@gmail.coc")
            ));

            courseModelDao.insert(new CourseModel(
                    "Course3",
                    "2020-03-01",
                    "2020-10-01",
                    CourseStatus.INPROGRESS,
                    new InstructorModel("Instructor3", "12345678", "instructor2@gmail.coc")
            ));
            courseModelDao.insert(new CourseModel(
                    "Course4",
                    "2020-03-01",
                    "2020-10-01",
                    CourseStatus.INPROGRESS,
                    new InstructorModel("Instructor4", "12345678", "instructor1@gmail.coc")
            ));
            return null;
        }
    }
}
