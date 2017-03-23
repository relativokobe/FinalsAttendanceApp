package com.example.asus.finalsattendanceapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;

public class AhdzLogIn extends AppCompatActivity {

    Button login;
    EditText text;
    EditText pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ahdz_log_in);
        requestWindowFeature(Window.FEATURE_NO_TITLE);

        login = (Button)findViewById(R.id.login);
        text = (EditText)findViewById(R.id.userName);
        pass = (EditText)findViewById(R.id.password);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            startActivity(new Intent(AhdzLogIn.this,AhdzHome.class));
            }
        });


    }
}
