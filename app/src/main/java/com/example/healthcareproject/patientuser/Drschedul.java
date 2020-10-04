package com.example.healthcareproject.patientuser;
import android.app.AlertDialog;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;
import com.example.healthcareproject.doctoruser.Home_Activitydtr;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Drschedul extends AppCompatActivity implements View.OnClickListener {

    private Button savebtn;
    private EditText nopatientobj;
    SQLiteDatabase db;
    String avlday;
    private Spinner spinner1,spinner2,spinner3;
    private int s,e,tatalinday,counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drschedul);
        register();
        startspinner();
        endspinner();
        endspinner();
        Dravlday();
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS DrscheduleInfo(druserid INTEGER NOT NULL,availableadds VARCHAR NOT NULL,availday VARCHAR NOT NULL,starting INTEGER NOT NULL,ending INTEGER NOT NULL);");
        db.execSQL("CREATE TABLE IF NOT EXISTS DrtokenInfo(druserid INTEGER NOT NULL,tokennumber VARCHAR NOT NULL,availday VARCHAR NOT NULL);");
    }

    private void register() {
        savebtn=(Button)findViewById(R.id.save);
        nopatientobj=(EditText)findViewById(R.id.noofpatienttxt);
        savebtn.setOnClickListener(this);
    }
    public void Dravlday()
    {
        spinner1 = (Spinner) findViewById(R.id.drdate);
        String[] days = new String[]{"Select a day","Mon", "Tue", "Wed", "Thu", "Fri", "Sat","Sun"};
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
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Alarm","select starting time"); }
        });
    }
    public void startspinner()
    {
        spinner2 = (Spinner) findViewById(R.id.starttime);
        String[] stimes = new String[]{
                "starting time","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14", "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24"};

        List<String> plantsList = new ArrayList<>(Arrays.asList(stimes));
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
        spinner2.setAdapter(spinnerArrayAdapter);
        spinner2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    s=Integer.valueOf(spinner2.getSelectedItem().toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Alarm","select starting time"); }
        });
    }
    public void endspinner()
    {
        spinner3 = (Spinner) findViewById(R.id.endtime);

        String[] etimes = new String[]{
                "Ending time","1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12", "13", "14",
                "15", "16", "17", "18", "19", "20", "21",
                "22", "23", "24"
        };
        final List<String> plantsList = new ArrayList<>(Arrays.asList(etimes));
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
        spinner3.setAdapter(spinnerArrayAdapter);
        spinner3.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    e=Integer.valueOf(spinner3.getSelectedItem().toString());
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Alarm","select ending time");
            }
        });
    }
    @Override
    public void onClick(View v) {
        int startno,endno;
        if(nopatientobj.getText().toString().equals("")||nopatientobj.getText().length()==0){
            showMessage("Invalid","Please fill-up all the fields");
        }
        else if(spinner1.getSelectedItem().toString().equals("Select a day"))
        {
            showMessage("Invalid","Please select a day.");
        }
        else if(spinner2.getSelectedItem().toString().equals("starting time"))
        {
            showMessage("Invalid","Please select starting time.");
        }
        else if(spinner3.getSelectedItem().toString().equals("Ending time"))
        {
            showMessage("Invalid","Please select ending time.");
        }
        else if(Integer.parseInt(spinner2.getSelectedItem().toString())>=Integer.parseInt(spinner3.getSelectedItem().toString())){
            showMessage("Failed","Please set correct timing");
        }else{
            int num_patientinone=Integer.parseInt(nopatientobj.getText().toString());
            tatalinday=(e-s)*num_patientinone;
            counter=(e-s)*num_patientinone;
            db.execSQL("INSERT INTO DrscheduleInfo VALUES('"+ Home_Activitydtr.druserid+"','"+Home_Activitydtr.drclinicadds+"','"+avlday+"',"+s+","+e+");");
            int numbers=1;
            while(counter>0)
            {
                db.execSQL("INSERT INTO DrtokenInfo VALUES('"+Home_Activitydtr.druserid+"','"+ numbers +"','"+avlday+"');");
                numbers++;
                counter--;
            }
            showMessage("Done!","You have successfully set your schedule for "+avlday);
            nopatientobj.setText("");
        }
    }

    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}