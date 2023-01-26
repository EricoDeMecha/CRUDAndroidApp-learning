package com.gitlab.schoolsystem;

import android.text.TextUtils;
import android.widget.EditText;

public class Utils {
    public static boolean isEmpty(EditText field){
        boolean is_empty = TextUtils.isEmpty(field.getText());
        if(is_empty){
            field.setError("This field is required");
        }else{
            field.setError(null);
        }
        return is_empty;
    }
}
