package com.gitlab.schoolsystem;

import androidx.room.TypeConverter;

public class CourseStatusConverter {
    @TypeConverter
    public static CourseStatus fromString(String value){
        return CourseStatus.valueOf(value);
    }
    @TypeConverter
    public static String fromEnum(CourseStatus status){
        return status.name();
    }
}
