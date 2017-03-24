package com.example.asus.finalsattendanceapp.Fragments;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.finalsattendanceapp.Models.UserPhoto;
import com.example.asus.finalsattendanceapp.R;

import java.util.ArrayList;

/**
 * Created by asus on 25/03/2017.
 */

public class AbsentAdapter extends RecyclerView.Adapter<AbsentAdapter.ViewHolder> {
    ArrayList<UserPhoto> userList;
    Context mContext;

    public AbsentAdapter(ArrayList<UserPhoto> userList, Context mContext) {
        this.userList = userList;
        this.mContext = mContext;
    }

    @Override
    public AbsentAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_layout,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(AbsentAdapter.ViewHolder holder, int position) {
        Glide.with(mContext).load(userList.get(position).getUri()).into(holder.image);
        holder.name.setText(userList.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return userList.size();
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
