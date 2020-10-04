package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.List;

public class Mypatients extends AppCompatActivity {

    private SQLiteDatabase db;
    private List<Helodrhome> heroListdr;
    private ListView listView2;
    private String ptnamestr, ptcontactstr, ptemailstr, ptaddsstr, ptptgenderstr, visitingdaystr, visitingaddsstr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mypatients);
        String day=getIntent().getStringExtra("day");
        heroListdr = new ArrayList<>();
        listView2 = (ListView) findViewById(R.id.mypatientslist);
        drQueueyes(day);
    }
    private void drQueueyes(String avlday) {
        String selectedday = avlday;
        int flag=0;

        try{
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        String myquery = "select * from PatientsQueueInfo where druserid=" + Home_Activitydtr.druserid + " and visitingday='" + selectedday + "' ORDER BY tokennumber ASC";
        Cursor con = db.rawQuery(myquery, null);
        while (con.moveToNext()) {
            int ptuserid = con.getInt(0);
            int tokenrowid;
            String userinfo = "select * from MyPatiensInfo where rowid=" + ptuserid + "";
            Cursor con2 = db.rawQuery(userinfo, null);
            while (con2.moveToNext()) {
                ptnamestr = con2.getString(0);
                ptcontactstr = con2.getString(1);
                ptemailstr = con2.getString(2);
                ptaddsstr = con2.getString(3);
                ptptgenderstr = con2.getString(5);
            }
            String tokennumber = con.getString(1);
            tokenrowid = con.getInt(2);
            visitingdaystr = con.getString(4);
            visitingaddsstr = con.getString(5);
            flag++;
            if (ptptgenderstr.equals("male")) {
                heroListdr.add(new Helodrhome(R.drawable.ic_account_circle_black_24dp, ptnamestr, ptcontactstr, ptptgenderstr, ptemailstr, tokennumber, visitingdaystr, visitingaddsstr, ptuserid, tokenrowid));
                MyListAdapterdrhome adapterdr = new MyListAdapterdrhome(this, R.layout.my_custom_listdrhome, heroListdr);
                listView2.setAdapter(adapterdr);
            } else {
                heroListdr.add(new Helodrhome(R.drawable.female, ptnamestr, ptcontactstr, ptptgenderstr, ptemailstr, tokennumber, visitingdaystr, visitingaddsstr, ptuserid, tokenrowid));
                MyListAdapterdrhome adapterdr = new MyListAdapterdrhome(this, R.layout.my_custom_listdrhome, heroListdr);
                listView2.setAdapter(adapterdr);
            }
        }
        if(flag==0){
            showMessage("Empty-Queue","No patient for today!");
        }
        }catch (Exception e){
            showMessage("Empty-Queue","No patient for today!");
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