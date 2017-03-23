package com.example.asus.finalsattendanceapp.Student;

import android.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.support.design.widget.TabLayout;

import com.example.asus.finalsattendanceapp.R;
import com.example.asus.finalsattendanceapp.Student.StudentClassList;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Collections;

public class StudentHome extends AppCompatActivity {
FragmentManager fm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_student_home);
        TabLayout tab = (TabLayout)findViewById(R.id.tab);


        fm = getFragmentManager();


        tab.addTab(tab.newTab().setText("Class"));
        tab.addTab(tab.newTab().setText("Upcoming Classes"));
        tab.addTab(tab.newTab().setText("Me"));


        fm.beginTransaction().add(new StudentClassList(),"Class").commit();
        fm.beginTransaction().replace(R.id.frame, new StudentClassList()).commit();
        fm.beginTransaction().add(new Upcomingclass(),"Upcoming Classes").commit();

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    fm.beginTransaction().replace(R.id.frame, new StudentClassList()).commit();
                }else if(tab.getPosition() == 1){
                    fm.beginTransaction().replace(R.id.frame, new Upcomingclass()).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });




    }


}
