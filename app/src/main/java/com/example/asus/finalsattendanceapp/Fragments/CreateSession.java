package com.example.asus.finalsattendanceapp.Fragments;


import android.app.DatePickerDialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.example.asus.finalsattendanceapp.Models.SessionModel;
import com.example.asus.finalsattendanceapp.R;
import com.firebase.client.Firebase;

import org.json.JSONObject;

import java.util.Calendar;


public class CreateSession extends DialogFragment {


    private LocationManager locationManager;
    private LocationListener locationListener;
    Firebase firebase;
    Firebase upComing;
    TimePicker timePicker;
    Button dateButton;
    Button startTime;
    Button endTime;
    Button create;
    TextView date;
    TextView start;
    TextView end;
    String SelectedDate;
    String startTimeTx;
    String endTimeTx;
    String am_pmStart;
    String am_pmEnd;
    int hrsAfter, minAfter;
    int hrs, min;
    int m, d, y;

    RequestQueue requestQueue;

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

    public CreateSession() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_create_session, container, false);

        requestQueue = Volley.newRequestQueue(getActivity());
        dateButton = (Button)view.findViewById(R.id.button4);
        startTime = (Button)view.findViewById(R.id.button5);
        create = (Button)view.findViewById(R.id.OK);
        endTime = (Button)view.findViewById(R.id.button6);
        date = (TextView)view.findViewById(R.id.date);
        start = (TextView)view.findViewById(R.id.startTime);
        end = (TextView)view.findViewById(R.id.endTime);
        firebase = new Firebase("https://finalsattendanceapp.firebaseio.com/Session");
        upComing = new Firebase("https://finalsattendanceapp.firebaseio.com/UpcomingSession");

        getDialog().setTitle("Create Session Mr AhdzLeeBee");





        dateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                m = calendar.get(Calendar.MONTH);
                d = calendar.get(Calendar.DAY_OF_MONTH);
                y = calendar.get(Calendar.YEAR);
                DatePickerDialog datePickerDialog = new DatePickerDialog(v.getContext(), new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        m = month;
                        d = dayOfMonth;
                        y = year;
                        date.setText(dates[m]+" / "+d+" / "+y);
                        SelectedDate = dates[m]+" - "+d+" - "+y;

                    }
                }, y, m, d);
                datePickerDialog.show();
                Log.e("kobe",""+m+d+y);

            }
        });

        startTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();

                TimePickerDialog tpd = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @RequiresApi(api = Build.VERSION_CODES.M)
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay < 12){
                            am_pmStart = "AM";
                        }else{
                            am_pmStart = "PM";
                        }

                        hrs = hourOfDay;
                        min = minute;
                        start.setText(hrs+":"+min+" "+am_pmStart);
                        startTimeTx = hrs+":"+min+" "+am_pmStart;
                        /*timePicker.setHour(hrs);
                        timePicker.setMinute(min);*/

                    }
                }, hrs, min, false);
                tpd.show();
            }
        });

        endTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar calendar = Calendar.getInstance();
               // hrs = calendar.get(Calendar.HOUR_OF_DAY);
               // min = calendar.get(Calendar.MINUTE);
                TimePickerDialog tpd = new TimePickerDialog(v.getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        if(hourOfDay < 12){
                            am_pmStart = "AM";
                        }else{
                            am_pmStart = "PM";
                        }
                        hrsAfter = hourOfDay;
                        minAfter = minute;
                        end.setText(hrsAfter+":"+minAfter);
                        endTimeTx = hrsAfter+":"+minAfter+" "+am_pmStart;

                    }
                }, hrs, min, false);
                tpd.show();
            }
        });

        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               /* JsonObjectRequest request = new JsonObjectRequest("https://maps.googleapis.com/maps/api/geocode/json?"+lat+","
                +lang+"&key=AIzaSyDSbSU-uPu9qis3BJRRGrdiLaDQBW7UYdg", new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {

                    }
                });*/
                Firebase activityID = firebase.push();
                SessionModel sessionModel = new SessionModel(SelectedDate,startTimeTx,endTimeTx,"Banawa",activityID.getKey());

                activityID.setValue(sessionModel);
                upComing.child(activityID.getKey()).setValue(sessionModel);
                dismiss();

            }
        });

        return view;

    }
}
