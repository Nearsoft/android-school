package com.nearsoft.androidschool.todoapp.models;

import java.io.Serializable;
import java.util.Date;

public class ToDoContent implements Serializable {
    private String title;
    private Date date;
    private boolean done;
    private String location;
    private boolean starred;
    private String notes;
    private boolean hasDate;

    public ToDoContent(String title, Date date, boolean hasDate, String location) {
        this.title = title;
        this.date = date;
        this.location = location;
        this.hasDate = hasDate;
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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
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
}
