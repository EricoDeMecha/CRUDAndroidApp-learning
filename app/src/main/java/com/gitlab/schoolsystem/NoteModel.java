package com.gitlab.schoolsystem;

import static androidx.room.ForeignKey.CASCADE;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "note_table")
public class NoteModel {

    @PrimaryKey( autoGenerate = true)
    int id;

    @ForeignKey(entity = CourseModel.class, parentColumns = "course_title", childColumns = "course_title", onDelete = CASCADE)
    private String course_title;

    public String note_title,
    node_body;

    public NoteModel(String note_title, String node_body) {
        this.note_title = note_title;
        this.node_body = node_body;
    }

    public String getNote_title() {
        return note_title;
    }

    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    public String getNode_body() {
        return node_body;
    }

    public void setNode_body(String node_body) {
        this.node_body = node_body;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    public String getCourse_title() {
        return course_title;
    }

    public void setCourse_title(String course_title) {
        this.course_title = course_title;
    }

    @Override
    public String toString() {
        return "NoteModel{" +
                "id=" + id +
                ", course_title='" + course_title + '\'' +
                ", note_title='" + note_title + '\'' +
                ", node_body='" + node_body + '\'' +
                '}';
    }
}
