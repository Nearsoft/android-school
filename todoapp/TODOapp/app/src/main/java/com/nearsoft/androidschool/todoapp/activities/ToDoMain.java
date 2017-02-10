package com.nearsoft.androidschool.todoapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.ToDoContent;
import com.nearsoft.androidschool.todoapp.ToDoListAdapter;

import java.util.ArrayList;

public class ToDoMain extends AppCompatActivity {

    private ToDoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);

        RecyclerView rvTODO = (RecyclerView) findViewById(R.id.rvTODOList);
        adapter = new ToDoListAdapter(this, new ArrayList<ToDoContent>());

        rvTODO.setLayoutManager(new LinearLayoutManager(this));
        rvTODO.setAdapter(adapter);
    }
}
