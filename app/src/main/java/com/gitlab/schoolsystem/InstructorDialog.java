package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class InstructorDialog {

    private Context context;
    private CourseModelViewModel courseModelViewModel;
    private CourseModel courseModel;
    private InstructorModel instructor;

    public InstructorDialog(Context context, CourseModelViewModel courseModelViewModel, CourseModel courseModel){
        this.context = context;
        this.courseModelViewModel = courseModelViewModel;
        this.courseModel = courseModel;
    }

    public void show() {
        // Instructor dialog  elements
        View  instructor_dialog_view = LayoutInflater.from(context).inflate(R.layout.instructor_dialog, null);
        EditText instructor_name = instructor_dialog_view.findViewById(R.id.instructor_name);
        EditText instructor_phone = instructor_dialog_view.findViewById(R.id.instructor_phone);
        EditText instructor_email = instructor_dialog_view.findViewById(R.id.instructor_email);
        // update elements
            instructor = courseModel.getCourse_instructor();
            instructor_name.setText(instructor.getName());
            instructor_phone.setText(instructor.getPhone());
            instructor_email.setText(instructor.getEmail_address());
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(instructor_dialog_view);
        builder.setTitle("Instructor's details")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(instructor_name) && !Utils.isEmpty(instructor_phone) && !Utils.isEmpty(instructor_email)){
                            String instructor_name_ = instructor_name.getText().toString();
                            String instructor_phone_ = instructor_phone.getText().toString();
                            String instructor_email_ = instructor_email.getText().toString();
                            InstructorModel new_instructor = new InstructorModel(instructor_name_, instructor_phone_, instructor_email_);
                            courseModel.setCourse_instructor(new_instructor);
                            courseModelViewModel.update(courseModel);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
     builder.create().show();
    }
}
