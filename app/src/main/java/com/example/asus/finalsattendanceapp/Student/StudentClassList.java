package com.example.asus.finalsattendanceapp.Student;


import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.asus.finalsattendanceapp.ClassListAdapter;
import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.example.asus.finalsattendanceapp.Models.SessionModelWithType;
import com.example.asus.finalsattendanceapp.Models.Type;
import com.example.asus.finalsattendanceapp.R;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.Query;
import com.firebase.client.ValueEventListener;
import com.google.firebase.auth.FirebaseAuth;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;



/**
 * A simple {@link Fragment} subclass.
 */
public class StudentClassList extends Fragment {

    Firebase sessions;
    Firebase studentSessions;
    Firebase doneSessions;
    ArrayList<SessionModel>sessionModels;
    ArrayList<Type>types;
    ArrayList<SessionModelWithType>sessionModelWithTypes;

    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    Query studentSessionQ;
    public StudentClassList() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view =  inflater.inflate(R.layout.fragment_student_class_list, container, false);

        types = new ArrayList<>();
        sessionModels = new ArrayList<>();
        sessionModelWithTypes = new ArrayList<>();
        doneSessions = new Firebase("https://finalsattendanceapp.firebaseio.com/DoneSession");
        sessions = new Firebase("https://finalsattendanceapp.firebaseio.com/Session");
        studentSessions = new Firebase("https://finalsattendanceapp.firebaseio.com/StudentSession");

        final String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        studentSessionQ = studentSessions.child(userId);
        studentSessionQ.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SessionModelWithType type = dataSnapshot.getValue(SessionModelWithType.class);
                sessionModelWithTypes.add(type);
                //ayoha diri kay ang tanan sessions ang i butang fuck
                Collections.sort(sessionModelWithTypes, new Comparator<SessionModelWithType>() {
                    @Override
                    public int compare(SessionModelWithType o1, SessionModelWithType o2) {
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

                Log.e("ruby","type niyaaa "+type.getType());
                Log.e("ruby","sessionModelTypes = "+sessionModelWithTypes.size()+"session model size ="+sessionModels.size());
                if(sessionModelWithTypes.size()> 0){
                    start(view);
                }
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
    public void start(View rootview){
        RecyclerView listView = (RecyclerView)rootview.findViewById(R.id.listView);
        layoutManager = new LinearLayoutManager(rootview.getContext());
        listView.setLayoutManager(layoutManager);
        adapter = new StudentClassListAdapter(sessionModels,sessionModelWithTypes,rootview.getContext());
        listView.setAdapter(adapter);
    }

}
