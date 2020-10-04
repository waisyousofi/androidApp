package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Myschedule extends AppCompatActivity implements View.OnClickListener{

    String avlday;
    Spinner spinner1;
    SQLiteDatabase db;
    Button deletebtnobj;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myschedule);
        deletebtnobj=(Button)findViewById(R.id.deletebtn);
        deletebtnobj.setOnClickListener(this);
        deletingday();

    }
    public void deletingday()
    {
        spinner1 = (Spinner) findViewById(R.id.daysdr);
        String[] days = new String[]{"Select a day to delete its tokens","Sun","Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        List<String> plantsList = new ArrayList<>(Arrays.asList(days));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this, R.layout.spinnerinlayout, plantsList) {
            @Override
            public boolean isEnabled(int position) {
                if (position == 0) {
                    return false;
                } else {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (position == 0) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnerinlayout);
        spinner1.setAdapter(spinnerArrayAdapter);
        spinner1.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    avlday=selectedItemText;
                    Toast.makeText(getApplicationContext(), "Selected : " + selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        if(spinner1.getSelectedItem().equals("Select a day to delete its tokens"))
        {
            Toast.makeText(getApplicationContext(),"Please select a day.",Toast.LENGTH_LONG).show();
        }
        else {
            db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE,null);
            db.execSQL("DELETE  from DrtokenInfo where druserid="+ Home_Activitydtr.druserid+" and availday='"+avlday+"'");
            db.execSQL("DELETE  from DrscheduleInfo where druserid="+Home_Activitydtr.druserid+" and availday='"+avlday+"'");
            db.execSQL("DELETE  from PatientsQueueInfo where druserid="+Home_Activitydtr.druserid+" and visitingday='"+avlday+"'");

            Toast.makeText(getApplicationContext(),"deleted.",Toast.LENGTH_LONG).show();
        }
    }
}