package com.example.asus.finalsattendanceapp.Models;

/**
 * Created by asus on 22/03/2017.
 */

public class StudentModel {
    String displayName;
    String emailID;
    String id;
    String uri;

    public StudentModel(){

    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getEmailID() {
        return emailID;
    }

    public void setEmailID(String emailID) {
        this.emailID = emailID;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public StudentModel(String displayName, String emailID, String id, String uri) {

        this.displayName = displayName;
        this.emailID = emailID;
        this.id = id;
        this.uri = uri;
    }
}
