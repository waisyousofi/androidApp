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

public class Reviews extends AppCompatActivity {
    SQLiteDatabase db;
    List<Helorv> heroListrv;
    ListView listView8;
    String comment,nostars,patienname;
    int druserid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reviews);
        druserid=getIntent().getIntExtra("druserid",0);
        heroListrv = new ArrayList<>();
        listView8 = (ListView) findViewById(R.id.listView8);
        int flag = 0;
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        String myquery = "select * from allFeedback where druserid="+druserid+"";
        Cursor con = db.rawQuery(myquery, null);
        while (con.moveToNext()) {
            flag = 1;
            patienname=con.getString(0);
            comment = con.getString(1);
            nostars = con.getString(2);
            heroListrv.add(new Helorv(patienname,nostars,comment));
            MyListAdapterrv adapterrv = new MyListAdapterrv(this, R.layout.my_custom_listreview, heroListrv);
            listView8.setAdapter(adapterrv);
        }

        if(flag==0)
        {
            Toast.makeText(getApplicationContext(),"No review yet",Toast.LENGTH_LONG).show();
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