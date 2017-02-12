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

public class ToDoMain extends AppCompatActivity {

    private ToDoListAdapter adapter;
    private FloatingActionButton addToDoItemFab;
    private RecyclerView toDoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);

        addToDoItemFab = (FloatingActionButton) findViewById(R.id.addToDoItemFab);
        adapter = new ToDoListAdapter(getData());
        toDoRecyclerView = (RecyclerView) findViewById(R.id.rvTODOList);
        toDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        toDoRecyclerView.setAdapter(adapter);

        addToDoItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(addToDoItemFab, "this should take you to and activity to add a new task ", Snackbar.LENGTH_SHORT).show();
            }
        });
    }

    public List<ToDoContent> getData() {
        List<ToDoContent> data = new ArrayList<>();
        data.add(new ToDoContent("task 1", "Today", "at Nearsoft"));
        data.add(new ToDoContent("task 2", "Today", "at Nearsoft"));
        data.add(new ToDoContent("task 3", "yesterday", "at Cafenio"));
        return data;
    }
}
