package com.gitlab.schoolsystem;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;

/**
 * The type Note dialog.
 */
public class NoteDialog {
    private AlertDialog dialog;
    private View view;
    private Context context;
    private static final NoteDialog instance = new NoteDialog();

    /**
     * Get instance note dialog.
     *
     * @return the note dialog
     */
    public static NoteDialog getInstance(){
        return instance;
    }

    /**
     * Build dialog alert dialog.
     *
     * @param context     the context
     * @param noteAdapter the note adapter
     * @param note        the note
     * @param position    the position
     * @return the alert dialog
     */
    public AlertDialog buildDialog(Context context, NoteAdapter noteAdapter, NoteModel note, int position) {
        this.context = context;

        // Note dialog  elements
        view = LayoutInflater.from(context).inflate(R.layout.note_dialog, null);
        EditText note_title = view.findViewById(R.id.note_title_edit_1);
        EditText note_body = view.findViewById(R.id.note_body_edit_1);
        // update elements
        note_title.setText(note.getNote_title());
        note_body.setText(note.getNode_body());
        // build the dialog
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setView(view);
        builder.setTitle("Note_details details")
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(note_title) && !Utils.isEmpty(note_body)){
                            note.setNote_title(note_title.getText().toString());
                            note.setNode_body(note_body.getText().toString());
                            noteAdapter.notifyItemChanged(position);
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
