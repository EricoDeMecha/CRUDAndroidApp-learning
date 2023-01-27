package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

public class NoteDialog {
    private Context context;
    private NoteModelViewModel noteModelViewModel;
    private NoteModel noteModel;
    private boolean isInserting;

    public NoteDialog(Context context, NoteModelViewModel noteModelViewModel, NoteModel noteModel, boolean isInserting){
        this.context = context;
        this.noteModelViewModel = noteModelViewModel;
        this.isInserting = isInserting;
        this.noteModel = noteModel;
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
                                noteModelViewModel.insert(new NoteModel(note_title.getText().toString(), note_body.getText().toString()));
                            }else {
                                noteModelViewModel.update(new NoteModel(note_title.getText().toString(), note_body.getText().toString()));
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
