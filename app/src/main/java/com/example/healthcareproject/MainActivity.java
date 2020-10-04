package com.example.healthcareproject;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.doctoruser.DoctorLogin;
import com.example.healthcareproject.patientuser.Patient_Login;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    public static Drawable errorpic;
    private Button patient, doctor;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.patientdoctor);
        errorpic=getResources().getDrawable(R.drawable.erroimg);
//        Intent intent3 = new Intent(MainActivity.this, Patient_Doctor.class);
//        intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
//        startActivity(intent3);
        register();
    }

    private void register(){
        patient=(Button) findViewById(R.id.patientbtn);
        doctor=(Button) findViewById(R.id.doctorbtn1);
        patient.setOnClickListener(this);
        doctor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.patientbtn:
                Intent intent=new Intent(MainActivity.this, Patient_Login.class);
                startActivity(intent);
                break;
            case R.id.doctorbtn1:
                Intent intent1=new Intent(MainActivity.this, DoctorLogin.class);
                startActivity(intent1);
                break;
        }
    }
}