package com.nearsoft.androidschool.todoapp.activities.main_activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.main_activity.adapter.ToDoListAdapter;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.ArrayList;
import java.util.List;

public class ToDoMain extends AppCompatActivity {

    private ToDoListAdapter mAdapter;
    private FloatingActionButton mAddToDoItemFab;
    private RecyclerView mToDoRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todomain);

        mAddToDoItemFab = (FloatingActionButton) findViewById(R.id.addToDoItemFab);
        mAdapter = new ToDoListAdapter(getFakeData());
        mToDoRecyclerView = (RecyclerView) findViewById(R.id.rvTODOList);
        mToDoRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mToDoRecyclerView.setAdapter(mAdapter);

        mAddToDoItemFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fabAction();
            }
        });
    }

    private void fabAction() {
//        TODO:add the intent to call the Add item Activity
        Snackbar.make(mAddToDoItemFab, "Function not implemented yet", Snackbar.LENGTH_SHORT).show();
    }

    //    TODO:delet this when retrieving real data
    public List<ToDoContent> getFakeData() {
        List<ToDoContent> fakeList = new ArrayList<>();
        fakeList.add(new ToDoContent("task 1", "Today", "at Nearsoft"));
        fakeList.add(new ToDoContent("task 2", "Today", "at Nearsoft"));
        fakeList.add(new ToDoContent("task 3", "yesterday", "at Cafenio"));

        return fakeList;
    }
}
