package com.gitlab.schoolsystem;

import android.Manifest;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.core.content.PackageManagerCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
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
    private CourseModel courseModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_course_assessments, container, false);
        RecyclerView recyclerView = fragment_view.findViewById(R.id.assessment_recycler_view);
        assessmentAdapter = new AssessmentAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(requireContext()));
        recyclerView.setAdapter(assessmentAdapter);

        courseModel = ((CourseChildrenActivity) requireActivity()).getCourseModel();


        assessmentModelViewModel = ViewModelProviders.of(requireActivity()).get(AssessmentModelViewModel.class);
        if(courseModel != null){
            assessmentModelViewModel.getAssessmentsByCourseName(courseModel.getTerm_name()).observe(requireActivity(), new Observer<List<AssessmentModel>>() {
                @Override
                public void onChanged(List<AssessmentModel> assessmentModels) {
                    assessmentAdapter.setAssessmentModelList(assessmentModels);
                }
            });
        }else{
            assessmentModelViewModel.getAllAssessments().observe(requireActivity(), new Observer<List<AssessmentModel>>() {
                @Override
                public void onChanged(List<AssessmentModel> assessmentModels) {
                    assessmentAdapter.setAssessmentModelList(assessmentModels);
                }
            });
        }
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

        // Register the BroadcastReceiver
        LocalBroadcastManager.getInstance(requireContext()).registerReceiver(new AlarmReceiver(), new IntentFilter("com.example.ALARM_TRIGGERED"));

        // adapter listener
        assessmentAdapter.setAssessmentListenerOnClick(new AssessmentAdapter.OnItemClicked() {
            @Override
            public void onItemClicked(AssessmentModel assessmentModel) {
                // create a pop up for the purposes of editing
                new AssessmentDialog(requireContext(), assessmentModelViewModel, assessmentModel, false).show();
                /*if(ContextCompat.checkSelfPermission(requireActivity(), Manifest.permission.SET_ALARM) ==
                PackageManager.PERMISSION_GRANTED){
                    scheduleNotification();
                }else{
                    Toast.makeText(requireContext(),"Alarm persion denied", Toast.LENGTH_SHORT).show();
                }*/
            }
        });
        return fragment_view;
    }
    public void scheduleNotification() {
        Intent intent = new Intent(requireContext(), AlarmReceiver.class);
        intent.setAction("com.example.ALARM_TRIGGERED");
        final PendingIntent pIntent = PendingIntent.getBroadcast(requireContext(), AlarmReceiver.REQUEST_CODE,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis();
        AlarmManager alarm = (AlarmManager) requireContext().getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis,
                5 * 1000, pIntent);
    }
}
