[\[Paso Anterior\]](02_db_helper.md)

# Todo Content Refactor

En este punto ya tenemos `TodoDbContract` y `DbHelper` pero antes de continuar necesitamos modificar nuestra clase `ToDoContent`.

Lo que necesitamos cambiar es primeramente agregar un campu `id` y remover la inicializacion de `Date` que esta en el constructor como pueden ver a continuacion

```java
package com.nearsoft.androidschool.todoapp.models;

import java.io.Serializable;
import java.util.Date;

public class ToDoContent implements Serializable {
    private Long id;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

```

[\[Siguiente Paso\]](04_todo_db_helper.md)
