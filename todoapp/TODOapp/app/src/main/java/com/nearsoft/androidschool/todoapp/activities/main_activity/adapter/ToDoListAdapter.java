package com.nearsoft.androidschool.todoapp.activities.main_activity.adapter;

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


    static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        CardView mContainer;
        TextView mToDoNameTextBox;
        TextView mDateTextBox;
        CheckBox mDoneCheckbox;
        ImageButton mMapButton;

        ViewHolder(View itemView) {
            super(itemView);
            mContainer = (CardView) itemView.findViewById(R.id.cardViewContainer);
            mToDoNameTextBox = (TextView) itemView.findViewById(R.id.toDoText);
            mDateTextBox = (TextView) itemView.findViewById(R.id.dateText);
            mDoneCheckbox = (CheckBox) itemView.findViewById(R.id.isDoneCheckbox);
            mMapButton = (ImageButton) itemView.findViewById(R.id.mapImageButton);
        }

        void bindData(ToDoContent toDoItem) {
            mToDoNameTextBox.setText(toDoItem.getTitle());
            mDateTextBox.setText(toDoItem.getDate());
//  TODO: this might be refactor
            mDoneCheckbox.setOnClickListener(this);
            mMapButton.setOnClickListener(this);
            mContainer.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (view.getId() == R.id.isDoneCheckbox) {
//  TODO: should hide the item
                Snackbar.make(mMapButton, "this should hide this item when clicked maybe", Snackbar.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.mapImageButton) {
//  TODO: open an intent to the map to sho location maybe
                Snackbar.make(mMapButton, "this should show the map in google maps", Snackbar.LENGTH_SHORT).show();
            }
            if (view.getId() == R.id.cardViewContainer) {
//  TODO: open an intent to the map to sho location maybe
                Snackbar.make(mMapButton, "This should take you to the detail", Snackbar.LENGTH_SHORT).show();
            }
        }
    }
}
