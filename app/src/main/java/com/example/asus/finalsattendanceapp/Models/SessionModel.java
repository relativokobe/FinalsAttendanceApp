package com.example.asus.finalsattendanceapp.Models;

import android.text.format.Time;

import java.util.Date;

/**
 * Created by asus on 21/03/2017.
 */

public class SessionModel   {
    String date;
    String timeStart;
    String timeEnd;
    String location;
    String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public SessionModel(){

    }
    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeStart() {
        return timeStart;
    }

    public void setTimeStart(String timeStart) {
        this.timeStart = timeStart;
    }

    public String getTimeEnd() {
        return timeEnd;
    }

    public void setTimeEnd(String timeEnd) {
        this.timeEnd = timeEnd;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public SessionModel(String date, String timeStart, String timeEnd, String location, String id) {

        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.location = location;
        this.id = id;
    }

}
