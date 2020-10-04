package com.example.healthcareproject.doctoruser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.healthcareproject.patientuser.Drschedul;
import com.example.healthcareproject.R;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Home_Activitydtr extends AppCompatActivity implements View.OnClickListener{

    private Button schedul,patientq;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;

    SQLiteDatabase db;
    static public String dremail,drname,drspecaility,drexperiance,drmobile,dradds,drpassword,drgender,avlday,drclinicadds;
    static public int druserid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.homedtr);
        register();
        Dravlday();

        String druseridstr=getIntent().getStringExtra("druserid");
        drclinicadds=getIntent().getStringExtra("drclinicadds");
        druserid=Integer.parseInt(druseridstr);
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        Cursor con=db.rawQuery("Select * from MyDoctorInfos where rowid="+Integer.parseInt(druseridstr)+"",null);
        while (con.moveToNext())
        {
            drname=con.getString(0);
            dremail=con.getString(1);
            drspecaility=con.getString(2);
            drexperiance=con.getString(3);
            drmobile=con.getString(4);
            dradds=con.getString(5);
            drpassword=con.getString(6);
            drgender=con.getString(7);
        }
        dl = (DrawerLayout)findViewById(R.id.homelayoutdr);
        t = new ActionBarDrawerToggle(this, dl,R.string.Open, R.string.Close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView)findViewById(R.id.nv1);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch(id)
                {
                    case R.id.accountdr:
                        Intent intent=new Intent(Home_Activitydtr.this, Myaccountdr.class);
                        startActivity(intent);
                        break;
                    case R.id.mycartdr:
                        Intent intent1=new Intent(Home_Activitydtr.this, Drsetting.class);
                        startActivity(intent1);
                        break;
                    case R.id.myschedule:
                        Intent intent2=new Intent(Home_Activitydtr.this, Myschedule.class);
                        startActivity(intent2);
                        break;
                    default:
                        return true;
                }
                return true;

            }
        });
    }

    private void Dravlday() {
        Spinner spinner = (Spinner) findViewById(R.id.drdays);
        String[] plants = new String[]{"Select a day to check the queue", "Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        List<String> plantsList = new ArrayList<>(Arrays.asList(plants));
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
        spinner.setAdapter(spinnerArrayAdapter);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selectedItemText = (String) parent.getItemAtPosition(position);
                if (position > 0) {
                    avlday = selectedItemText;
                    patientq.setEnabled(true);
                    patientq.setBackground(getDrawable(R.drawable.mybutton));
                    Toast.makeText(getApplicationContext(), "Selected :" + selectedItemText, Toast.LENGTH_SHORT).show();
                }
            }
            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    private void register()
    {
        patientq=(Button)findViewById(R.id.patientq);
        schedul=(Button)findViewById(R.id.drschedule);
        schedul.setOnClickListener(this);
        patientq.setOnClickListener(this);
        patientq.setEnabled(false);
    }

    @Override
    public void onClick(View v)
    {
        int id=v.getId();
        switch (id) {
            case R.id.drschedule:
                Intent intent = new Intent(Home_Activitydtr.this, Drschedul.class);
                startActivity(intent);
                break;
            case R.id.patientq:
                Intent intent2 = new Intent(Home_Activitydtr.this, Mypatients.class);
                intent2.putExtra("day",avlday);
                startActivity(intent2);
                break;
            }
            return;
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
