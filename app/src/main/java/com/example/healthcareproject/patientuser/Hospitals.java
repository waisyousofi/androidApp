package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.List;

public class Hospitals extends AppCompatActivity {
    private SQLiteDatabase db;
    private List<Helohospital> heroListhosp;
    private ListView listView5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hospitals);
        heroListhosp = new ArrayList<>();
        listView5 = (ListView) findViewById(R.id.listView55);
        addressBased();
    }

    private void addressBased() {
        int flag4=0;
        String myquery="select rowid,* from MyDoctorInfos";
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        Cursor cone = db.rawQuery(myquery, null);
        while (cone.moveToNext())
        {
            flag4++;
            int druserid=cone.getInt(0);

            String dremailstr=cone.getString(2);
            String drspecialitystr=cone.getString(3);
            String drmobilestr=cone.getString(5);
            String drcitystr=cone.getString(6);
            int flagfb = 0,revnum=0;
            String myqueryfb3 = "select * from allFeedback where druserid="+druserid+"";
            Cursor confbk3 = db.rawQuery(myqueryfb3, null);
            while (confbk3.moveToNext()) {
                flagfb++;
                revnum++;
            }
            if(flagfb==0)
            {
                revnum=0;
            }
            confbk3.close();
                heroListhosp.add(new Helohospital(drcitystr, drspecialitystr, drmobilestr,dremailstr,revnum,druserid));
                MyListAdapterhosp adapterhosp = new MyListAdapterhosp(this, R.layout.my_custom_listhosp, heroListhosp);
                listView5.setAdapter(adapterhosp);
        }
        if(flag4==0){
            Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();
        }
        cone.close();
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

