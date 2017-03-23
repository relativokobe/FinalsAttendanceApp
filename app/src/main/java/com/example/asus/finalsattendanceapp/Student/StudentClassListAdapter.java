package com.example.asus.finalsattendanceapp.Student;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.finalsattendanceapp.ClassListAdapter;
import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.example.asus.finalsattendanceapp.Models.Type;
import com.example.asus.finalsattendanceapp.R;

import java.util.ArrayList;

/**
 * Created by asus on 23/03/2017.
 */

public class StudentClassListAdapter extends RecyclerView.Adapter<StudentClassListAdapter.ViewHolder> {
    ArrayList<SessionModel>sessionModels;
    ArrayList<Type>types;
    Context mcontext;

    public StudentClassListAdapter(ArrayList<SessionModel> sessionModels, ArrayList<Type> types, Context mcontext) {
        this.sessionModels = sessionModels;
        this.types = types;
        this.mcontext = mcontext;
    }

    @Override
    public StudentClassListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.class_list_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentClassListAdapter.ViewHolder holder, int position) {
    if(types.get(position).getType().equals("late")){
        holder.cardId.setCardBackgroundColor(Color.GRAY);
    }else if(types.get(position).getType().equals("absent")){
        holder.cardId.setCardBackgroundColor(Color.RED);
    }else{
        holder.cardId.setCardBackgroundColor(Color.GREEN);
    }

        holder.date.setText(sessionModels.get(position).getDate());
        holder.starTime.setText(sessionModels.get(position).getTimeStart());
        holder.endTime.setText(sessionModels.get(position).getTimeEnd());

    }

    class ViewHolder extends RecyclerView.ViewHolder{
        TextView date;
        TextView starTime;
        TextView endTime;
        CardView cardId;

        public ViewHolder(final View itemView){
            super(itemView);
            date = (TextView)itemView.findViewById(R.id.Date);
            starTime = (TextView)itemView.findViewById(R.id.startTime);
            endTime = (TextView)itemView.findViewById(R.id.endTime);
            cardId = (CardView)itemView.findViewById(R.id.card);

        }
    }

    @Override
    public int getItemCount() {
        return 0;
    }
}
