package com.gitlab.schoolsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NavUtils;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class CourseActivity extends AppCompatActivity{
    public static final String TITLE =
            "com.gitlab.schoolsystem.TermModel";
    public static final String TITLE2 =
            "com.gitlab.schoolsystem.CourseModel";
    private static final String TAG = "CourseActivity";
    private static final int NUM_COLS  = 2;

    private TermModel termModel;

    private CourseModelViewModel courseModelViewModel;
    private CourseAdapter courseAdapter;
    @Override
    public boolean onSupportNavigateUp() {
        NavUtils.navigateUpFromSameTask(this);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_courses);

        if(getIntent().hasExtra(CourseActivity.TITLE)){
            termModel = getIntent().getParcelableExtra(CourseActivity.TITLE);
        }
        // set up a back button on the toolbar
        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            Intent data = getIntent();
            if(data.hasExtra(CourseActivity.TITLE2)){
                actionBar.setTitle(data.getStringExtra(CourseActivity.TITLE2));
            }
            if(termModel != null){
                actionBar.setTitle(termModel.getTerm_name());
            }
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        // init recyclerview
        //    private CourseAdapter courseAdapter;
        RecyclerView recyclerView = findViewById(R.id.courses_recycler_view);
        courseAdapter = new CourseAdapter();
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(courseAdapter);


        courseModelViewModel = ViewModelProviders.of(this).get(CourseModelViewModel.class);
        courseModelViewModel.getAllCourses().observe(this, new Observer<List<CourseModel>>() {
            @Override
            public void onChanged(List<CourseModel> courseModels) {
                courseAdapter.setCourseModelList(courseModels);
            }
        });
        // add dialog
        FloatingActionButton add_course_btn = findViewById(R.id.add_course);
        add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                public CourseDialog(Context context, CourseModelViewModel courseModelViewModel, CourseModel courseModel, boolean isInserting){
                List<CourseModel> temp_courses =  courseModelViewModel.getAllCourses().getValue();
                new CourseDialog(CourseActivity.this, courseModelViewModel, temp_courses).show();
            }
        });
        // delete dialog
        courseAdapter.setCourseListerOnClick(new CourseAdapter.OnCourseListener() {
            @Override
            public void onCourseClicked(CourseModel course) {
                Intent intent = new Intent(CourseActivity.this, CourseChildrenActivity.class);
                intent.putExtra(CourseActivity.TITLE2, course);
                startActivity(intent);
            }

            @Override
            public void onDeleteClicked(CourseModel course) {
                // create a delete confirmation dialog
                AlertDialog.Builder builder = new AlertDialog.Builder(CourseActivity.this);
                builder.setTitle("Are you sure?");
                builder.setMessage("Operation cannot be undone.");
                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        // deleted selected course
                        courseModelViewModel.delete(course);
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(CourseActivity.this, "Delete canceled", Toast.LENGTH_SHORT).show();
                    }
                });
                builder.show();
            }

            @Override
            public void onInstructorClicked(CourseModel course) {
                // create a dialog with instructor details
                //     public InstructorDialog(Context context, CourseModelViewModel courseModelViewModel, CourseModel courseModel){
                new InstructorDialog(CourseActivity.this, courseModelViewModel, course).show();
            }
        });
    }
}