package com.gitlab.schoolsystem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity{
    public static final String TITLE =
            "com.gitlab.schoolsystem.TermModel";
    private TermAdapter termAdapter;
    private TermModelViewModel termModelViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        RecyclerView recyclerView = findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter = new TermAdapter();
        recyclerView.setAdapter(termAdapter);

        termModelViewModel = ViewModelProviders.of(this).get(TermModelViewModel.class);
        termModelViewModel.getAllTerms().observe(this, new Observer<List<TermModel>>() {
            @Override
            public void onChanged(List<TermModel> termModels) {
                termAdapter.setTermList(termModels);
            }
        });
        FloatingActionButton add = findViewById(R.id.add);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new TermDialog(MainActivity.this, termModelViewModel, null, true).show();
            }
        });
        // add swipe right or left to delete
        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0,
                ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                termModelViewModel.delete(termAdapter.getTermAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Note  deleted", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);
        // set listeners
        termAdapter.setListenerOnClick(new TermAdapter.OnTermListener() {
            @Override
            public void onTermClicked(TermModel term) {
                Intent  intent  = new Intent(MainActivity.this, CourseActivity.class);
                intent.putExtra(MainActivity.TITLE, term);
                startActivity(intent);
            }

            @Override
            public void onUpdateButtonClicked(TermModel term) {
                // create a dialog that reports back here
                new TermDialog(MainActivity.this,  termModelViewModel, term, false).show();
            }
        });
    }
}