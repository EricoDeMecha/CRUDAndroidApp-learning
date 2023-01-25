package com.gitlab.schoolsystem;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;


import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements TermAdapter.OnTermListener{
    // dialog items
    AlertDialog dialog;
    final Calendar m_calendar = Calendar.getInstance();
    private View dialog_view;
    private FloatingActionButton add;
    private EditText term_name;
    private EditText m_start_date , m_end_date;
    private LinearLayout layout;

    RecyclerView recyclerView;
    TermAdapter termAdapter;
    ArrayList<TermModel> termModelList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_screen);

        add = findViewById(R.id.add);

        buildTermDialog();

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.show();;
            }
        });


        termModelList = new ArrayList<>();
        recyclerView =  findViewById(R.id.recycleview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        termAdapter = new TermAdapter( this, termModelList, this);
        recyclerView.setAdapter(termAdapter);

    }
    private void clearDialogFields(){
        term_name.getText().clear();
        m_start_date.getText().clear();
        m_end_date.getText().clear();
    }
    private void buildTermDialog() {
        dialog_view = getLayoutInflater().inflate(R.layout.term_dialog, null) ;
        m_start_date = dialog_view.findViewById(R.id.startdate);
        m_end_date = dialog_view.findViewById(R.id.enddate);
        DatePicker.getInstance().selectDate(this, m_start_date);
        DatePicker.getInstance().selectDate(this, m_end_date);
/*        selectDate(m_start_date);
        selectDate(m_end_date);*/
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        term_name = dialog_view.findViewById(R.id.termEdit);
        builder.setView(dialog_view);
        builder.setTitle("Enter term name:")
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        termModelList.add(new TermModel(term_name.getText().toString(), m_start_date.getText().toString(), m_end_date.getText().toString()));
                        termAdapter.notifyItemInserted(termModelList.size()-1);
                        clearDialogFields();
                        recyclerView.scrollToPosition(termModelList.size()-1);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        clearDialogFields();
                    }
                });
        dialog = builder.create();
    }
    /*private void selectDate(EditText date_view){
        date_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                        m_calendar.set(Calendar.YEAR, year);
                        m_calendar.set(Calendar.MONTH, month);
                        m_calendar.set(Calendar.DAY_OF_MONTH, day);
                        date_view.setText(updateDate());
                    }
                };
                new DatePickerDialog(MainActivity.this, date, m_calendar.get(Calendar.YEAR), m_calendar.get(Calendar.MONTH), m_calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }
    private String updateDate() {
        String m_format = "yyyy-MM-dd";
        SimpleDateFormat date_format = new SimpleDateFormat(m_format, Locale.US);
        return date_format.format(m_calendar.getTime());
    }*/

    @Override
    public void onTermClicked(int position) {
        Intent intent = new Intent(this, CourseActivity.class);
        intent.putExtra("Term", termModelList.get(position));
        startActivity(intent);
    }
}