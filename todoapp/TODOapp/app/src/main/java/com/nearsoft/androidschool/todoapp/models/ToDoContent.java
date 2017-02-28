package com.nearsoft.androidschool.todoapp.models;

import java.io.Serializable;
import java.util.Date;

public class ToDoContent implements Serializable {
    private String title;
    private Date date;
    private boolean done;
    private double lat;
    private double lng;
    private boolean starred;
    private String notes;
    private boolean hasDate;

    public ToDoContent(String title, Date date, boolean hasDate, double lat, double lng) {
        this.title = title;
        this.date = date;
        this.hasDate = hasDate;
        this.lat = lat;
        this.lng = lng;
    }

    public ToDoContent() {
        this.date = new Date();
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

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
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

    public boolean hasDate() {
        return this.hasDate;
    }

    public void setHasDate(boolean hasDate) {
        this.hasDate = hasDate;
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
