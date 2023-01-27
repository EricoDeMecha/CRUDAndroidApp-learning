package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class AssessmentDialog {
    private Context context;
    private AssessmentModelViewModel assessmentModelViewModel;
    private AssessmentModel assessmentModel;
    private boolean isInserting;

    public AssessmentDialog(Context context, AssessmentModelViewModel assessmentModelViewModel, AssessmentModel assessmentModel, boolean isInserting){
        this.context = context;
        this.assessmentModelViewModel = assessmentModelViewModel;
        this.isInserting = isInserting;
        this.assessmentModel = assessmentModel;
    }

    public void show() {
        // Note dialog  elements
        View view = LayoutInflater.from(context).inflate(R.layout.assessment_dialog, null);
        EditText assessment_title = view.findViewById(R.id.assessment_title_edit);
        EditText assessment_due_date = view.findViewById(R.id.due_date_edit_2);

        new DatePicker(context, assessment_due_date).enable();
        // update elements
        if(!isInserting && assessmentModel != null) {
            // requires update
            assessment_title.setText(assessmentModel.getName());
            assessment_due_date.setText(assessmentModel.getDue_date());
        }
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Assessment details")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(assessment_title) && !Utils.isEmpty(assessment_due_date)){
                            if(isInserting){
                                assessmentModelViewModel.insert(new AssessmentModel(assessment_title.getText().toString(), assessment_due_date.getText().toString()));
                            }else{
                                assessmentModelViewModel.update(new AssessmentModel(assessment_title.getText().toString(), assessment_due_date.getText().toString()));
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
        AlertDialog dialog = builder.create();
        dialog.getWindow().setBackgroundDrawableResource(R.drawable.rounded_corners_background);
        dialog.show();
    }
}
