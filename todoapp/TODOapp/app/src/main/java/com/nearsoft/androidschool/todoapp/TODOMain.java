package com.nearsoft.androidschool.todoapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nearsoft.androidschool.todoapp.Activities.TODOListAdapter;

public class TODOMain extends AppCompatActivity {

    private TODOListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);

        RecyclerView rvTODO = (RecyclerView) findViewById(R.id.rvTODOList);
        adapter = new TODOListAdapter();

        rvTODO.setLayoutManager(new LinearLayoutManager(this));
        rvTODO.setAdapter(adapter);
    }
}
