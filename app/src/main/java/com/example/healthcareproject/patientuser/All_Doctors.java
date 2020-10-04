package com.example.healthcareproject.patientuser;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.List;

public class All_Doctors extends Activity implements SearchView.OnQueryTextListener{

    private SQLiteDatabase db;
    private MyListAdapter22 adapter2;
    private SearchView searchView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.all_doctors);
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        searchView=findViewById(R.id.searchtextid55);
        if (searchView.requestFocus()==true){
            nametextWater();
        }
    }

    private void nametextWater() {

        ListView listView;
        ArrayList<MyModel> myModelArrayList=new ArrayList<>();
        listView=findViewById(R.id.listviewid55);
        searchView.setOnQueryTextListener(this);

        ArrayList<String> ar=new ArrayList<>();
        try {
            Cursor condname = db.rawQuery("select drname from MyDoctorInfos", null);
            while (condname.moveToNext()) {
                ar.add(condname.getString(0));
            }
            condname.close();
            }catch (Exception e){}
            int i = 0;
            for (String str : ar) {
                MyModel model = new MyModel(ar.get(i));
                i++;
                myModelArrayList.add(model);
            }
            adapter2 = new MyListAdapter22(All_Doctors.this, myModelArrayList);
            listView.setAdapter(adapter2);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    TextView textView = view.findViewById(R.id.searchtextitemex22);
                    String str = textView.getText().toString();
                    doctorsListviewAll(str);
                }
            });
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        adapter2.myfilter(newText);
        if(newText.equals("")){
            nametextWater();
        }
        return false;
    }
    private void doctorsListviewAll(String searchstr){
        int flag5=0,reviewnum=0;
        String str=searchstr;
        try {
            String myquery = "select rowid,* from MyDoctorInfos where drname='" + str + "'";
            ListView listView=findViewById(R.id.listviewid55);
            Cursor cond2 = db.rawQuery(myquery, null);
            List<Helo> heloList1 = new ArrayList<>();
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
                } catch (Exception e) {
                    reviewnum = 0;
                }
                String drnamestr = cond2.getString(1);
                String dremailstr = cond2.getString(2);
                String drspecialitystr = cond2.getString(3);
                String drexperiancestr = cond2.getString(4);
                String drmobilestr = cond2.getString(5);
                String drcitystr = cond2.getString(6);
                String drgenderstr = cond2.getString(8);
                if(drgenderstr.equals("female")){
                    heloList1.add(new Helo(R.drawable.femdr, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, reviewnum));
                }else{
                    heloList1.add(new Helo(R.drawable.mydrs, druserid, drnamestr, drspecialitystr, drexperiancestr, drcitystr, dremailstr, drmobilestr, drgenderstr, reviewnum));
                }
                MyListAdapter adapter2 = new MyListAdapter(this, R.layout.my_custom_list, heloList1);
                listView.setAdapter(adapter2);
            }
            if (flag5 == 0) {
                Toast.makeText(this, "No Doctor found.", Toast.LENGTH_SHORT).show();
            }
            cond2.close();
        }catch (Exception e){Toast.makeText(this, "No Doctor available.", Toast.LENGTH_SHORT).show();}
    }
}