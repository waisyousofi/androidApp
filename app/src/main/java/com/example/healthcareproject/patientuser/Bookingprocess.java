package com.example.healthcareproject.patientuser;


import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class Bookingprocess extends AppCompatActivity implements View.OnClickListener {

    private Button searchbtn;
    private SQLiteDatabase db;
    private Spinner spinner1,spinner2;
    private  int firsti = 0,secondi=0,flag=0,temp=0,lasti=0,reviewnum=0;
    private String draddress,specaility1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.booking_process);
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        locationsSpinner ();
        specailitySpinner ();
        doctorsListviewAll();
        searchbtn = findViewById(R.id.findbtn);
        searchbtn.setOnClickListener(this);
    }


    public void locationsSpinner ()
    {
        firsti=secondi=temp=lasti=0;flag=1;
        spinner1 = (Spinner)findViewById(R.id.locationsfromdr);
        try {
            db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
            Cursor conln = db.rawQuery("select * from MyDoctorInfos", null);
            String[] array2 = new String[conln.getCount()];
            while (conln.moveToNext()) {
                String drspecialitystr = conln.getString(conln.getColumnIndex("drcityadds"));
                if (firsti == 0) {
                    array2[firsti] = "Select any city...";

                } else {
                    array2[firsti] = drspecialitystr;
                    System.out.println(drspecialitystr);
                }
                firsti++;
            }

            String[] second = new String[array2.length];
            for (firsti = 0; firsti < array2.length; firsti++) {
                for (secondi = 0; secondi < array2.length; secondi++) {
                    if (array2[firsti].equals(second[secondi])) {
                        flag++;
                    }
                }
                if (flag == 1) {
                    second[firsti] = array2[firsti];
                    temp++;
                }
                flag = 1;
            }

            String[] last2 = new String[temp];
            for (firsti = 0; firsti < second.length; firsti++) {
                if (second[firsti] != null) {
                    last2[lasti] = second[firsti];
                    lasti++;
                }
            }

            for (firsti = 0; firsti < last2.length; firsti++) {
                System.out.println("last2 " + last2[firsti]);
            }
            List<String> plantsList = new ArrayList<>(Arrays.asList(last2));
            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinnerinlayout, plantsList) {
                @Override
                public boolean isEnabled(int position) {
                    if (position == 0) {
                        return true;
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
                    draddress = spinner1.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    showMessage("Alarm", "select starting time");
                }
            });
        }catch (Exception e){}
    }
    public void specailitySpinner ()
    {

        firsti=secondi=temp=lasti=0;flag=1;
        spinner2 = (Spinner)findViewById(R.id.specialityfromdr);
        try {
            db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
            Cursor conspt = db.rawQuery("select * from MyDoctorInfos", null);
            String[] array2 = new String[conspt.getCount()];
            while (conspt.moveToNext()) {
                String drspecialitystr = conspt.getString(conspt.getColumnIndex("drspeciality"));
                if (firsti == 0) {
                    array2[firsti] = "Select any speciality...";

                } else {
                    array2[firsti] = drspecialitystr;
                    System.out.println(drspecialitystr);
                }
                firsti++;
            }

            String[] second = new String[array2.length];
            for (firsti = 0; firsti < array2.length; firsti++) {
                for (secondi = 0; secondi < array2.length; secondi++) {
                    if (array2[firsti].equals(second[secondi])) {
                        flag++;
                    }
                }
                if (flag == 1) {
                    second[firsti] = array2[firsti];
                    temp++;
                }
                flag = 1;
            }

            String[] last2 = new String[temp];
            for (firsti = 0; firsti < second.length; firsti++) {
                if (second[firsti] != null) {
                    last2[lasti] = second[firsti];
                    lasti++;
                }
            }

            for (firsti = 0; firsti < last2.length; firsti++) {
                System.out.println("last2 " + last2[firsti]);
            }
            List<String> plantsList = new ArrayList<>(Arrays.asList(last2));
            final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(this, R.layout.spinnerinlayout, plantsList) {
                @Override
                public boolean isEnabled(int position) {
                    if (position == 0) {
                        return true;
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
                    specaility1 = spinner2.getSelectedItem().toString();
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {
                    showMessage("Alarm", "select starting time");
                }
            });
        }catch (Exception e){}
    }
    private void doctorsListviewAll(){
        int flag5=0;
        List<Helo> heloList1=new ArrayList<>();
        ListView listView1 = (ListView) findViewById(R.id.newlistView);
        try{
            String myquery = "select rowid,* from MyDoctorInfos";
            Cursor cond2 = db.rawQuery(myquery, null);
            while (cond2.moveToNext()) {
                flag5++;
                int druserid = cond2.getInt(0);
                int flagfb = 0;
                try {
                    String myqueryfb = "select * from allFeedback where druserid=" + druserid + "";
                    Cursor confbk = db.rawQuery(myqueryfb, null);
                    while (confbk.moveToNext()) {
                        flagfb++;
                        reviewnum++;
                    }
                    if (flagfb == 0) {
                        reviewnum = 0;
                    }
                    confbk.close();
                }catch (Exception e){
                    reviewnum = 0;
                }
                String drnamestr = cond2.getString(1);
                String dremailstr = cond2.getString(2);
                String drspecialitystr = cond2.getString(3);
                String drexperiancestr = cond2.getString(4);
                String drmobilestr = cond2.getString(5);
                String drcitystr = cond2.getString(6);
                String drgenderstr = cond2.getString(8);
                if(drgenderstr.equals("male")){
                    heloList1.add(new Helo(R.drawable.mydrs, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, reviewnum));
                }else{
                    heloList1.add(new Helo(R.drawable.femdr, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, reviewnum));
                }
                MyListAdapter adapter = new MyListAdapter(this, R.layout.my_custom_list, heloList1);
                listView1.setAdapter(adapter);
            }
            if (flag5 == 0) {
                Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();
            }
            cond2.close();}catch (Exception e){
            Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onClick(View v) {

        if(draddress.equals("Select any city...") && (!specaility1.equals("Select any speciality..."))){
            specialityBased(specaility1);
        }
        else if((!draddress.equals("Select any city...")) && specaility1.equals("Select any speciality...")){
            addressBased(draddress);
        }
        else if(!(draddress.equals("Select any city...")) && !(specaility1.equals("Select any speciality..."))){
            bothBased();
        }
        else {
            Toast.makeText(this, "Please select any value !", Toast.LENGTH_SHORT).show();
        }
    }

    private void bothBased() {
        int flaga=0,reviewn=0;
        List<Helo> heloList3=new ArrayList<>();
        ListView listView3 = (ListView) findViewById(R.id.newlistView);
        try {
            String myquery = "select rowid,* from MyDoctorInfos where drcityadds='" + draddress + "' and drspeciality='" + specaility1 + "'";
            Cursor cond = db.rawQuery(myquery, null);
            while (cond.moveToNext()) {
                flaga++;
                int druserid = cond.getInt(0);
                String drnamestr = cond.getString(1);
                String dremailstr = cond.getString(2);
                String drspecialitystr = cond.getString(3);
                String drexperiancestr = cond.getString(4);
                String drmobilestr = cond.getString(5);
                String drcitystr = cond.getString(6);
                String drgenderstr = cond.getString(8);
                int flagfb = 0;
                try {
                    String myqueryfb2 = "select * from allFeedback where druserid=" + druserid + "";
                Cursor confbk2 = db.rawQuery(myqueryfb2, null);
                while (confbk2.moveToNext()) {
                    flagfb++;
                    reviewn++;
                }
                if (flagfb == 0) {
                    reviewn = 0;
                }
                confbk2.close();}catch (Exception e){reviewn = 0;}
                if(drgenderstr.equals("male")){
                    heloList3.add(new Helo(R.drawable.mydrs, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, reviewn));
                }else {
                    heloList3.add(new Helo(R.drawable.femdr, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, reviewn));
                }
                MyListAdapter adapter = new MyListAdapter(this, R.layout.my_custom_list, heloList3);
                listView3.setAdapter(adapter);
            }
            if (flaga == 0) {
                Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();
            }
            cond.close();
        }catch (Exception e){}
    }
    private void addressBased(String draddress) {
        int flag4=0;
        List<addressModel> addressModelList = new ArrayList<>();
        ListView listView2=findViewById(R.id.newlistView);
        try {
            String myquery = "select rowid,* from MyDoctorInfos where drcityadds='"+draddress+"'";
            Cursor cone = db.rawQuery(myquery, null);
            while (cone.moveToNext()) {
                flag4++;
                int druserid = cone.getInt(0);
                String drnamestr = cone.getString(1);
                String dremailstr = cone.getString(2);
                String drspecialitystr = cone.getString(3);
                String drexperiancestr = cone.getString(4);
                String drmobilestr = cone.getString(5);
                String drcitystr = cone.getString(6);
                String drgenderstr = cone.getString(8);

                int flagfb = 0, revnum = 0;
                try {
                    String myqueryfb3 = "select * from allFeedback where druserid=" + druserid + "";
                Cursor confbk3 = db.rawQuery(myqueryfb3, null);
                while (confbk3.moveToNext()) {
                    flagfb++;
                    revnum++;
                }
                if (flagfb == 0) {
                    revnum=0;
                }
                confbk3.close();}catch (Exception e){revnum=0;}
                if(drgenderstr.equals("male")) {
                    addressModelList.add(new addressModel(R.drawable.mydrs, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, revnum));
                }else{
                    addressModelList.add(new addressModel(R.drawable.femdr, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, revnum));
                }
                AddressAdapter adapter = new AddressAdapter(this, R.layout.my_custom_list, addressModelList);
                listView2.setAdapter(adapter);
            }
            if (flag4 == 0) {
                Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();
            }
            cone.close();
        }catch (Exception e){Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();}
    }
    private void specialityBased(String specaility1) {
        int flag3=0;
        List<specialityModel> specialityModelList=new ArrayList<>();
        try {
            String myquery = "select rowid,* from MyDoctorInfos where drspeciality='" + specaility1 + "'";
            Cursor conp = db.rawQuery(myquery, null);
            while (conp.moveToNext()) {
                flag3++;
                int druserid = conp.getInt(0);
                String drnamestr = conp.getString(1);
                String dremailstr = conp.getString(2);
                String drspecialitystr = conp.getString(3);
                String drexperiancestr = conp.getString(4);
                String drmobilestr = conp.getString(5);
                String drcitystr = conp.getString(6);
                String drgenderstr = conp.getString(8);

                int flagfb2 = 0, revnum2 = 0;
                try {
                    String myqueryfb4 = "select * from allFeedback where druserid=" + druserid + "";
                    Cursor confbk4 = db.rawQuery(myqueryfb4, null);
                    while (confbk4.moveToNext()) {
                        flagfb2++;
                        revnum2++;
                    }
                    if (flagfb2 == 0) {
                        revnum2 = 0;
                    }
                    confbk4.close();
                }catch (Exception e){revnum2 = 0;}
                if(drgenderstr.equals("male")){
                    specialityModelList.add(new specialityModel(R.drawable.mydrs, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, revnum2));
                }else{
                    specialityModelList.add(new specialityModel(R.drawable.femdr, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, revnum2));
                }
                SpecialityAdapter adapter = new SpecialityAdapter(this, R.layout.my_custom_list, specialityModelList);
                ListView listView3 = findViewById(R.id.newlistView);
                listView3.setAdapter(adapter);
            }
            if (flag3 == 0) {
                Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();
            }
            conp.close();
        }catch (Exception e){Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();}
    }
    public void showMessage(String title, String message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}