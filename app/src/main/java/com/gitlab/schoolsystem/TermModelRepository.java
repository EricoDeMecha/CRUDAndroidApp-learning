package com.gitlab.schoolsystem;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

public class TermModelRepository {
    private TermModelDao termModelDao;
    private LiveData<List<TermModel>> termModelList;
    public TermModelRepository(Application application){
        TermModelDatabase database = TermModelDatabase.getInstance(application);
        termModelDao = database.termModelDao();
        termModelList = termModelDao.getALLNotes();
    }
    public void insert(TermModel term){
        new InsertTermModelAsyncTask(termModelDao).execute(term);
    }
    public void update(TermModel term){
        new UpdateTermModelAsyncTask(termModelDao).execute(term);
    }
    public void delete(TermModel term){
        new DeleteTermModelAsyncTask(termModelDao).execute(term);
    }
    public void deleteAll(){
        new DeleteAllTermModelAsyncTask(termModelDao).execute();
    }
    public LiveData<List<TermModel>> getAllTermModels(){
        return termModelList;
    }

    private static class InsertTermModelAsyncTask extends AsyncTask<TermModel, Void, Void>{
        private TermModelDao termModelDao;
        private InsertTermModelAsyncTask(TermModelDao termModelDao){
            this.termModelDao = termModelDao;
        }
        @Override
        protected Void doInBackground(TermModel... termModels) {
            termModelDao.insert(termModels[0]);
            return null;
        }
    }
    private static class UpdateTermModelAsyncTask extends AsyncTask<TermModel, Void, Void>{
        private TermModelDao termModelDao;
        private UpdateTermModelAsyncTask(TermModelDao termModelDao){
            this.termModelDao = termModelDao;
        }
        @Override
        protected Void doInBackground(TermModel... termModels) {
            termModelDao.update(termModels[0]);
            return null;
        }
    }
    private static class DeleteTermModelAsyncTask extends AsyncTask<TermModel, Void, Void>{
        private TermModelDao termModelDao;
        private DeleteTermModelAsyncTask(TermModelDao termModelDao){
            this.termModelDao = termModelDao;
        }
        @Override
        protected Void doInBackground(TermModel... termModels) {
            termModelDao.delete(termModels[0]);
            return null;
        }
    }
    private static class DeleteAllTermModelAsyncTask extends AsyncTask<Void, Void, Void>{
        private TermModelDao termModelDao;
        private DeleteAllTermModelAsyncTask(TermModelDao termModelDao){
            this.termModelDao = termModelDao;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            termModelDao.deleteAllTerms();
            return null;
        }
    }
}
