package com.gitlab.schoolsystem;

import android.annotation.SuppressLint;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class Utils {
    public static boolean isEmpty(EditText field){
        boolean is_empty = TextUtils.isEmpty(field.getText());
        if(is_empty){
            field.setError("This field is required");
            Toast.makeText(field.getContext(), "Empty field/s" , Toast.LENGTH_LONG).show();
        }else{
            field.setError(null);
        }
        return is_empty;
    }
    public static int compareDates(String date1, String date2) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date firstDate = sdf.parse(date1);
            Date secondDate = sdf.parse(date2);
            return firstDate.compareTo(secondDate);
        } catch (ParseException e) {
            e.printStackTrace();
            return 0;
        }
    }
    public static Calendar getCalendarInstance(String date_string){
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
        try {
            Date date = simpleDateFormat.parse(date_string);
            calendar.setTime(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return calendar;
    }
}
