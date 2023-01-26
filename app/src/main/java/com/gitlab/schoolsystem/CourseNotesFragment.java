package com.gitlab.schoolsystem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CourseNotesFragment extends Fragment {
    // add note dialog components
    private AlertDialog  note_dialog;
    private View note_dialog_view;

    private FloatingActionButton note_add_button;
    private final List<NoteModel> noteModelList = new ArrayList<>();
    private NoteAdapter noteAdapter;
    private RecyclerView recyclerView;
    private final int NUM_COLS = 3;
    private View fragment_view;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        fragment_view = inflater.inflate(R.layout.fragment_course_notes, container, false);

        // prepopulate the notelist
        getNotes();

        initRecyclerView();
        buildNoteDialog();
        note_add_button = fragment_view.findViewById(R.id.add_note_button);
        note_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                note_dialog.show();
            }
        });
        return fragment_view;
    }
    public void buildNoteDialog(){
        note_dialog_view = getLayoutInflater().inflate(R.layout.add_note_dialog, null);
        //  note title
        EditText note_title = note_dialog_view.findViewById(R.id.note_title_edit);
        // note body
        EditText note_body = note_dialog_view.findViewById(R.id.note_body_edit);
        // builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this.requireContext());
        builder.setView(note_dialog_view);
        builder.setTitle("Draft note")
                .setPositiveButton("Save", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        if(!Utils.isEmpty(note_title) && !Utils.isEmpty(note_body)){
                            noteModelList.add(new NoteModel(
                                    note_title.getText().toString(),
                                    note_body.getText().toString()
                            ));
                            noteAdapter.notifyItemInserted(noteModelList.size()-1);
                        }
                    }
                })
                .setNegativeButton("Discard", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        /*Clear  and note */
                        note_body.getText().clear();
                        note_title.getText().clear();
                    }
                });
        note_dialog = builder.create();
    }
    private void initRecyclerView(){
        recyclerView = fragment_view.findViewById(R.id.note_recycler_view);
        noteAdapter = new NoteAdapter(getContext(), noteModelList);
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(noteAdapter);
    }
    private void getNotes(){
        /*TODO; get the notes from the database*/
        noteModelList.add(new NoteModel("Title1", "Body1"));
        noteModelList.add(new NoteModel("Title2", "Body2"));
        noteModelList.add(new NoteModel("Title3", "Body3"));
        noteModelList.add(new NoteModel("Title4", "Body4"));
        noteModelList.add(new NoteModel("Title5", "Body5"));
    }
}
