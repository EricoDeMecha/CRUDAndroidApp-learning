package com.gitlab.schoolsystem;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class CourseNotesFragment extends Fragment {
    private NoteModelViewModel noteModelViewModel;
    private NoteAdapter noteAdapter;
    private CourseModel courseModel;
    private static String course_title;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View fragment_view = inflater.inflate(R.layout.fragment_course_notes, container, false);
        RecyclerView recyclerView = fragment_view.findViewById(R.id.note_recycler_view);
        NoteAdapter noteAdapter = new NoteAdapter();
        int NUM_COLS = 3;
        recyclerView.setLayoutManager(new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL));
        recyclerView.setAdapter(noteAdapter);
        // retrieve course model
        courseModel = ((CourseChildrenActivity) requireActivity()).getCourseModel();

        course_title = courseModel.getCourse_title();

        noteModelViewModel = ViewModelProviders.of(requireActivity()).get(NoteModelViewModel.class);
        noteModelViewModel.getNotesByCourseName(course_title).observe(requireActivity(), new Observer<List<NoteModel>>() {
            @Override
            public void onChanged(List<NoteModel> noteModelList) {
                noteAdapter.setNoteModelList(noteModelList);
            }
        });

        FloatingActionButton note_add_button = fragment_view.findViewById(R.id.add_note_button);
        note_add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new NoteDialog(requireContext(), noteModelViewModel, null, course_title,true).show();
            }
        });
        noteAdapter.setNoteListener(new NoteAdapter.OnItemListener() {
            @Override
            public void onItemClicked(NoteModel note) {
                new NoteDialog(requireContext(), noteModelViewModel,  note, course_title,false).show();
            }

            @Override
            public void onDeleteClicked(NoteModel note) {
                AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
                builder.setTitle("Are you sure?");
                builder.setMessage("Operation cannot be undone.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        noteModelViewModel.delete(note);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                builder.show();
            }
        });
        return fragment_view;
    }
    private void getNotes(){
        /*TODO; get the notes from the database*/

    }
}
