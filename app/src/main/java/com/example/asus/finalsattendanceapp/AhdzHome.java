package com.example.asus.finalsattendanceapp;


import android.app.FragmentManager;
import android.support.design.widget.FloatingActionButton;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.asus.finalsattendanceapp.Fragments.CreateSession;

public class AhdzHome extends AppCompatActivity {


    FloatingActionButton fab;
    FragmentManager fm;
    TabLayout tab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahdz_home);
        getSupportActionBar().hide();
        fab = (FloatingActionButton)findViewById(R.id.Fab);
        fab.show();
        TabLayout tab = (TabLayout)findViewById(R.id.tab);



        tab.addTab(tab.newTab().setText("AhdzzzLeeBeeee"));
        tab.addTab(tab.newTab().setText("Classes"));

        fm = getFragmentManager();
        fm.beginTransaction().add(new AhdzHomeFragmentProfileAndStudents(),"AhdzleeBeeHome").commit();
        fm.beginTransaction().replace(R.id.frame,new AhdzHomeFragmentProfileAndStudents()).commit();
        fm.beginTransaction().add(new Classes(),"Classes").commit();


        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    fm.beginTransaction().replace(R.id.frame,new AhdzHomeFragmentProfileAndStudents()).commit();
                }
                else if(tab.getPosition() == 1){
                    fm.beginTransaction().replace(R.id.frame,new Classes()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              FragmentManager fm = getFragmentManager();
                CreateSession ss = new CreateSession();

                ss.show(fm,"Create Session");

            }
        });


    }
}
