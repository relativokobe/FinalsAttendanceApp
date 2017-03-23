package com.example.asus.finalsattendanceapp.Models;

/**
 * Created by asus on 23/03/2017.
 */

public class SessionModelWithType {
    String date;
    String timeStart;
    String timeEnd;
    String location;
    String id;
    String type;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public SessionModelWithType(String date, String timeStart, String timeEnd, String location, String id, String type) {

        this.date = date;
        this.timeStart = timeStart;
        this.timeEnd = timeEnd;
        this.location = location;
        this.id = id;
        this.type = type;
    }
}
