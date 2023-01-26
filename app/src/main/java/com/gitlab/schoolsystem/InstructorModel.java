package com.gitlab.schoolsystem;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The type Instructor model.
 */
public class InstructorModel implements Parcelable {
    private String  name, phone, email_address;

    /**
     * Instantiates a new Instructor model.
     *
     * @param name the name
     */
    public InstructorModel(String name){
        this.name = name;
        this.phone = "123456789";
        this.email_address = "generic@gmail.com";
    }

    /**
     * Instantiates a new Instructor model.
     *
     * @param name          the name
     * @param phone         the phone
     * @param email_address the email address
     */
    public InstructorModel(String name, String phone, String email_address) {
        this.name = name;
        this.phone = phone;
        this.email_address = email_address;
    }

    /**
     * Instantiates a new Instructor model.
     *
     * @param in the in
     */
    protected InstructorModel(Parcel in) {
        name = in.readString();
        phone = in.readString();
        email_address = in.readString();
    }

    /**
     * The constant CREATOR.
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets phone.
     *
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets phone.
     *
     * @param phone the phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets email address.
     *
     * @return the email address
     */
    public String getEmail_address() {
        return email_address;
    }

    /**
     * Sets email address.
     *
     * @param email_address the email address
     */
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
}
