package com.gitlab.schoolsystem;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "instructor_table")
public class InstructorModel implements Parcelable {

    @PrimaryKey(autoGenerate = true)
    private int instructor_id;
    private String  name, phone, email_address;

    @Ignore
    public InstructorModel(String name){
        this.name = name;
        this.phone = "123456789";
        this.email_address = "generic@gmail.com";
    }

    public InstructorModel(String name, String phone, String email_address) {
        this.name = name;
        this.phone = phone;
        this.email_address = email_address;
    }

    protected InstructorModel(Parcel in) {
        name = in.readString();
        phone = in.readString();
        email_address = in.readString();
    }

    public static final Creator<InstructorModel> CREATOR = new Creator<InstructorModel>() {
        @Override
        public InstructorModel createFromParcel(Parcel in) {
            return new InstructorModel(in);
        }

        @Override
        public InstructorModel[] newArray(int size) {
            return new InstructorModel[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail_address() {
        return email_address;
    }

    public void setEmail_address(String email_address) {
        this.email_address = email_address;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(phone);
        parcel.writeString(email_address);
    }

    @Override
    public String toString() {
        return "Instructor{" +
                "name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email_address='" + email_address + '\'' +
                '}';
    }
    public void setInstructor_id(int instructor_id) {
        this.instructor_id = instructor_id;
    }

    public int getInstructor_id() {
        return instructor_id;
    }

}
