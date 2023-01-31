package com.gitlab.schoolsystem;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Parcelable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class AssessmentDialog {
    private Context context;
    private AssessmentModelViewModel assessmentModelViewModel;
    private AssessmentModel assessmentModel;
    private boolean isInserting;
    private String course_title;

    public AssessmentDialog(Context context, AssessmentModelViewModel assessmentModelViewModel, AssessmentModel assessmentModel, String course_title,boolean isInserting){
        this.context = context;
        this.assessmentModelViewModel = assessmentModelViewModel;
        this.isInserting = isInserting;
        this.assessmentModel = assessmentModel;
        this.course_title = course_title;
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
                                AssessmentModel new_model = new AssessmentModel(assessment_title.getText().toString(), assessment_due_date.getText().toString());
                                new_model.setCourse_title(course_title);
                                assessmentModelViewModel.insert(new_model);
                            }else{
                                // update  the model
                                String temp_date = assessment_due_date.getText().toString();
                                assessmentModel.setName(assessment_title.getText().toString());
                                assessmentModel.setDue_date(temp_date);
                                assessmentModelViewModel.update(assessmentModel);
                                // set notification
                                Calendar calendar = Utils.getCalendarInstance(temp_date);
                                if(calendar != null){
                                    scheduleNotification(calendar);
                                    Toast.makeText(context,"Notification scheduled for "+temp_date , Toast.LENGTH_LONG).show();
                                }
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
    public void scheduleNotification(Calendar calendar) {
        Intent intent = new Intent(context, AlarmReceiver.class);
        intent.setAction("com.example.ALARM_TRIGGERED");
        PendingIntent pIntent = PendingIntent.getBroadcast(context, 1,
                intent, PendingIntent.FLAG_IMMUTABLE);
        AlarmManager alarmManager = (AlarmManager) context.getSystemService(context.ALARM_SERVICE);
        alarmManager.setInexactRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pIntent);
    }
}
