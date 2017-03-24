package com.example.asus.finalsattendanceapp.Models;

/**
 * Created by asus on 25/03/2017.
 */

public class UserPhoto {
    String name;
    String uri;

    public UserPhoto(){

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUri() {
        return uri;
    }

    public void setUri(String uri) {
        this.uri = uri;
    }

    public UserPhoto(String name, String uri) {

        this.name = name;
        this.uri = uri;
    }
}
