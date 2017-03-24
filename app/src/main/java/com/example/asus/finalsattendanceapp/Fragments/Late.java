package com.example.asus.finalsattendanceapp.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.finalsattendanceapp.Models.UserPhoto;
import com.example.asus.finalsattendanceapp.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;

import java.util.ArrayList;



public class Late extends Fragment {
    Firebase late;

    ArrayList<UserPhoto> userList;
    Query query;
    String id, dateText, locationText, startTimeText, endTimeText;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_late, container, false);
        late = new Firebase("https://finalsattendanceapp.firebaseio.com/SessionAttendanceLate");
        final RecyclerView listView = (RecyclerView)view.findViewById(R.id.listView);

        userList = new ArrayList<>();
        if(getArguments() != null) {

            id = getArguments().getString("sessionID");
            dateText = getArguments().getString("date");
            locationText = getArguments().getString("location");
            startTimeText = getArguments().getString("timeStart");
            endTimeText = getArguments().getString("timEnd");

        }
        query = late.child(id);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                UserPhoto userPhoto = dataSnapshot.getValue(UserPhoto.class);
                userList.add(userPhoto);

                layoutManager = new LinearLayoutManager(view.getContext());
                listView.setLayoutManager(layoutManager);
                adapter = new LateAdapter(userList,view.getContext());
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
