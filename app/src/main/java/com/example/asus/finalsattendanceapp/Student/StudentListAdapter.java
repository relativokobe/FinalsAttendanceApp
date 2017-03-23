package com.example.asus.finalsattendanceapp.Student;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.asus.finalsattendanceapp.Models.StudentModel;
import com.example.asus.finalsattendanceapp.R;

import java.util.ArrayList;

/**
 * Created by asus on 21/03/2017.
 */

public class StudentListAdapter extends RecyclerView.Adapter<StudentListAdapter.ViewHolder> {

    ArrayList<StudentModel> studentModels;
    Context mcontext;

    public StudentListAdapter(ArrayList<StudentModel> studentModels, Context mcontext) {
        this.studentModels = studentModels;
        this.mcontext = mcontext;
        Log.e("Ruby","adapter size = "+studentModels.size());
    }

    @Override
    public StudentListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
       View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.student_list_layout,parent,false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(StudentListAdapter.ViewHolder holder, int position) {
        Glide.with(mcontext).load(studentModels.get(position).getUri()).into(holder.image);
        holder.name.setText(studentModels.get(position).getDisplayName());
    }

    @Override
    public int getItemCount() {
        Log.e("ruby","size sa getItemcount"+studentModels.size());
        return studentModels.size();
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
