package com.gitlab.schoolsystem;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class AssessmentModelViewModel extends AndroidViewModel {
    private AssessmentModelRepository assessmentModelRepository;
    private LiveData<List<AssessmentModel>> allAssessments;
    public AssessmentModelViewModel(@NonNull Application application) {
        super(application);
        assessmentModelRepository = new AssessmentModelRepository(application);
        allAssessments = assessmentModelRepository.getAllAssessments();
    }
    public void insert(AssessmentModel assessment){
        assessmentModelRepository.insert(assessment);
    }
    public void update(AssessmentModel assessment){
        assessmentModelRepository.update(assessment);
    }
    public void delete(AssessmentModel assessment){
        assessmentModelRepository.delete(assessment);
    }
    public void deleteAllAssessment(){
        assessmentModelRepository.deleteAll();
    }
    public LiveData<List<AssessmentModel>> getAllAssessments(){
        return allAssessments;
    }
    public LiveData<List<AssessmentModel>> getAssessmentsByCourseName(String course_name){
        return assessmentModelRepository.getAssessmentsByCourseName(course_name);
    }
}
