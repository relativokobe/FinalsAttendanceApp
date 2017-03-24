package com.example.asus.finalsattendanceapp;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.asus.finalsattendanceapp.Models.SessionModel;

import java.util.ArrayList;

/**
 * Created by asus on 22/03/2017.
 */

public class ClassListAdapter extends RecyclerView.Adapter<ClassListAdapter.ViewHolder> {

    Context context;
    ArrayList<SessionModel> list;

    public ClassListAdapter(ArrayList<SessionModel>sessionModels, Context context){

        this.list = sessionModels;
        this.context = context;
        Log.e("ruby",list.size()+" size ");
    }
    @Override
    public ClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ClassListAdapter.ViewHolder holder, int position) {
        holder.endTime.setText(list.get(position).getTimeEnd());
        holder.starTime.setText(list.get(position).getTimeStart());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView starTime;
        TextView endTime;

        public ViewHolder(final View itemView){
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.Date);
            starTime = (TextView)itemView.findViewById(R.id.startTime);
            endTime = (TextView)itemView.findViewById(R.id.endTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = ((Activity)context).getFragmentManager();

                    ClassInfoAhdz classInfoAhdz = new ClassInfoAhdz();
                    Bundle args = new Bundle();

                    args.putString("sessionID",list.get(getAdapterPosition()).getId());
                    args.putString("date",list.get(getAdapterPosition()).getDate());
                    args.putString("location",list.get(getAdapterPosition()).getLocation());
                    args.putString("timeStart",list.get(getAdapterPosition()).getTimeStart());
                    args.putString("timEnd",list.get(getAdapterPosition()).getTimeEnd());
                    Log.e("ruby",list.get(getAdapterPosition()).getTimeEnd()+" = end time");

                    classInfoAhdz.setArguments(args);
                    fm.beginTransaction().replace(R.id.frame,classInfoAhdz).commit();

                }
            });
        }


    }
}
