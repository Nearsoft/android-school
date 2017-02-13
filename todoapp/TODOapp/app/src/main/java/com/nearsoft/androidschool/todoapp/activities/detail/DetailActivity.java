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
