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
import com.nearsoft.androidschool.todoapp.database.ToDoDbHelper;

public class MainActivity extends AppCompatActivity {

    private ToDoListAdapter adapter;
    private FloatingActionButton addFab;
    private RecyclerView todoRecyclerView;
    private ToDoDbHelper toDoDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);
        toDoDbHelper = new ToDoDbHelper(this);

        addFab = (FloatingActionButton) findViewById(R.id.fab);
        adapter = new ToDoListAdapter();

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

    @Override
    protected void onResume() {
        super.onResume();
        adapter.updateToDos(toDoDbHelper.getAllToDos());
    }

    @Override
    protected void onDestroy() {
        toDoDbHelper.onDestroy();
        super.onDestroy();
    }

}
