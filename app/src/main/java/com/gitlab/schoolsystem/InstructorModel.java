package com.gitlab.schoolsystem;

public class InstructorModel {
    private String  name, phone, email_address;

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
}
