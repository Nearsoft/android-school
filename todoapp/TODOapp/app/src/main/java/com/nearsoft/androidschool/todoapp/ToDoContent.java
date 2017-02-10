package com.nearsoft.androidschool.todoapp;


/**
 * Created by Ramon on 2/1/2017.
 */
public class ToDoContent{
    //TODO: define contents of a single item
    private String title;
    private String date;
    private boolean done;
    private String location;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
