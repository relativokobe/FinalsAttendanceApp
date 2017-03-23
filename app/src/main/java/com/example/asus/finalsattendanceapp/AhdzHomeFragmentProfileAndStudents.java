package com.example.asus.finalsattendanceapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.finalsattendanceapp.Models.StudentModel;
import com.example.asus.finalsattendanceapp.Student.StudentListAdapter;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AhdzHomeFragmentProfileAndStudents extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    Firebase firebase;
    RecyclerView.Adapter adapter;
    ArrayList<StudentModel> list;

    public AhdzHomeFragmentProfileAndStudents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment


        final View view = inflater.inflate(R.layout.fragment_ahdz_home_fragment_profile_and_students, container, false);

        firebase = new Firebase("https://finalsattendanceapp.firebaseio.com/Students");
        final RecyclerView listView = (RecyclerView) view.findViewById(R.id.listView);

     list = new ArrayList<>();

        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                StudentModel sm = dataSnapshot.getValue(StudentModel.class);
                list.add(sm);
                Log.e("ruby","sa Home"+list.size());
                layoutManager = new LinearLayoutManager(view.getContext());
                listView.setLayoutManager(layoutManager);
                    adapter = new StudentListAdapter(list,view.getContext());

                listView.setAdapter(adapter);
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {

            }
        });



        return view;
    }

}
