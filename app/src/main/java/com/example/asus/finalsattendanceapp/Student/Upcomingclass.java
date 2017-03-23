package com.example.asus.finalsattendanceapp.Student;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.example.asus.finalsattendanceapp.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.util.ArrayList;

/**
 * A simple {@link Fragment} subclass.
 */
public class Upcomingclass extends Fragment {

    Firebase upComingClasses;
    ArrayList<SessionModel>upComingSessions;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    public Upcomingclass() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        upComingClasses = new Firebase("https://finalsattendanceapp.firebaseio.com/UpcomingSession");
        final View view =  inflater.inflate(R.layout.fragment_upcomingclass2, container, false);

        upComingSessions = new ArrayList<>();
        final RecyclerView listView = (RecyclerView)view.findViewById(R.id.listView);
        upComingClasses.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {

                SessionModel sm = dataSnapshot.getValue(SessionModel.class);
                upComingSessions.add(sm);
                Log.e("kobe","size = "+upComingSessions.size());


                layoutManager = new LinearLayoutManager(view.getContext());
                listView.setLayoutManager(layoutManager);
                adapter = new StudentUpComingClassListAdapter(upComingSessions,view.getContext());
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
