[\[Paso Anterior\]](06_edit_todo_transition.md)

# Date Field on Detail Activity

- Queremos agregar un nuevo campo al detalle que sea la fecha, actualmente no lo mostramos, asi que hay que agregar esos campos:

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
    private TextView dateTextView;
    private FloatingActionButton editFab;

    private ToDoContent todo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        todo = getTodo();
        setContentView(R.layout.activity_detail);
        titleTextView = (TextView) findViewById(R.id.title);
        notesTextView = (TextView) findViewById(R.id.notes);
        dateTextView = (TextView) findViewById(R.id.date_text);
        editFab = (FloatingActionButton) findViewById(R.id.fab);
        editFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Snackbar.make(editFab, "should open the \"edit\" activity", Snackbar.LENGTH_SHORT).show();
            }
        });

        titleTextView.setText(todo.getTitle());
        notesTextView.setText(todo.getNotes());
        dateTextView.setText(todo.getDate());
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

Como podras ver, agregamos un nuevo campo `date_text` y no ha sido agregado a la vista, hagamoslo en `activity_detail.xml`

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
        android:layout_width="match_parent"
        android:layout_height="match_parent"
        android:padding="@dimen/activity_vertical_margin">

        <TextView
            android:id="@+id/title"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:ellipsize="end"
            android:lines="1"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Title"
            tools:text="todo title will be here and if it is long AF it will be hidden" />

        <TextView
            android:id="@+id/date_text"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/title"
            android:layout_marginTop="10dp"
            android:gravity="end"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Small"
            tools:text="01/01/1990" />

        <TextView
            android:id="@+id/notes"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_alignParentStart="true"
            android:layout_below="@+id/date_text"
            android:layout_marginTop="16dp"
            android:maxHeight="300dp"
            android:textAppearance="@style/Base.TextAppearance.AppCompat.Body1"
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

Por ultimo, hagamos un cambio en `MainActivity.java` para demostrar que podemos ver los campos de `notes` y `date`.

```java
package com.nearsoft.androidschool.todoapp.activities.main;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.main.adapter.ToDoListAdapter;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ToDoListAdapter adapter;
    private FloatingActionButton addFab;
    private RecyclerView todoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);

        addFab = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new ToDoListAdapter(getData());

        todoRecyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        todoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoRecyclerView.setAdapter(adapter);

        addFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(todoRecyclerView, "this should take you to and activity to add a new task ", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public List<ToDoContent> getData() {
        List<ToDoContent> data = new ArrayList<>();
        ToDoContent first = new ToDoContent("task 1", "Today", "at Nearsoft");
        first.setNotes("sample text, text sample, hehe hehe\nmore text, here is another text and more samples\nsampletext, stub, lalala i hate the word \"fake\"");
        data.add(first);
        data.add(new ToDoContent("task 2", "Today", "at Nearsoft"));
        data.add(new ToDoContent("task 3", "yesterday", "at Cafenio"));
        return data;
    }
}
```

El cambio grande se encuentra en:

```java
public List<ToDoContent> getData() {
    // aqui, estamos ahora agregando las notas al Todo 1 y un "location"
    List<ToDoContent> data = new ArrayList<>();
    ToDoContent first = new ToDoContent("task 1", "Today", "at Nearsoft");
    first.setNotes("sample text, text sample, hehe hehe\nmore text, here is another text and more samples\nsampletext, stub, lalala i hate the word \"fake\"");
    data.add(first);
    data.add(new ToDoContent("task 2", "Today", "at Nearsoft"));
    data.add(new ToDoContent("task 3", "yesterday", "at Cafenio"));
    return data;
}
```

En este punto, podemos continuar.

[\[Siguiente Paso\]](08_main_activity_view.md)
