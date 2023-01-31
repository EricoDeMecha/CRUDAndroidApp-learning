package com.gitlab.schoolsystem;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class NoteDialog {
    private Context context;
    private NoteModelViewModel noteModelViewModel;
    private NoteModel noteModel;
    private boolean isInserting;
    private String course_title;
    public NoteDialog(Context context, NoteModelViewModel noteModelViewModel, NoteModel noteModel, String course_title, boolean isInserting){
        this.context = context;
        this.noteModelViewModel = noteModelViewModel;
        this.isInserting = isInserting;
        this.noteModel = noteModel;
        this.course_title  = course_title;
    }

    public void show(){
        // Note dialog  elements
        View view = LayoutInflater.from(context).inflate(R.layout.note_dialog, null);
        EditText note_title = view.findViewById(R.id.note_title_edit_1);
        EditText note_body = view.findViewById(R.id.note_body_edit_1);
        // update elements
        if(!isInserting){
            // is update enabled
            note_title.setText(noteModel.getNote_title());
            note_body.setText(noteModel.getNode_body());
        }
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Note details")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(note_title) && !Utils.isEmpty(note_body)){
                            if(isInserting){
                                NoteModel new_note = new NoteModel(note_title.getText().toString(), note_body.getText().toString());
                                new_note.setCourse_title(course_title);
//                                Log.d(TAG, "onClick: "+ new_note.toString());
                                noteModelViewModel.insert(new_note);
                            }else {
                                noteModel.setNote_title(note_title.getText().toString());
                                noteModel.setNode_body(note_body.getText().toString());
                                noteModelViewModel.update(noteModel);
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
