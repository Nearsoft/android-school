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
    private boolean notify;

    public ToDoContent(String title, Date date, double lat, double lng, boolean notify) {
        this.title = title;
        this.date = date;
        this.lat = lat;
        this.lng = lng;
        this.notify = notify;
    }

    public ToDoContent(String title, Date date) {
        this.title = title;
        this.date = date;
    }

    public ToDoContent() {
        this.date = new Date();
    }

    public boolean isNotify() {
        return notify;
    }

    public void setNotify(boolean notify) {
        this.notify = notify;
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
        return this.date != null;
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
