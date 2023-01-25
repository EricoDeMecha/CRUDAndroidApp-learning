package com.gitlab.schoolsystem;

import android.app.DatePickerDialog;
import android.content.Context;
import android.view.View;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class DatePicker {
    private static final DatePicker instance = new DatePicker();
    private Calendar calendar;

    private DatePicker() {
        calendar = Calendar.getInstance();
    }

    public static DatePicker getInstance() {
        return instance;
    }

    public void selectDate( Context context, EditText date_view) {
        date_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(android.widget.DatePicker datePicker, int year, int month, int day) {
                        calendar.set(Calendar.YEAR, year);
                        calendar.set(Calendar.MONTH, month);
                        calendar.set(Calendar.DAY_OF_MONTH, day);
                        date_view.setText(updateDate());
                    }
                };
                new DatePickerDialog(context, date, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    private String updateDate() {
        String m_format = "yyyy-MM-dd";
        SimpleDateFormat date_format = new SimpleDateFormat(m_format, Locale.US);
        return date_format.format(calendar.getTime());
    }
}
