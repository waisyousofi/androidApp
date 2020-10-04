package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Myaccount extends AppCompatActivity implements View.OnClickListener{

    private Button logout;
    private TextView name,emailadds,mobno,address,gender;
    private SQLiteDatabase db;
    private ImageView ptimg;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myaccount);
        register();
        name= (TextView)findViewById(R.id.user11);
        emailadds= (TextView)findViewById(R.id.useremailtext);
        mobno= (TextView)findViewById(R.id.userphonetext);
        address=(TextView)findViewById(R.id.useraddress);
        gender=(TextView)findViewById(R.id.urgender);
        ptimg=findViewById(R.id.usericon);
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        Cursor con=db.rawQuery("select * from MyPatiensInfo where rowid="+Home_Activity.ptid+"",null);
        while (con.moveToNext()){
            name.setText(con.getString(0));
            emailadds.setText("EmailId: "+con.getString(1));
            mobno.setText("Mobile No: "+con.getString(2));
            address.setText("Address: "+con.getString(3));
            gender.setText("Gender: "+con.getString(5));
        }
    }

    private void register()
    {
        logout=(Button)findViewById(R.id.singoutbtnaccount);
        logout.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        Intent intent=new Intent(Myaccount.this, Patient_Login.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}