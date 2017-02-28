package com.nearsoft.androidschool.todoapp.models;

import java.io.Serializable;

public class ToDoContent implements Serializable {
    private String title;
    private String date;
    private boolean done;
    private double lat;
    private double lng;
    private boolean starred;
    private String notes;

    public ToDoContent(String title, String date, double lat, double lng) {
        this.title = title;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLng() {
        return lng;
    }

    public void setLng(double lng) {
        this.lng = lng;
    }
}
