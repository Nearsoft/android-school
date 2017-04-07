package com.nearsoft.androidschool.todoapp.activities.main.adapter;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageButton;
import android.widget.TextView;

import com.nearsoft.androidschool.todoapp.R;
import com.nearsoft.androidschool.todoapp.activities.detail.DetailActivity;
import com.nearsoft.androidschool.todoapp.models.ToDoContent;

import java.util.ArrayList;
import java.util.List;

public class ToDoListAdapter extends RecyclerView.Adapter<ToDoListAdapter.ViewHolder> {

    private List<ToDoContent> toDoList;

    public ToDoListAdapter() {
        toDoList = new ArrayList<>();
    }

    @Override
    public ToDoListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ToDoListAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bindData(toDoList.get(position));
    }

    @Override
    public int getItemCount() {
        return toDoList.size();
    }

    public void updateToDos(List<ToDoContent> todos) {
        toDoList.clear();
        toDoList.addAll(todos);
        notifyDataSetChanged();
    }

    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        ToDoContent item;
        CardView container;
        TextView toDoNameTextBox;
        TextView dateTextBox;
        CheckBox doneCheckbox;
        ImageButton mapButton;

        ViewHolder(View itemView) {
            super(itemView);
            container = (CardView) itemView.findViewById(R.id.cardViewContainer);
            toDoNameTextBox = (TextView) itemView.findViewById(R.id.toDoText);
            dateTextBox = (TextView) itemView.findViewById(R.id.dateText);
            doneCheckbox = (CheckBox) itemView.findViewById(R.id.isDoneCheckbox);
            mapButton = (ImageButton) itemView.findViewById(R.id.mapImageButton);
        }

        void bindData(ToDoContent toDoItem) {
            item = toDoItem;
            toDoNameTextBox.setText(toDoItem.getTitle());
            if (toDoItem.hasDate()) {
                dateTextBox.setText(toDoItem.getDate().toString());
            } else {
                dateTextBox.setText("");
            }
            doneCheckbox.setOnClickListener(this);
            mapButton.setOnClickListener(this);
            container.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.isDoneCheckbox) {
                Snackbar.make(mapButton, "this should hide this item when clicked maybe", Snackbar.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.mapImageButton) {
                Snackbar.make(view, "Maps are not going to be part of this Android School :-(", Snackbar.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.cardViewContainer) {
                Intent intent = new Intent(view.getContext(), DetailActivity.class);
                intent.putExtra(DetailActivity.EXTRA_TODO_KEY, item);
                view.getContext().startActivity(intent);
            }
        }
    }
}
