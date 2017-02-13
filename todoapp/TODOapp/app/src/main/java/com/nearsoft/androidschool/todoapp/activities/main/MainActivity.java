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
