package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;

import java.util.Date;

public class TermDialog {
    private static final String TAG = "TermDialog";
    private Context context;
    private TermModelViewModel termModelViewModel;
    private TermModel current_term;
    private boolean isInserting;
    public TermDialog(Context context, TermModelViewModel termModelViewModel, TermModel current_term, boolean isInserting){
        this.context = context;
        this.termModelViewModel = termModelViewModel;
        this.current_term = current_term;
        this.isInserting = isInserting;
    }
    public void show() {
        // Term dialog  elements
        View view = LayoutInflater.from(context).inflate(R.layout.term_dialog, null);
        EditText term_name = view.findViewById(R.id.termEdit);
        EditText term_start_date = view.findViewById(R.id.startdate);
        EditText term_end_date = view.findViewById(R.id.enddate);
        new DatePicker(context, term_start_date).enable();
        new DatePicker(context, term_end_date).enable();
        // update elements
        if(!isInserting){
            term_name.setText(current_term.getTerm_name());
            term_start_date.setText(current_term.getStart_date());
            term_end_date.setText(current_term.getEnd_date());
        }
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Term Fields")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(term_name)&&!Utils.isEmpty(term_end_date) && !Utils.isEmpty(term_start_date)){
                            String start_date_ = term_start_date.getText().toString();
                            String end_date_ = term_end_date.getText().toString();
                            if(Utils.compareDates(start_date_, end_date_) < 0){
                                if(!isInserting){
                                    // insert
                                    TermModel this_model = new TermModel(term_name.getText().toString(), start_date_, end_date_);
                                    termModelViewModel.insert(new TermModel(term_name.getText().toString(), start_date_, end_date_));
                                }else{
                                    // update
                                    termModelViewModel.update(new TermModel(term_name.getText().toString(), start_date_, end_date_));
                                }
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
//        builder.create().show();
    }
}
