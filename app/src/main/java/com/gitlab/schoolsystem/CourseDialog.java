package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class CourseDialog {
    private Context context;
    private CourseModelViewModel courseModelViewModel;
    private List<CourseModel> courseModels;

    public CourseDialog(Context context, CourseModelViewModel courseModelViewModel, List<CourseModel> courseModels){
        this.context = context;
        this.courseModelViewModel = courseModelViewModel;
        this.courseModels = courseModels;
    }
    public void show() {
        View course_dialog_view = LayoutInflater.from(context).inflate(R.layout.course_dialog, null);
        // Course title
        EditText course_title_view = course_dialog_view.findViewById(R.id.coursetitle_edit);
        // course start date
        EditText course_start_date = course_dialog_view.findViewById(R.id.startdate_edit);
        new DatePicker(context, course_start_date).enable();
        // course end date
        EditText course_end_date = course_dialog_view.findViewById(R.id.enddate_edit);
        new DatePicker(context, course_end_date).enable();
        //  course status spinner
        Spinner status_spinner = course_dialog_view.findViewById(R.id.status_spinner);
        String[] status_resource_array = context.getResources().getStringArray(R.array.course_status_list);
        AtomicReference<String> selected_status  = new AtomicReference<>();
        setupSpinner(status_spinner, status_resource_array, (pair)->{
            Spinner spinner = pair.first;
            selected_status.set(pair.second);
        } );
        // instructor spinner
        Spinner instructor_spinner = course_dialog_view.findViewById(R.id.instructor_spinner);
        String[] instructor_resource_array =  getInstructorNames();
        AtomicReference<String> selected_instructor = new AtomicReference<>();
        setupSpinner(instructor_spinner, instructor_resource_array, (pair)->{
            Spinner spinner = pair.first;
            selected_instructor.set(pair.second);
        });
        // builder
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(course_dialog_view);
        builder.setTitle("Enter the course details")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(course_title_view) && !Utils.isEmpty(course_start_date) && !Utils.isEmpty(course_start_date)){
                            String start_date_ = course_start_date.getText().toString();
                            String end_date_ = course_end_date.getText().toString();
                            if(Utils.compareDates(start_date_, end_date_) < 0){
                                CourseModel  new_course_model = new CourseModel(
                                        course_title_view.getText().toString(),
                                        start_date_,
                                        end_date_,
                                        CourseStatus.valueOf(selected_status.toString()),
                                        new InstructorModel(selected_instructor.toString()));
                                courseModelViewModel.insert(new_course_model);
                            }else {
                                Toast.makeText(context, "Wrong dates order", Toast.LENGTH_LONG).show();
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", null);
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corners_background);
        dialog.show();
    }

    private String[] getInstructorNames() {
        List<String> instructors = new ArrayList<>();
        for(CourseModel courseModel: courseModels){
            instructors.add(courseModel.getCourse_instructor().toString());
        }
        return instructors.toArray(new String[0]);
    }

    private void setupSpinner(final Spinner spinner , String[] resource_array, Consumer<Pair<Spinner, String>> callback){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                context,
                R.layout.custom_spinner,
                resource_array
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = spinner.getItemAtPosition(i).toString();
                callback.accept(new Pair<>(spinner, selectedItem));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }
}


