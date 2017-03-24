package com.example.asus.finalsattendanceapp;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by asus on 24/03/2017.
 */

public class SessionStudentAdapter extends RecyclerView.Adapter<SessionStudentAdapter.ViewHolder> {
    @Override
    public SessionStudentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(SessionStudentAdapter.ViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        TextView name;
        ImageView image;

        public ViewHolder(final View itemView){
            super(itemView);
            name = (TextView)itemView.findViewById(R.id.nameID);
            image = (ImageView)itemView.findViewById(R.id.imageView);
        }
    }
}
