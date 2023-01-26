package com.gitlab.schoolsystem;

/**
 * The type Note model.
 */
public class NoteModel {
    /**
     * The Note title.
     */
    public String note_title, /**
     * The Node body.
     */
    node_body;

    /**
     * Instantiates a new Note model.
     *
     * @param note_title the note title
     * @param node_body  the node body
     */
    public NoteModel(String note_title, String node_body) {
        this.note_title = note_title;
        this.node_body = node_body;
    }

    /**
     * Gets note title.
     *
     * @return the note title
     */
    public String getNote_title() {
        return note_title;
    }

    /**
     * Sets note title.
     *
     * @param note_title the note title
     */
    public void setNote_title(String note_title) {
        this.note_title = note_title;
    }

    /**
     * Gets node body.
     *
     * @return the node body
     */
    public String getNode_body() {
        return node_body;
    }

    /**
     * Sets node body.
     *
     * @param node_body the node body
     */
    public void setNode_body(String node_body) {
        this.node_body = node_body;
    }
}
