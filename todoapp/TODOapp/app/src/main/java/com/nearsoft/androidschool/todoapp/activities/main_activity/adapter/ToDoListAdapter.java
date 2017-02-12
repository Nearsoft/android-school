package com.nearsoft.androidschool.todoapp.activities.main_activity.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.List;

/**
 * Created by Ramon on 2/1/2017.
 */

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private List<ToDoContent> mToDoList;

    public ToDoListAdapter(List<ToDoContent> toDoItemList) {
        mToDoList = toDoItemList;
    }

    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bindData(mToDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return mToDoList.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder {

        TextView mToDoNameTextBox;
        TextView mDateTextBox;

        ViewHolder(View itemView) {
            super(itemView);
            mToDoNameTextBox = (TextView) itemView.findViewById(R.id.toDoText);
            mDateTextBox = (TextView) itemView.findViewById(R.id.dateText);
        }

        void bindData(ToDoContent toDoItem) {
            mToDoNameTextBox.setText(toDoItem.getTitle());
            mDateTextBox.setText(toDoItem.getDate());
        }
    }
}
