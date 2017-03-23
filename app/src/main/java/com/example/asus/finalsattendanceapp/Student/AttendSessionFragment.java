package com.example.asus.finalsattendanceapp.Student;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.example.asus.finalsattendanceapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendSessionFragment extends Fragment {
    String id;
    String dateText;
    String locationText;
    String endTimeText;
    String startTimeText;

    Button attend;
    TextView date;
    TextView startTime;
    TextView endTime;

    public AttendSessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attend_session, container, false);

        attend = (Button)view.findViewById(R.id.button);
        date = (TextView)view.findViewById(R.id.date);
        startTime = (TextView)view.findViewById(R.id.startTime);
        endTime = (TextView)view.findViewById(R.id.endTime);

        if (getArguments() != null) {

            dateText = getArguments().getString("date");
            locationText = getArguments().getString("location");
            startTimeText = getArguments().getString("timeStart");
            endTimeText = getArguments().getString("timEnd");

            id = getArguments().getString("sessionID");
            TextView text = (TextView) view.findViewById(R.id.text);
            date = (TextView)view.findViewById(R.id.date);
            startTime = (TextView)view.findViewById(R.id.startTime);
            endTime = (TextView)view.findViewById(R.id.endTime);

            date.setText(dateText);
            startTime.setText("start time: "+startTimeText);
            endTime.setText("end time:"+endTimeText);

            text.setText(id+" = ID pisteng yawa");
        }

        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });


        return view;

    }
}
