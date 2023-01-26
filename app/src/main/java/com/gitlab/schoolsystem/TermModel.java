package com.gitlab.schoolsystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type Term model.
 */
public class TermModel implements Parcelable {
    /**
     * The Term name.
     */
    String term_name, /**
     * The Start date.
     */
    start_date, /**
     * The End date.
     */
    end_date;

    /**
     * Instantiates a new Term model.
     */
    public TermModel(){}

    /**
     * Instantiates a new Term model.
     *
     * @param term_name  the term name
     * @param start_date the start date
     * @param end_date   the end date
     */
    public TermModel(String term_name, String start_date, String end_date) {
        this.term_name = term_name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    /**
     * Instantiates a new Term model.
     *
     * @param in the in
     */
    protected TermModel(Parcel in) {
        term_name = in.readString();
        start_date = in.readString();
        end_date = in.readString();
    }

    /**
     * The constant CREATOR.
     */
    public static final Creator<TermModel> CREATOR = new Creator<TermModel>() {
        @Override
        public TermModel createFromParcel(Parcel in) {
            return new TermModel(in);
        }

        @Override
        public TermModel[] newArray(int size) {
            return new TermModel[size];
        }
    };

    /**
     * Gets term name.
     *
     * @return the term name
     */
    public String getTerm_name() {
        return term_name;
    }

    /**
     * Sets term name.
     *
     * @param term_name the term name
     */
    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }

    /**
     * Gets start date.
     *
     * @return the start date
     */
    public String getStart_date() {
        return start_date;
    }

    /**
     * Sets start date.
     *
     * @param start_date the start date
     */
    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    /**
     * Gets end date.
     *
     * @return the end date
     */
    public String getEnd_date() {
        return end_date;
    }

    /**
     * Sets end date.
     *
     * @param end_date the end date
     */
    public void setEnd_date(String end_date) {
        this.end_date = end_date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(term_name);
        parcel.writeString(start_date);
        parcel.writeString(end_date);
    }
}
