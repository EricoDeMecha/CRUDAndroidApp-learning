package com.gitlab.schoolsystem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Consumer;

public class CourseActivity extends AppCompatActivity implements CourseAdapter.OnCourseListener{
    private static final String TAG = "CourseActivity";
    private static final int NUM_COLS  = 2;
    private final List<CourseModel> courseModelList = new ArrayList<>();
    private FloatingActionButton add_course_btn;
    private CourseAdapter courseAdapter;
    private RecyclerView recyclerView;
    // course dialog
    private  AlertDialog course_dialog;
    private View course_dialog_view;
    private final Calendar calendar = Calendar.getInstance();
    TermModel termModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.list_of_courses);

        if(getIntent().hasExtra("Term")){
            termModel = getIntent().getParcelableExtra("Term");
        }
        // set up a back button on the toolbar
        ActionBar actionBar  = getSupportActionBar();
        actionBar.setTitle(termModel.getTerm_name());
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);
        // TODO Use term details to update the course details
        courseModelList.add(new CourseModel(
           "Course1",
           "2020-03-01",
           "2020-10-01",
           CourseStatus.INPROGRESS,
           new InstructorModel("Instructor1", "12345678", "instructor1@gmail.coc")
        ));
        courseModelList.add(new CourseModel(
                "Course2",
                "2020-03-01",
                "2020-10-01",
                CourseStatus.INPROGRESS,
                new InstructorModel("Instructor2", "12345678", "instructor1@gmail.coc")
        ));

        courseModelList.add(new CourseModel(
                "Course3",
                "2020-03-01",
                "2020-10-01",
                CourseStatus.INPROGRESS,
                new InstructorModel("Instructor3", "12345678", "instructor1@gmail.coc")
        ));
        courseModelList.add(new CourseModel(
                "Course4",
                "2020-03-01",
                "2020-10-01",
                CourseStatus.INPROGRESS,
                new InstructorModel("Instructor4", "12345678", "instructor1@gmail.coc")
        ));

        // init recyclerview
        initRecyclerView();
        // build dialog window
        buildCourseDialog();
        add_course_btn = findViewById(R.id.add_course);
        add_course_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                course_dialog.show();
            }
        });
    }
    public void initRecyclerView(){
        recyclerView= findViewById(R.id.courses_recycler_view);
        courseAdapter = new CourseAdapter(this, courseModelList, this);
        StaggeredGridLayoutManager staggeredGridLayoutManager = new StaggeredGridLayoutManager(NUM_COLS, LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(staggeredGridLayoutManager);
        recyclerView.setAdapter(courseAdapter);
    }
    public void buildCourseDialog(){
        course_dialog_view = getLayoutInflater().inflate(R.layout.course_dialog, null);
        // Course title
        EditText course_title_view = course_dialog_view.findViewById(R.id.coursetitle_edit);
        // course start date
        EditText course_start_date = course_dialog_view.findViewById(R.id.startdate_edit);
        DatePicker.getInstance().selectDate(this, course_start_date);
        // course end date
        EditText course_end_date = course_dialog_view.findViewById(R.id.enddate_edit);
        DatePicker.getInstance().selectDate(this, course_end_date);
        //  course status spinner
        Spinner status_spinner = course_dialog_view.findViewById(R.id.status_spinner);
        String[] status_resource_array = getResources().getStringArray(R.array.course_status_list);
        AtomicReference<String> selected_status  = new AtomicReference<>();
        setupSpinner(status_spinner, status_resource_array, (pair)->{
            Spinner spinner = pair.first;
            selected_status.set(pair.second);
        } );
        // instructor spinner
        Spinner instructor_spinner = course_dialog_view.findViewById(R.id.instructor_spinner);
        String[] instructor_resource_array =  getInstructorNames();
        AtomicReference<String> selected_instructor = new AtomicReference<>();
        setupSpinner(instructor_spinner, instructor_resource_array, (pair)->{
            Spinner spinner = pair.first;
            selected_instructor.set(pair.second);
        });
        // builder
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setView(course_dialog_view);
        builder.setTitle("Enter the course details")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        courseModelList.add(new CourseModel(
                                course_title_view.getText().toString(),
                                course_start_date.getText().toString(),
                                course_end_date.getText().toString(),
                                CourseStatus.valueOf(selected_status.toString()),
                                new InstructorModel(selected_instructor.toString())));
                        courseAdapter.notifyItemInserted(courseModelList.size()-1);
                        clearCourseDialogFields();
                        recyclerView.scrollToPosition(courseModelList.size()-1);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearCourseDialogFields();
                    }
                });
        course_dialog  = builder.create();
    }
    private void setupSpinner(final Spinner spinner , String[] resource_array, Consumer<Pair<Spinner, String>>callback){
        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                R.layout.custom_spinner,
                resource_array
        );
        adapter.setDropDownViewResource(R.layout.custom_spinner_dropdown);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem = spinner.getItemAtPosition(i).toString();
                callback.accept(new Pair<>(spinner, selectedItem));
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

    private void clearCourseDialogFields(){
        /*TODO- clear dialog fields*/
    }
    public String[] getInstructorNames(){
        // TODO : retrieve this from the database
        String[] string_array = {"instructor1", "instructor2", "instructor3", "instructor4"};
        return string_array;
    }

    @Override
    public void onCourseClicked(int position) {
        Intent intent = new Intent(this, Assessment.class);
        intent.putExtra("Course", (CharSequence) courseModelList.get(position));
        startActivity(intent);
    }
}