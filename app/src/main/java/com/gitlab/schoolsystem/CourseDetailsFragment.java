package com.gitlab.schoolsystem;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;
import java.util.Objects;

public class CourseDetailsFragment extends Fragment {
    private CourseModel courseModel;
    private View view;
    private FloatingActionButton save_button;
    private EditText course_title, start_date, end_date;
    private Spinner status_spinner, instructor_spinner;
    private static final String TAG = "CourseDetailsFragment";
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_course_details, container, false);
        save_button = view.findViewById(R.id.save_button);
        courseModel = ((CourseChildren) requireActivity()).getCourseModel();
        setElements();
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveElements();
            }
        });
        return view;
    }
    private void setElements(){
        course_title = view.findViewById(R.id.coursetitle_edit);
        course_title.setText(courseModel.getCourse_title());

        start_date = view.findViewById(R.id.startdate_edit);
        start_date.setText(courseModel.getCourse_start_date());

        end_date = view.findViewById(R.id.enddate_edit);
        end_date.setText(courseModel.getCourse_end_date());
        // status spinner
        status_spinner  = view.findViewById(R.id.status_spinner);
        status_spinner.setSelection(courseModel.getCourse_status().ordinal());
        // instructor spinner
        instructor_spinner = view.findViewById(R.id.instructor_spinner);
        String[] instructors = getInstructors();
        ArrayAdapter<String> adapter =  new ArrayAdapter<>(requireContext(), R.layout.custom_spinner, instructors);
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        instructor_spinner.setAdapter(adapter);
        int position = Arrays.asList(instructors).indexOf(courseModel.getCourse_instructor().getName());
        instructor_spinner.setSelection(position);
    }

    private String[] getInstructors(){
        /*TODO - get all instructors from the database */
        return new String[]{"Instructor1", "Instructor2","Instructor3","Instructor4","Instructor5"};
    }
    private void saveElements(){
        String course_title_ = course_title.getText().toString();
        String start_date_ =  start_date.getText().toString();
        String end_date_ = end_date.getText().toString();
        String course_status_  = status_spinner.getSelectedItem().toString();
        String instructor_name_ = instructor_spinner.getSelectedItem().toString();
        Log.d(TAG, "saveElements: "+  course_title_ + " | "+  start_date_ + " | "+  end_date_ + " | "+  course_status_ + " | " + instructor_name_);
    }
}
