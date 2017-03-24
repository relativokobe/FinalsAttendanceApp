package com.example.asus.finalsattendanceapp;


import android.app.FragmentManager;
import android.os.Bundle;
import android.app.Fragment;
import android.support.design.widget.TabLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.asus.finalsattendanceapp.Fragments.Absent;
import com.example.asus.finalsattendanceapp.Fragments.Late;
import com.example.asus.finalsattendanceapp.Fragments.Ontime;


/**
 * A simple {@link Fragment} subclass.
 */
public class ClassInfoAhdz extends Fragment {

    String id;
    String dateText;
    String locationText;
    String endTimeText;
    String startTimeText;
    String ampm;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TabLayout tab;


    FragmentManager fm;
    TextView date;
    TextView startTime;
    TextView endTime;
    public ClassInfoAhdz() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_class_info_ahdz, container, false);
        date = (TextView)view.findViewById(R.id.date);
        startTime = (TextView)view.findViewById(R.id.startTime);
        endTime = (TextView)view.findViewById(R.id.endTime);

        RecyclerView listView = (RecyclerView)view.findViewById(R.id.listView);


        TabLayout tab = (TabLayout)view.findViewById(R.id.tab);

        tab.addTab(tab.newTab().setText("OnTime"));
        tab.addTab(tab.newTab().setText("Late"));
        tab.addTab(tab.newTab().setText("Absent"));

        if(getArguments() != null){
            id = getArguments().getString("sessionID");
            dateText = getArguments().getString("date");
            locationText = getArguments().getString("location");
            startTimeText = getArguments().getString("timeStart");
            endTimeText = getArguments().getString("timEnd");

            date.setText(dateText);
            startTime.setText("start time: "+startTimeText);
            endTime.setText("end time:"+endTimeText);
        }

        fm = getFragmentManager();
        final Late late = new Late();
        final Ontime ontime = new Ontime();
        final Absent absent = new Absent();

        fm.beginTransaction().add(ontime,"OnTime");
        fm.beginTransaction().add(late,"Late");
        fm.beginTransaction().add(absent,"Absent");
        fm.beginTransaction().replace(R.id.fram2,ontime).commit();
        Bundle args = new Bundle();

        args.putString("sessionID",id);
        args.putString("date",dateText);
        args.putString("location",locationText);
        args.putString("timeStart",startTimeText);
        args.putString("timEnd",endTimeText);

        late.setArguments(args);
        ontime.setArguments(args);
        absent.setArguments(args);

        tab.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                if(tab.getPosition() == 0){
                    fm.beginTransaction().replace(R.id.fram2,ontime).commit();
                }else if(tab.getPosition() == 1){
                    fm.beginTransaction().replace(R.id.fram2,late).commit();
                }else if(tab.getPosition() == 2){
                    fm.beginTransaction().replace(R.id.fram2,absent).commit();
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });



        return view;
    }

}
