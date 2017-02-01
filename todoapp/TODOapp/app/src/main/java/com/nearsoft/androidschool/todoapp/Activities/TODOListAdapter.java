package com.nearsoft.androidschool.todoapp.Activities;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Ramon on 2/1/2017.
 */

public class TODOListAdapter extends RecyclerView.Adapter<TODOListAdapter.ViewHolder> {

    //TODO add functionality
    public TODOListAdapter() {
    }

    @Override
    public TODOListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ViewHolder(item);
    }

    @Override
    public void onBindViewHolder(TODOListAdapter.ViewHolder viewHolder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(View itemView) {
            super(itemView);
        }
    }
}
