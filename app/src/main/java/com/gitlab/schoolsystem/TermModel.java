package com.gitlab.schoolsystem;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "term_table")
public class TermModel implements Parcelable {


    @PrimaryKey(autoGenerate = true)
    private int id;

    private String term_name,
    start_date,
    end_date;


    public TermModel(String term_name, String start_date, String end_date) {
        this.term_name = term_name;
        this.start_date = start_date;
        this.end_date = end_date;
    }

    protected TermModel(Parcel in) {
        term_name = in.readString();
        start_date = in.readString();
        end_date = in.readString();
    }

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

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTerm_name() {
        return term_name;
    }

    public void setTerm_name(String term_name) {
        this.term_name = term_name;
    }

    public String getStart_date() {
        return start_date;
    }

    public void setStart_date(String start_date) {
        this.start_date = start_date;
    }

    public String getEnd_date() {
        return end_date;
    }

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

    @Override
    public String toString() {
        return "TermModel{" +
                "id=" + id +
                ", term_name='" + term_name + '\'' +
                ", start_date='" + start_date + '\'' +
                ", end_date='" + end_date + '\'' +
                '}';
    }
}
