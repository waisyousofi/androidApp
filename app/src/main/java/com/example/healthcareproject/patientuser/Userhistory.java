package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.List;


public class Userhistory extends AppCompatActivity {
    SQLiteDatabase db;
    List<Helouser> heroListur;
    ListView listView3;
    ImageView drimg;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_process2);

        heroListur = new ArrayList<>();
        listView3 = (ListView) findViewById(R.id.urhistrtlistView);
        drimg=findViewById(R.id.userhistimage);
        int flag=0;
        try {
            db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
            Cursor con = db.rawQuery("select * from PatientsQueueInfo where ptuserid=" + Home_Activity.ptid + "", null);
            while (con.moveToNext()) {
                String pttokenno = con.getString(1);
                int tokenrowid = con.getInt(2);
                int druserid = con.getInt(3);
                String visitingdaystr = con.getString(4);
                String visitingadds = con.getString(5);
                String drname = con.getString(6);
                flag = 1;
                try {
                    Cursor congendr = db.rawQuery("select drgender from MyDoctorInfos where rowid=" + druserid + "", null);
                    while (congendr.moveToNext()){
                        String drgend=congendr.getString(0);
                        if (drgend.equals("female")) {
                            heroListur.add(new Helouser(R.drawable.femdr, druserid, Home_Activity.ptid, visitingdaystr, visitingadds, pttokenno, tokenrowid, drname));
                            } else {
                            heroListur.add(new Helouser(R.drawable.mydrs, druserid, Home_Activity.ptid, visitingdaystr, visitingadds, pttokenno, tokenrowid, drname));
                            }
                        }
                        congendr.close();
                    }catch (Exception e){}
                MyListAdapteruser adapterur = new MyListAdapteruser(Userhistory.this, R.layout.my_custom_listuser, heroListur);
                listView3.setAdapter(adapterur);
            } con.close();
        }catch (Exception e){}
            if (flag == 0) {
                showMessage("No appointment", " No history yet");
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