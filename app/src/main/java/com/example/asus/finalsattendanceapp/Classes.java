package com.example.asus.finalsattendanceapp;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;


/**
 * A simple {@link Fragment} subclass.
 */
public class Classes extends Fragment {
Firebase firebase;
    ArrayList<SessionModel> list;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;


    public Classes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        final View view =  inflater.inflate(R.layout.fragment_classes, container, false);
        //Firebase.setAndroidContext(getContext());
        firebase = new Firebase("https://finalsattendanceapp.firebaseio.com/Session");
        final RecyclerView listView = (RecyclerView) view.findViewById(R.id.listView);

        list = new ArrayList<>();
        firebase.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SessionModel model = dataSnapshot.getValue(SessionModel.class);
                String key = dataSnapshot.getKey();
                list.add(model);

                Collections.sort(list,new Comparator<SessionModel>() {
                    @Override
                    public int compare(SessionModel o1, SessionModel o2) {
                        SimpleDateFormat format = new SimpleDateFormat(
                                "dd-MM-yyyy");
                        int compareResult = 0;
                        try{
                            Date date1 = format.parse(o1.getDate());
                            Date date2 = format.parse(o2.getDate());
                            compareResult = date2.compareTo(date1);
                            if(compareResult==0){
                                compareResult = 1;
                            }else if(compareResult == 1){
                                compareResult = 0;
                            }
                        }catch (Exception e){
                            compareResult = o2.getDate().compareTo(o1.getDate());
                            if(compareResult==0){
                                compareResult = 1;
                            }else if(compareResult == 1){
                                compareResult = 0;
                            }
                        }
                        return compareResult;
                    }
                });

               // Log.e("ruby","syd "+list.get(0).getTimeEnd()+" size = "+list.size());
                layoutManager = new LinearLayoutManager(view.getContext());
                listView.setLayoutManager(layoutManager);
                adapter = new ClassListAdapter(list);
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
