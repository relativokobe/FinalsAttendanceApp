package com.example.asus.finalsattendanceapp.Student;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.example.asus.finalsattendanceapp.Models.SessionModelWithType;
import com.example.asus.finalsattendanceapp.R;
import com.firebase.client.Firebase;
import com.google.firebase.auth.FirebaseAuth;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * A simple {@link Fragment} subclass.
 */
public class AttendSessionFragment extends Fragment {
    String id;
    String dateText;
    String locationText;
    String endTimeText;
    String startTimeText;
    String ampm;

    Firebase studSess;
    Firebase late;
    Firebase present;
    Firebase Absent;
    Firebase done;

    String yearOfSession;
    String dayOfSession;
    String monthOfSession;

    String dateofSession;

    Button attend;
    TextView date;
    TextView startTime;
    TextView endTime;

    String [] dates = {
            "January",
            "February",
            "March",
            "April",
            "May",
            "June",
            "July",
            "August",
            "September",
            "October",
            "November",
            "December"
    };

    public AttendSessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_attend_session, container, false);


        studSess = new Firebase("https://finalsattendanceapp.firebaseio.com/StudentSession");
        present = new Firebase("https://finalsattendanceapp.firebaseio.com/SessionAttendancePresent");
        late = new Firebase("https://finalsattendanceapp.firebaseio.com/SessionAttendanceLate");
        Absent = new Firebase("https://finalsattendanceapp.firebaseio.com/SessionAttendanceAbsent");
        done = new Firebase("https://finalsattendanceapp.firebaseio.com/DoneSession");
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

       dateofSession = dateText;
        String a = dateText;
        String b = dateText;

        monthOfSession = dateofSession.split("-")[0];
        dayOfSession = a.split("-")[1];
      // yearOfSession = b.split("-",2)[2];

        Log.e("kobee","month"+monthOfSession);
        Log.e("kobee","day"+dayOfSession);
        Log.e("kobee","year"+a);


        attend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int hr = c.get(Calendar.HOUR);
                int min = c.get(Calendar.MINUTE);
                int day = c.get(Calendar.DAY_OF_MONTH);
                int year = c.get(Calendar.YEAR);
                int month = c.get(Calendar.MONTH);

                String dayToday = dates[month] + " - "+day+" - "+year;
                Log.e("1234"," "+dayToday);
                Log.e("1234"," "+dateText);

                Log.e("ruby","day = "+day);

            if(dayToday.equals(dateText)) {
                DateFormat parser = new SimpleDateFormat("hh:mm aa");
                if (hr < 12) {
                    ampm = "AM";
                } else {
                    ampm = "PM";
                }
                String currentTime = hr + ":" + min + " " + ampm;

                try {
                    Date end = parser.parse(endTimeText);
                    Date start = parser.parse(startTimeText);
                    Date curr = parser.parse(currentTime);

                    if (curr.after(start)) {//meaning late
                        SessionModelWithType sm = new SessionModelWithType(dateText, startTimeText, endTimeText, locationText, id, "late");
                        studSess.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(sm);
                        late.child(id).child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("name").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

                    } else {
                        SessionModelWithType sm = new SessionModelWithType(dateText, startTimeText, endTimeText, locationText, id, "present");
                        studSess.child(FirebaseAuth.getInstance().getCurrentUser().getUid()).child(id).setValue(sm);
                        present.child(id).child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .child("name").setValue(FirebaseAuth.getInstance().getCurrentUser().getDisplayName());

                    }

                    SessionModel sm = new SessionModel(dateText, startTimeText, endTimeText, locationText, id);
                    done.child(id).setValue(sm);

                } catch (ParseException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "CATCH", Toast.LENGTH_SHORT).show();
                }

            }else{
                Toast.makeText(getActivity(), "Not allowed to Attend", Toast.LENGTH_SHORT).show();
            }
            }
        });

        return view;

    }



}
