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

import java.util.ArrayList;

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

        doneSessions.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                SessionModel sm = dataSnapshot.getValue(SessionModel.class);
                sessionModels.add(sm);
                studentSessionQ = studentSessions.child(sm.getId()).child(userId).orderByChild("type");
                studentSessionQ.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                    Type type = dataSnapshot.getValue(Type.class);
                        types.add(type);
                        Log.e("ruby","type niyaaa "+type);
                        if(sessionModels.size() == types.size()){
                            start(view);
                        }
                    }

                    @Override
                    public void onCancelled(FirebaseError firebaseError) {

                    }
                });
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
        adapter = new StudentClassListAdapter(sessionModels,types,rootview.getContext());
        listView.setAdapter(adapter);
    }

}
