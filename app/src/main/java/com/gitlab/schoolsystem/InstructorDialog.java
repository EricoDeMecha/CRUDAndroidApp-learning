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
    private AlertDialog instructor_dialog;
    private View instructor_dialog_view;
    private Context context;
    private static final InstructorDialog instance = new InstructorDialog();
    public static InstructorDialog getInstance(){
        return instance;
    }
    public AlertDialog buildDialog(Context context, CourseAdapter courseAdapter, InstructorModel instructor, int position) {
        this.context = context;

        // Instructor dialog  elements
        instructor_dialog_view = LayoutInflater.from(context).inflate(R.layout.instructor_dialog, null);
        EditText instructor_name = instructor_dialog_view.findViewById(R.id.instructor_name);
        EditText instructor_phone = instructor_dialog_view.findViewById(R.id.instructor_phone);
        EditText instructor_email = instructor_dialog_view.findViewById(R.id.instructor_email);
        // update elements
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
                        instructor.setName(instructor_name.getText().toString());
                        instructor.setPhone(instructor_phone.getText().toString());
                        instructor.setEmail_address(instructor_email.getText().toString());
                        courseAdapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
        instructor_dialog = builder.create();
        return instructor_dialog;
    }
}
