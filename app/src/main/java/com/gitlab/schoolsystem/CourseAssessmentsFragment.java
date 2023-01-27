package com.gitlab.schoolsystem;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CourseAssessmentsFragment extends Fragment {

    private AssessmentAdapter assessmentAdapter;
    private AssessmentModelViewModel assessmentModelViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_course_assessments, container, false);
        RecyclerView recyclerView = fragment_view.findViewById(R.id.assessment_recycler_view);
        assessmentAdapter = new AssessmentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(assessmentAdapter);

        assessmentModelViewModel = ViewModelProviders.of(requireActivity()).get(AssessmentModelViewModel.class);
        assessmentModelViewModel.getAllAssessments().observe(requireActivity(), new Observer<List<AssessmentModel>>() {
            @Override
            public void onChanged(List<AssessmentModel> assessmentModels) {
                assessmentAdapter.setAssessmentModelList(assessmentModels);
            }
        });
        // delete assessment on swipe
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT ) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                assessmentModelViewModel.delete(assessmentAdapter.getModelAt(viewHolder.getAdapterPosition()));
                Toast.makeText(requireContext(), "Note deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        // add button
        FloatingActionButton assessment_add_button = fragment_view.findViewById(R.id.add_assessment_btn);
        assessment_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new AssessmentDialog(requireContext(), assessmentModelViewModel, null, true).show();
            }
        });
        // adapter listener
        assessmentAdapter.setAssessmentListenerOnClick(new AssessmentAdapter.OnItemClicked() {
            @Override
            public void onItemClicked(AssessmentModel assessmentModel) {
                // create a pop up for the purposes of editing
                new AssessmentDialog(requireContext(), assessmentModelViewModel, assessmentModel, false).show();
            }
        });
        return fragment_view;
    }
}
