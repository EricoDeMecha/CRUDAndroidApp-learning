package com.gitlab.schoolsystem;

public class NoteModel {
    public String note_title, node_body;

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
}
