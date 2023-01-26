package com.gitlab.schoolsystem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseAssessmentsFragment extends Fragment {
    // add assessment components
    private AlertDialog assessment_dialog;
    private View assessment_dialog_view;

    private FloatingActionButton assessment_add_button;
    private final List<AssessmentModel> assessmentModelList = new ArrayList<>();
    private AssessmentAdapter assessmentAdapter;
    private RecyclerView recyclerView;
    private View fragment_view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_view = inflater.inflate(R.layout.fragment_course_assessments, container, false);

        // prepopulate the assessmentModelList
        getAssessMents();

        initRecyclerView();
        buildAssessmentDialog();
        assessment_add_button = fragment_view.findViewById(R.id.add_assessment_btn);
        assessment_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assessment_dialog.show();
            }
        });
        return fragment_view;
    }
    public void buildAssessmentDialog(){
        assessment_dialog_view = getLayoutInflater().inflate(R.layout.add_assessment_dialog, null);
        //  note title
        EditText assessment_title = assessment_dialog_view.findViewById(R.id.assessment_title_edit);
        // note body
        EditText assessment_due_date = assessment_dialog_view.findViewById(R.id.due_date_edit);
        DatePicker.getInstance().selectDate(getContext(),  assessment_due_date);
        // builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this.requireContext());
        builder.setView(assessment_dialog_view);
        builder.setTitle("Insert Assessment")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(assessment_title) && !Utils.isEmpty(assessment_title)) {
                            assessmentModelList.add(new AssessmentModel(
                                    assessment_title.getText().toString(),
                                    assessment_due_date.getText().toString()
                            ));
                            assessmentAdapter.notifyItemInserted(assessmentModelList.size()-1);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*Clear  and note */
                        assessment_title.getText().clear();
                        assessment_due_date.getText().clear();
                    }
                });
        assessment_dialog = builder.create();
    }
    private void initRecyclerView(){
        recyclerView = fragment_view.findViewById(R.id.assessment_recycler_view);
        assessmentAdapter = new AssessmentAdapter(getContext(), assessmentModelList);
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(assessmentAdapter);
    }
    private void getAssessMents(){
        /*TODO; get the notes from the database*/
        assessmentModelList.add(new AssessmentModel("Assessment1", "2022-01-01"));
        assessmentModelList.add(new AssessmentModel("Assessment2", "2022-01-01"));
        assessmentModelList.add(new AssessmentModel("Assessment3", "2022-01-01"));
        assessmentModelList.add(new AssessmentModel("Assessment4", "2022-01-01"));
        assessmentModelList.add(new AssessmentModel("Assessment5", "2022-01-01"));
    }
}
