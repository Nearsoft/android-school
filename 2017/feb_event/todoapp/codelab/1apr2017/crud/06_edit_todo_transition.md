[\[Paso Anterior\]](05_list_item.md)

# How to Edit

- Agregaremos un boton para poder editar el ToDo una vez que ya leimos los datos.

```java
package com.nearsoft.androidschool.todoapp.activities.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_TODO_KEY = "TODO";

    private TextView titleTextView;
    private TextView notesTextView;
    private FloatingActionButton editFab;

    private ToDoContent todo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todo = getTodo();
        setContentView(R.layout.activity_detail);
        titleTextView = (TextView) findViewById(R.id.title);
        notesTextView = (TextView) findViewById(R.id.notes);
        editFab = (FloatingActionButton) findViewById(R.id.fab);
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(editFab, "should open the \"edit\" activity", Snackbar.LENGTH_SHORT).show();
            }
        });

        titleTextView.setText(todo.getTitle());
        notesTextView.setText(todo.getNotes());
    }

    private ToDoContent getTodo() {
        Bundle extras = getIntent().getExtras();
        if (extras != null && extras.containsKey(EXTRA_TODO_KEY)) {
            return (ToDoContent) extras.getSerializable(EXTRA_TODO_KEY);
        }
        throw new AssertionError(getClass().getSimpleName() + " intent extras should contain an item");
    }

}
```

Si corremos la aplicación, recibiremos un `NullPointerException` por que `editFab` aun no esta definido en el XML de la vista `activity_detail.xml`

Hagamoslo.

```xml
<?xml version="1.0" encoding="utf-8"?>
<android.support.design.widget.CoordinatorLayout xmlns:android="http://schemas.android.com/apk/res/android"
    xmlns:app="http://schemas.android.com/apk/res-auto"
    xmlns:tools="http://schemas.android.com/tools"
    android:id="@+id/activity_edit"
    android:layout_width="match_parent"
    android:layout_height="match_parent"
    tools:context="com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity">

    <RelativeLayout
        android:id="@+id/content"
        android:padding="@dimen/activity_vertical_margin"
        android:layout_width="match_parent"
        android:layout_height="match_parent">

        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:lines="1"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Title"
            tools:text="todo title will be here and if it is long AF it will be hidden" />

        <TextView
            android:id="@+id/notes"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/title"
            android:layout_marginTop="16dp"
            android:maxHeight="300dp"
            tools:text="notes of the todo, this is a sample text.\ncan contain end of lines\nsomething here" />

    </RelativeLayout>

    <android.support.design.widget.FloatingActionButton
        android:id="@+id/fab"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:layout_alignParentStart="true"
        android:layout_alignParentTop="true"
        android:layout_margin="16dp"
        android:src="@drawable/ic_edit_white"
        app:layout_anchor="@id/content"
        app:layout_anchorGravity="bottom|right|end" />

</android.support.design.widget.CoordinatorLayout>
```

Si abres bien los ojos, veras que hay un nuevo `@drawable/ic_edit_white`

Su contenido es el siguiente:

`ic_edit_white.xml`

```xml
<?xml version="1.0" encoding="utf-8"?>
<vector xmlns:android="http://schemas.android.com/apk/res/android"
    android:width="24dp"
    android:height="24dp"
    android:viewportHeight="24.0"
    android:viewportWidth="24.0">
    <path
        android:fillColor="@android:color/white"
        android:pathData="M3,17.25V21h3.75L17.81,9.94l-3.75,-3.75L3,17.25zM20.71,7.04c0.39,-0.39 0.39,-1.02 0,-1.41l-2.34,-2.34c-0.39,-0.39 -1.02,-0.39 -1.41,0l-1.83,1.83 3.75,3.75 1.83,-1.83z" />
</vector>
```

Este es el drawable del boton de "Editar"

En este punto, podemos continuar.

[\[Siguiente Paso\]](07_add_new_fields_to_detail.md)
