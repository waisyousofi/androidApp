package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Newpasswordoutsidedr extends AppCompatActivity implements View.OnClickListener {

    private Button submitbtn;
    private EditText newnameobj;
    private SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassword);
        register();
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
    }
    private void register() {
        submitbtn = (Button) findViewById(R.id.newpasswdbtn);
        newnameobj = (EditText) findViewById(R.id.newpasswordtxt);
        submitbtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        String dremailstr = getIntent().getStringExtra("email");
        String drnamestr = getIntent().getStringExtra("doctor_name");

        String newpasswdstr = newnameobj.getText().toString().trim();
        int status=passwordCheck(newpasswdstr);
        if (status!=0){
            newnameobj.setError("password must be at least 8 characters,include A-Z,a-z,0-9 and a special character(@,#,$,%,&,*)");
            newnameobj.requestFocus();
            return;
        }
        else
        {
            Cursor con = db.rawQuery("update MyDoctorInfos set drpassword='"+newpasswdstr+"' where dremailid='"+dremailstr+"' and drname='"+drnamestr+"'", null);
            while (con.moveToNext()) {
            }
            Toast.makeText(this, "password changed successfully", Toast.LENGTH_SHORT).show();
            Intent intent=new Intent(Newpasswordoutsidedr.this, DoctorLogin.class);
            startActivity(intent);
            finish();
        }
    }
    private int passwordCheck(String drpasswordstr){
        int status=0;
        String pwd = drpasswordstr;
        char[] pwdarr = pwd.toCharArray();
        int lnt = pwdarr.length;
        System.out.println(lnt);
        if (lnt<8) {
            status++;
        } else {
            int cl = 0, sl = 0, num = 0, sp = 0;
            for (int i = 0; i < lnt; i++) {
                if (pwdarr[i] >= 'A' && pwdarr[i] <= 'Z') {
                    cl++;
                } else if (pwdarr[i] >= 'a' && pwdarr[i] <= 'z') {
                    sl++;
                } else if (pwdarr[i] >= '0' && pwdarr[i] <= '9') {
                    num++;
                } else if (pwdarr[i] == '@' || pwdarr[i] == '#' || pwdarr[i] == '$' || pwdarr[i] == '%' || pwdarr[i] == '&' || pwdarr[i] == '*') {
                    sp++;
                }
            }
            if (cl == 0 || sl == 0 || num == 0 || sp == 0) {
                status++;
            }
        }
        return status;
    }
}

