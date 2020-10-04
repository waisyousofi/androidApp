package com.example.healthcareproject.patientuser;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.healthcareproject.R;

public class HistoryInfo extends Activity {
    private SQLiteDatabase db;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.historyinfo);

        int druserid=getIntent().getIntExtra("druserid",0);
        String tokenno=getIntent().getStringExtra("pttokenno");
        String vday=getIntent().getStringExtra("vday");
        register(druserid,tokenno,vday);
    }

    private void register(int druserid, String tokenno,String vday) {
        TextView drname=findViewById(R.id.histdrname);
        TextView drcontacts=findViewById(R.id.histdrcontacts);
        TextView drspety=findViewById(R.id.histdrspeciality);
        TextView vgday=findViewById(R.id.histdayname);
        TextView pttokenno=findViewById(R.id.histtokentxt);
        TextView drlocantions=findViewById(R.id.histdrlocation);

        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        Cursor con=db.rawQuery("select * from MyDoctorInfos where rowid="+druserid+"",null);
        while (con.moveToNext()){
            Cursor con2=db.rawQuery("select  availableadds from DrscheduleInfo where druserid="+druserid+"",null);
            while (con2.moveToNext()){drlocantions.setText("Address :"+con2.getString(0)+","+con.getString(5));}
            drname.setText("Dr."+con.getString(0));
            drcontacts.setText("Contacts: "+con.getString(4)+","+con.getString(1));
            drspety.setText("Speciality: "+con.getString(2));
            vgday.setText("visit on: "+vday);
            pttokenno.setText("Your Token-number: "+tokenno);
            con2.close();
        }
        con.close();
    }
}