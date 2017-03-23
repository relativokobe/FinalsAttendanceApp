package com.example.asus.finalsattendanceapp.Student;

import android.app.Activity;
import android.app.FragmentManager;
import android.content.Context;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.finalsattendanceapp.ClassListAdapter;
import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.example.asus.finalsattendanceapp.R;

import java.util.ArrayList;

/**
 * Created by asus on 23/03/2017.
 */

public class StudentUpComingClassListAdapter extends RecyclerView.Adapter<StudentUpComingClassListAdapter.ViewHolder>{

    ArrayList<SessionModel> list;
    Context mContext;

    public StudentUpComingClassListAdapter(ArrayList<SessionModel> list, Context mContext) {
        this.list = list;
        this.mContext = mContext;
        Log.e("kobe"," size"+list.size());
    }

    @Override
    public StudentUpComingClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.up_coming_class_layout,parent,false);

      ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentUpComingClassListAdapter.ViewHolder holder, int position) {
        holder.endTime.setText(list.get(position).getTimeEnd());
        holder.starTime.setText(list.get(position).getTimeStart());
        holder.date.setText(list.get(position).getDate());
    }

    @Override
    public int getItemCount() {
        Log.e("ruby","getItemCount"+list.size());
        return list.size();

    }
    class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView starTime;
        TextView endTime;

        public ViewHolder(View itemView) {
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.Date);
            starTime = (TextView)itemView.findViewById(R.id.startTime);
            endTime = (TextView)itemView.findViewById(R.id.endTime);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    FragmentManager fm = ((Activity)mContext).getFragmentManager();
                    AttendSessionFragment attendSessionFragment = new AttendSessionFragment();
                    Bundle args = new Bundle();

                    args.putString("sessionID",list.get(getAdapterPosition()).getId());
                    args.putString("date",list.get(getAdapterPosition()).getDate());
                    args.putString("location",list.get(getAdapterPosition()).getLocation());
                    args.putString("timeStart",list.get(getAdapterPosition()).getTimeStart());
                    args.putString("timEnd",list.get(getAdapterPosition()).getTimeEnd());
                    Log.e("ruby",list.get(getAdapterPosition()).getTimeEnd()+" = end time");

                    attendSessionFragment.setArguments(args);
                    fm.beginTransaction().replace(R.id.frame,attendSessionFragment).addToBackStack("Upcoming Classes").commit();
                }
            });

        }
    }
}
