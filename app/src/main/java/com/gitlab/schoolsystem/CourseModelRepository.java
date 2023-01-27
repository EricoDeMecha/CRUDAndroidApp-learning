package com.gitlab.schoolsystem;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class CourseModelRepository {
    private CourseModelDao courseModelDao;
    private LiveData<List<CourseModel>> courseModelList;
    public CourseModelRepository(Application application){
        CourseModelDatabase database = CourseModelDatabase.getInstance(application);
        courseModelDao = database.courseModelDao();
        courseModelList = courseModelDao.getAllCourses();
    }
    public void insert(CourseModel course){
        new InsertCourseModelAsyncTask(courseModelDao).execute(course);
    }
    public void update(CourseModel course){
        new UpdateCourseModelAsyncTask(courseModelDao).execute(course);
    }
    public void delete(CourseModel course){
        new DeleteCourseModelAsyncTask(courseModelDao).execute(course);
    }
    public void deleteAll(){
        new DeleteCourseModelAsyncTask(courseModelDao).execute();
    }
    public LiveData<List<CourseModel>> getAllCourses(){
        return courseModelList;
    }
    public LiveData<List<CourseModel>>  getCoursesByTermName(String term_name){
        return courseModelDao.getCoursesByTermName(term_name);
    }
    private static class InsertCourseModelAsyncTask extends AsyncTask<CourseModel, Void, Void>{
        private CourseModelDao courseModelDao;
        private InsertCourseModelAsyncTask(CourseModelDao courseModelDao1){
            this.courseModelDao = courseModelDao1;
        }
        @Override
        protected Void doInBackground(CourseModel... courseModels) {
            courseModelDao.insert(courseModels[0]);
            return null;
        }
    }
    private static class UpdateCourseModelAsyncTask extends AsyncTask<CourseModel, Void, Void>{
        private CourseModelDao courseModelDao;
        private UpdateCourseModelAsyncTask(CourseModelDao courseModelDao){
            this.courseModelDao = courseModelDao;
        }
        @Override
        protected Void doInBackground(CourseModel... courseModels) {
            courseModelDao.update(courseModels[0]);
            return null;
        }
    }
    private static class DeleteCourseModelAsyncTask extends AsyncTask<CourseModel, Void, Void>{
        private CourseModelDao courseModelDao;
        private DeleteCourseModelAsyncTask(CourseModelDao courseModelDao){
            this.courseModelDao = courseModelDao;
        }
        @Override
        protected Void doInBackground(CourseModel... courseModels) {
            courseModelDao.delete(courseModels[0]);
            return null;
        }
    }
    private static class DeleteAllCourseModelAsyncTask extends AsyncTask<Void, Void, Void>{
        private CourseModelDao courseModelDao;
        private DeleteAllCourseModelAsyncTask(CourseModelDao courseModelDao){
            this.courseModelDao = courseModelDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            courseModelDao.deleteAllCourses();
            return null;
        }
    }
}
