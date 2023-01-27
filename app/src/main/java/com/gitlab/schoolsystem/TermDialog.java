package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

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
                            if(!isInserting){
                                // update
                                termModelViewModel.update(new TermModel(term_name.getText().toString(), term_start_date.getText().toString(), term_end_date.getText().toString()));
                            }else{
                                // insert
                                TermModel this_model = new TermModel(term_name.getText().toString(), term_start_date.getText().toString(), term_end_date.getText().toString());
                                Log.d(TAG, "onClick: "+ this_model.toString());
                                termModelViewModel.insert(new TermModel(term_name.getText().toString(), term_start_date.getText().toString(), term_end_date.getText().toString()));
                            }
                        }
                    }
                })
                .setNegativeButton("Cancel", null);
        builder.create().show();
    }
}
