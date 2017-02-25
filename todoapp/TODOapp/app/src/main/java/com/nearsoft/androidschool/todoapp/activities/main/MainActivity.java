package com.nearsoft.androidschool.todoapp.activities.main;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity;
import com.nearsoft.androidschool.todoapp.activities.main.adapter.ToDoListAdapter;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.ArrayList;
import java.util.Date;
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
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                view.getContext().startActivity(intent);
            }
        });
    }

    public List<ToDoContent> getData() {
        List<ToDoContent> data = new ArrayList<>();
        ToDoContent first = new ToDoContent("task 1", new Date(), true, "at Nearsoft");
        first.setNotes("sample text, text sample, hehe hehe\nmore text, here is another text and more samples\nsampletext, stub, lalala i hate the word \"fake\"");
        data.add(first);
        data.add(new ToDoContent("task 2", null, false, "at Nearsoft"));
        data.add(new ToDoContent("task 3", null, false, "at Cafenio"));
        return data;
    }
}
