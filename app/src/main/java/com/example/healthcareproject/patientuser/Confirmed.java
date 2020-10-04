package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Confirmed extends AppCompatActivity {

    private TextView drname, location, token, selecteday;
    private SQLiteDatabase db;
    private String tokenno,visitingday,visitingadds,drnamestr,dradds2,drcontactno;
    int tokenrowid,druserid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.confirmed);
        register();
        patientQ();
    }

    private void patientQ() {
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS PatientsQueueInfo(ptuserid INTEGER NOT NULL,tokennumber VARCHAR NOT NULL,tokenrowid INTEGER NOT NULL,druserid INTEGER NOT NULL,visitingday VARCHAR NOT NULL,visitingadds VARCHAR NOT NULL,drname VARCHAR NOT NULL);");
        db.execSQL("INSERT INTO PatientsQueueInfo VALUES("+Home_Activity.ptid+",'"+tokenno+"',"+tokenrowid+","+druserid+",'"+visitingday+"','"+visitingadds+"','"+drnamestr+"');");
        showMessage("Confirmed!", "Get well soon!");
    }

    public void register() {
        tokenrowid=getIntent().getIntExtra("tokenrowid",0);
        tokenno=getIntent().getStringExtra("tokenno");
        druserid=getIntent().getIntExtra("druserid",0);
        visitingadds=getIntent().getStringExtra("visitingadds");
        visitingday=getIntent().getStringExtra("visitingday");
        drnamestr=getIntent().getStringExtra("drname");
        dradds2=getIntent().getStringExtra("dradds2");
        drcontactno=getIntent().getStringExtra("drcontactno");

        drname = (TextView) findViewById(R.id.drname);
        location = (TextView) findViewById(R.id.drlocation);
        token = (TextView) findViewById(R.id.tokentxt);
        selecteday = (TextView) findViewById(R.id.dayname);
        findViewById(R.id.backtohomebtn).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent homeIntent = new Intent(Confirmed.this,Home_Activity.class);
                homeIntent.putExtra("ptid",String.valueOf(Home_Activity.ptid));
                homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(homeIntent);
            }
        });

        drname.setText("Name :Dr."+drnamestr);
        location.setText("Location :"+dradds2+","+visitingadds);
        token.setText("Your token number :" +tokenno);
        selecteday.setText("Visiting day:" +visitingday);
    }

    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}