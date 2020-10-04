package com.example.healthcareproject.patientuser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;


public class Doctor_Details extends AppCompatActivity implements View.OnClickListener{

    private TextView name,specaility,email,adds1,mobno,gender,startingt,endingt,checktxt;
    private Button bookingbtn;
    private SQLiteDatabase db;
    public int flag2=0,tokenrowid;
    private String selectedday,tokennostr,visitingadds,drname,dradds2,drcontactno;
    private Spinner qspinner;
    private int druserid;
    private String druseridstr;
    private ImageView drprofile;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctro_details);
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        druseridstr =getIntent().getStringExtra("druserid");
        druserid=Integer.parseInt(druseridstr);
        register();
        DbDetails();
        DaysSpinner();
    }

    public void DbDetails()
    {
        Cursor con = db.rawQuery("select * from MyDoctorInfos where rowid="+druserid+"",null);
        Cursor conadds = db.rawQuery("select availableadds from DrscheduleInfo where druserid="+druserid+"",null);
        while (con.moveToNext())
        {
            while (conadds.moveToNext()){
                if(conadds.getString(0)!=null){
                    dradds2=conadds.getString(0);
                }else{
                    dradds2="";
                }
            }
            name.setText("Dr."+con.getString(0));
            email.setText("Email Id: "+con.getString(1));
            specaility.setText("Speciality: "+con.getString(2));
            mobno.setText("Contact us for any query:"+con.getString(4));
            adds1.setText("Address: "+dradds2+","+con.getString(5));
            gender.setText("Gender: "+con.getString(7));
            visitingadds=con.getString(5);
            drname=con.getString(0);
            drcontactno=con.getString(4);
            if(con.getString(7).equals("female")){
                drprofile.setImageDrawable(getResources().getDrawable(R.drawable.femdr));
            }
        }
    }

    public void DaysSpinner()
    {
        final Spinner spinner = (Spinner) findViewById(R.id.days);
        String[] daysspr = new String[]{"Select a day", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        final List<String> plantsList = new ArrayList<>(Arrays.asList(daysspr));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.spinnerinlayout,plantsList){
            @Override
            public boolean isEnabled(int position){
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if(position<dayOfWeek)
                {
                    return false;
                }
                else
                {
                    return true;
                }
            }
            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                Date date = calendar.getTime();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
                if(position<dayOfWeek){
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };
        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnerinlayout);
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                Calendar calendar = Calendar.getInstance(TimeZone.getDefault());
                Date date = calendar.getTime();
                int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);

                if (position>=dayOfWeek)
                {
                    selectedday=spinner.getSelectedItem().toString().trim();
                    selectedday = selectedItemText;
                    Cursor con4 = db.rawQuery("select availday,starting,ending,availableadds from DrscheduleInfo where druserid="+druserid+"", null);
                    int flagtest=0;
                    while (con4.moveToNext())
                    {
                        while(selectedday.equals(con4.getString(0)))
                        {
                            flagtest++;
                            if (con4.getInt(1) == 13) {
                                startingt.setText("Available from : 1PM");
                            } else if (con4.getInt(1) == 14) {
                                startingt.setText("Available from : 2PM");
                            } else if (con4.getInt(1) == 15) {
                                startingt.setText("Available from : 3PM");
                            } else if (con4.getInt(1) == 16) {
                                startingt.setText("Available from : 4PM");
                            } else if (con4.getInt(1) == 17) {
                                startingt.setText("Available from : 5PM");
                            } else if (con4.getInt(1) == 18) {
                                startingt.setText("Available from : 6PM");
                            } else if (con4.getInt(1) == 19) {
                                startingt.setText("Available from : 7PM");
                            } else if (con4.getInt(1) == 20) {
                                startingt.setText("Available from : 8PM");
                            } else if (con4.getInt(1) == 21) {
                                startingt.setText("Available from : 9PM");
                            } else if (con4.getInt(1) == 22) {
                                startingt.setText("Available from : 10PM");
                            } else if (con4.getInt(1) == 23) {
                                startingt.setText("Available from : 11PM");
                            } else if (con4.getInt(1) == 24) {
                                startingt.setText("Available from : 12AM");
                            } else {
                                startingt.setText("Available from : " + con4.getInt(1) + " AM");
                            }
                            if (con4.getInt(2) == 13) {
                                endingt.setText("To :1PM");
                            } else if (con4.getInt(2) == 14) {
                                endingt.setText("To :2PM");
                            } else if (con4.getInt(2) == 15) {
                                endingt.setText("To :3PM");
                            } else if (con4.getInt(2) == 16) {
                                endingt.setText("To :4PM");
                            } else if (con4.getInt(2) == 17) {
                                endingt.setText("To :5PM");
                            } else if (con4.getInt(2) == 18) {
                                endingt.setText("To :6PM");
                            } else if (con4.getInt(2) == 19) {
                                endingt.setText("To :7PM");
                            } else if (con4.getInt(2) == 20) {
                                endingt.setText("To :8PM");
                            } else if (con4.getInt(2) == 21) {
                                endingt.setText("To :9PM");
                            } else if (con4.getInt(2) == 22) {
                                endingt.setText("To :10PM");
                            } else if (con4.getInt(2) == 23) {
                                endingt.setText("To :11PM");
                            } else if (con4.getInt(2) == 24) {
                                endingt.setText("To :12AM");
                            }
                            else{
                                endingt.setText("To :" + con4.getInt(2) + "AM");
                            }
                            checktxt.setText("Check the queue");
                            qspinner.setEnabled(true);
                            tokenSpinner();
                            bookingbtn.setEnabled(true);
                            bookingbtn.setBackground(getResources().getDrawable(R.drawable.mybutton));
                            break;
                        }

                        if(flagtest==0)
                        {
                            startingt.setText("Available from:not available ");
                            endingt.setText("To:not available");
                            checktxt.setText("");
                            bookingbtn.setEnabled(false);
                            qspinner.setEnabled(false);
                            qspinner.setAdapter(null);
                        }

                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    public void tokenSpinner ()
    {
        int size=0;
        Cursor con3 = db.rawQuery("select * from DrtokenInfo where druserid="+druserid+" and availday='"+selectedday+"'",null);
        final String[] array2 = new String[con3.getCount()];
        int i = 0;
            while (con3.moveToNext())
            {
                String uname = con3.getString(con3.getColumnIndex("tokennumber"));
                array2[i] = uname;
                i++;
            }

        List<String> plantsList = new ArrayList<>(Arrays.asList(array2));
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinnerinlayout, plantsList)
        {
            @Override
            public boolean isEnabled(int position) {
                if (array2[position].contains("Booked")) {
                    return false;
                } else {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView, ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if (array2[position].contains("Booked")) {
                    tv.setTextColor(Color.GRAY);
                } else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.spinnerinlayout);
        qspinner.setAdapter(spinnerArrayAdapter);
        qspinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position >=0)
                {
                    tokennostr=selectedItemText;
                    Cursor con3 = db.rawQuery("select rowid from DrtokenInfo where druserid="+druserid+" and availday='"+selectedday+"' and tokennumber='"+tokennostr+"'",null);
                    while (con3.moveToNext()){
                        tokenrowid=con3.getInt(0);
                    }
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                showMessage("Alarm", "select starting time");
            }
        });
    }
    private void register()
    {
        drprofile=findViewById(R.id.usericon);
        qspinner = (Spinner) findViewById(R.id.queue);
        name=(TextView)findViewById(R.id.userdr);
        specaility=(TextView)findViewById(R.id.drspeciality);
        adds1=(TextView)findViewById(R.id.draddress);
        email=(TextView)findViewById(R.id.dremailtxt);
        mobno=(TextView)findViewById(R.id.drphonetext);
        gender=(TextView)findViewById(R.id.drgender);
        bookingbtn=(Button)findViewById(R.id.bookbtn);
        startingt=(TextView)findViewById(R.id.opentime);
        endingt=(TextView)findViewById(R.id.closeime);
        checktxt=(TextView)findViewById(R.id.chechqtxt);
        bookingbtn.setOnClickListener(this);
        bookingbtn.setEnabled(false);


    }
    public void showMessage(String title,String message)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
    @Override
    public void onClick(View v)
    {
        Cursor conup = db.rawQuery("UPDATE DrtokenInfo SET tokennumber='"+tokennostr+"/Booked"+"' WHERE druserid="+druserid+" and availday='"+selectedday+"' and tokennumber='"+tokennostr+"'", null);
        while (conup.moveToNext()){}
        Intent last=new Intent(Doctor_Details.this, Confirmed.class);
        last.putExtra("tokenrowid",tokenrowid);
        last.putExtra("tokenno",tokennostr);
        last.putExtra("druserid",druserid);
        last.putExtra("drname",drname);
        last.putExtra("dradds2",dradds2);
        last.putExtra("visitingday",selectedday);
        last.putExtra("visitingadds",visitingadds);
        last.putExtra("drcontactno",drcontactno);
        startActivity(last);
    }
}