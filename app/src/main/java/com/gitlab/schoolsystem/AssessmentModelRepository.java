package com.gitlab.schoolsystem;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;
import androidx.room.Room;

import java.util.List;

public class AssessmentModelRepository {
    private static final String DATABASE = "ss_database";
    private AssessmentModelDao assessmentModelDao;
    private LiveData<List<AssessmentModel>> assessments;
    public AssessmentModelRepository(Application application){
        AppDatabase database = Room.databaseBuilder(application, AppDatabase.class, DATABASE).build();
        assessmentModelDao = database.assessmentModelDao();
        assessments = assessmentModelDao.getAllAssessments();
    }
    public void insert(AssessmentModel Assessment){
        new InsertAssessmentModelAsyncTask(assessmentModelDao).execute(Assessment);
    }
    public void update(AssessmentModel Assessment){
        new UpdateAssessmentModelAsyncTask(assessmentModelDao).execute(Assessment);
    }
    public void delete(AssessmentModel Assessment){
        new DeleteAssessmentModelAsyncTask(assessmentModelDao).execute(Assessment);
    }
    public void deleteAll(){
        new DeleteAssessmentModelAsyncTask(assessmentModelDao).execute();
    }
    public LiveData<List<AssessmentModel>> getAllAssessments(){
        return assessments;
    }
    public LiveData<List<AssessmentModel>>  getAssessmentsByCourseName(String term_name){
        return assessmentModelDao.getAssessmentsByCourseName(term_name);
    }
    private static class InsertAssessmentModelAsyncTask extends AsyncTask<AssessmentModel, Void, Void>{
        private AssessmentModelDao AssessmentModelDao;
        private InsertAssessmentModelAsyncTask(AssessmentModelDao AssessmentModelDao1){
            this.AssessmentModelDao = AssessmentModelDao1;
        }
        @Override
        protected Void doInBackground(AssessmentModel... AssessmentModels) {
            AssessmentModelDao.insert(AssessmentModels[0]);
            return null;
        }
    }
    private static class UpdateAssessmentModelAsyncTask extends AsyncTask<AssessmentModel, Void, Void>{
        private AssessmentModelDao AssessmentModelDao;
        private UpdateAssessmentModelAsyncTask(AssessmentModelDao AssessmentModelDao){
            this.AssessmentModelDao = AssessmentModelDao;
        }
        @Override
        protected Void doInBackground(AssessmentModel... AssessmentModels) {
            AssessmentModelDao.update(AssessmentModels[0]);
            return null;
        }
    }
    private static class DeleteAssessmentModelAsyncTask extends AsyncTask<AssessmentModel, Void, Void>{
        private AssessmentModelDao AssessmentModelDao;
        private DeleteAssessmentModelAsyncTask(AssessmentModelDao AssessmentModelDao){
            this.AssessmentModelDao = AssessmentModelDao;
        }
        @Override
        protected Void doInBackground(AssessmentModel... AssessmentModels) {
            AssessmentModelDao.delete(AssessmentModels[0]);
            return null;
        }
    }
    private static class DeleteAllAssessmentModelAsyncTask extends AsyncTask<Void, Void, Void>{
        private AssessmentModelDao AssessmentModelDao;
        private DeleteAllAssessmentModelAsyncTask(AssessmentModelDao AssessmentModelDao){
            this.AssessmentModelDao = AssessmentModelDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            AssessmentModelDao.deleteAllAssessments();
            return null;
        }
    }
}
