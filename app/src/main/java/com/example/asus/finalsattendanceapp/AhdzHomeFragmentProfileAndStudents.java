package com.example.asus.finalsattendanceapp;


import android.net.Uri;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.asus.finalsattendanceapp.Models.StudentModel;
import com.example.asus.finalsattendanceapp.Student.StudentListAdapter;
import com.firebase.client.ChildEventListener;
import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class AhdzHomeFragmentProfileAndStudents extends Fragment {
    RecyclerView.LayoutManager layoutManager;
    Firebase firebase;
    RecyclerView.Adapter adapter;
    ImageView im;
    ArrayList<StudentModel> list;
    StorageReference storageReference;
    DatabaseReference databaseReference;

    public AhdzHomeFragmentProfileAndStudents() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        storageReference = FirebaseStorage.getInstance().getReference();
        final View view = inflater.inflate(R.layout.fragment_ahdz_home_fragment_profile_and_students, container, false);
        im = (ImageView)view.findViewById(R.id.imageView2);

        storageReference.child("Ahdz/pp.jpg").getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
               if(uri != null) {
                   Glide.with(getActivity()).load(uri).into(im);
               }
            }
        });

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
