package com.mohid.masu.admin.model;

public class StudentRow {

    private String id;
    private String fullName;
    private String username;
    private String gender;
    private String status;
    private String passwordDisplay;

    public StudentRow(String id, String fullName, String username, String gender, String status, String passwordDisplay) {
        this.id = id;
        this.fullName = fullName;
        this.username = username;
        this.gender = gender;
        this.status = status;
        this.passwordDisplay = passwordDisplay;
    }

    public String getId() {
        return id;
    }

    public String getFullName() {
        return fullName;
    }

    public String getUsername() {
        return username;
    }

    public String getGender() {
        return gender;
    }

    public String getStatus() {
        return status;
    }

    public String getPasswordDisplay() {
        return passwordDisplay;
    }
}