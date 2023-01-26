package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

/**
 * The type Assessment dialog.
 */
public class AssessmentDialog {
    private AlertDialog dialog;
    private View view;
    private Context context;
    private static final AssessmentDialog instance = new AssessmentDialog();

    /**
     * Get instance assessment dialog.
     *
     * @return the assessment dialog
     */
    public static AssessmentDialog getInstance(){
        return instance;
    }

    /**
     * Build dialog alert dialog.
     *
     * @param context           the context
     * @param assessmentAdapter the assessment adapter
     * @param assessment        the assessment
     * @param position          the position
     * @return the alert dialog
     */
    public AlertDialog buildDialog(Context context, AssessmentAdapter assessmentAdapter, AssessmentModel assessment, int position) {
        this.context = context;
        // Note dialog  elements
        view = LayoutInflater.from(context).inflate(R.layout.assessment_dialog, null);
        EditText assessment_title = view.findViewById(R.id.assessment_title_edit);
        EditText assessment_due_date = view.findViewById(R.id.due_date_edit_2);
        // update elements
        assessment_title.setText(assessment.getName());
        assessment_due_date.setText(assessment.getDue_date());
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Assessment details")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(assessment_title) && !Utils.isEmpty(assessment_due_date)){
                            assessment.setName(assessment_title.getText().toString());
                            assessment.setDue_date(assessment_due_date.getText().toString());
                            assessmentAdapter.notifyItemChanged(position);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // do nothing
                    }
                });
        dialog = builder.create();
        return dialog;
    }
}
