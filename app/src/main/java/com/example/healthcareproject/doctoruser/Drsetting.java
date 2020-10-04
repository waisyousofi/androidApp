package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.MainActivity;
import com.example.healthcareproject.R;
import com.example.healthcareproject.patientuser.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Drsetting extends AppCompatActivity implements View.OnClickListener {
    private Button chname,chpassword,chemail,chaddress,chmob,drdeleteacountbtn;
    private SQLiteDatabase db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drsetting);
        register();
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
    }

    private void register()
    {
        chname=(Button)findViewById(R.id.changenamedr);
        chpassword=(Button)findViewById(R.id.changepassworddr);
        chemail=(Button)findViewById(R.id.change_emaildr);
        chaddress=(Button)findViewById(R.id.changenaddsdr);
        chmob=(Button)findViewById(R.id.changemobdr);
        drdeleteacountbtn=findViewById(R.id.drdeleteacount);
        drdeleteacountbtn.setOnClickListener(this);
        chmob.setOnClickListener(this);
        chname.setOnClickListener(this);
        chpassword.setOnClickListener(this);
        chemail.setOnClickListener(this);
        chaddress.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.changenamedr:
                drNewName();
                break;
            case R.id.changepassworddr:
                drNewPassword();
                break;
            case R.id.change_emaildr:
                drNewEmailid();
                break;
            case R.id.changenaddsdr:
                drNewAddress();
                break;
            case R.id.changemobdr:
                drNewMobileno();
                break;
            case R.id.drdeleteacount:
                drDeleteacnt();
                break;
        }
    }

    private void drDeleteacnt() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Are you sure,to remove your account?");
        builder.setCancelable(true);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Delete my account", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                try {
                    Cursor con = db.rawQuery("delete from MyDoctorInfos where rowid=" + Home_Activitydtr.druserid + "", null);
                    Cursor condt = db.rawQuery("delete from DrscheduleInfo where druserid=" + Home_Activitydtr.druserid + "", null);
                    Cursor contdt = db.rawQuery("delete from DrtokenInfo where druserid=" + Home_Activitydtr.druserid + "", null);
                    Cursor condete = db.rawQuery("delete from PatientsQueueInfo where druserid=" + Home_Activitydtr.druserid + "", null);
                    while (condt.moveToNext()) {
                    }
                    while (contdt.moveToNext()) {
                    }
                    while (con.moveToNext()) {
                    }
                    while (condete.moveToNext()) {
                    }
                    Toast.makeText(getApplicationContext(), "Your account is been removed.", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(Drsetting.this, MainActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(intent);
                    finish();
                }catch (Exception e){Toast.makeText(getApplicationContext(), "unable to remove your account.", Toast.LENGTH_SHORT).show();}
            }
        }).show();
    }
    private void drNewMobileno() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Drsetting.this);
        builder.setTitle("Enter your new name:");

        LayoutInflater inflater=getLayoutInflater().from(this);
        View myview=inflater.inflate(R.layout.user_detailsupdate,null);
        final EditText newnameobj=myview.findViewById(R.id.newpasswordtxt2);
        newnameobj.requestFocus();
        builder.setView(myview);
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String drnewmobno = newnameobj.getText().toString().toLowerCase().trim();
                if (drnewmobno.equals("") || drnewmobno.length()!=10) {
                    Toast.makeText(Drsetting.this, "Not updated!", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor con = db.rawQuery("update MyDoctorInfos set drphoneno='"+drnewmobno+"' where rowid="+Home_Activitydtr.druserid+"", null);
                    while (con.moveToNext()) {
                    }
                    Toast.makeText(Drsetting.this, "Mobile number updated!", Toast.LENGTH_SHORT).show();
                }
            }
        }).show();
    }
    private void drNewAddress() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Drsetting.this);
        builder.setTitle("Enter your new name:");

        LayoutInflater inflater=getLayoutInflater().from(this);
        View myview=inflater.inflate(R.layout.user_detailsupdate,null);
        final EditText newnameobj=myview.findViewById(R.id.newpasswordtxt2);
        newnameobj.requestFocus();
        builder.setView(myview);
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String drnewadds = newnameobj.getText().toString().toLowerCase();
                if (drnewadds.equals("") || drnewadds.length()==0){
                    Toast.makeText(Drsetting.this, "Not updated !", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor con = db.rawQuery("update MyDoctorInfos set drcityadds='"+drnewadds+"' where rowid="+Home_Activitydtr.druserid+"", null);
                    while (con.moveToNext()) {}
                    Toast.makeText(Drsetting.this, "address updated!", Toast.LENGTH_SHORT).show();
                }
            }
        }).show();
    }
    private void drNewEmailid() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Drsetting.this);
        builder.setTitle("Enter your new email:");

        LayoutInflater inflater=getLayoutInflater().from(this);
        View myview=inflater.inflate(R.layout.user_detailsupdate,null);
        final EditText newnameobj=myview.findViewById(R.id.newpasswordtxt2);
        newnameobj.requestFocus();
        builder.setView(myview);
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String namestr = newnameobj.getText().toString().trim();
                Pattern p = Pattern.compile(Utils.regEx);
                Matcher m = p.matcher(namestr);
                if (namestr.equals("") || namestr.length() == 0) {
                    Toast.makeText(Drsetting.this, "Not updated.", Toast.LENGTH_SHORT).show();
                } else if (!m.find()) {
                    Toast.makeText(Drsetting.this, "invalid format", Toast.LENGTH_SHORT).show();
                    newnameobj.requestFocus();
                    return;
                } else {
                    Cursor con = db.rawQuery("update MyDoctorInfos set dremailid='"+namestr+"' where rowid="+Home_Activitydtr.druserid+"", null);
                    while (con.moveToNext()) {
                    }
                    Toast.makeText(Drsetting.this, "emailid updated.", Toast.LENGTH_SHORT).show();
                }
            }
        }).show();
    }
    private void drNewPassword() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Drsetting.this);
        builder.setTitle("Enter your new password:");

        LayoutInflater inflater=getLayoutInflater().from(this);
        View myview=inflater.inflate(R.layout.user_detailsupdate,null);
        final EditText newnameobj=myview.findViewById(R.id.newpasswordtxt2);
        newnameobj.requestFocus();
        builder.setView(myview);
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String drnewpasswd = newnameobj.getText().toString();
                int status = passwordCheck(drnewpasswd);
                if (status != 0) {
                    newnameobj.setError("password must contain at least 8 characters,include A-Z,a-z,0-9 and a special character(@,#,$,%,&,*)");
                    newnameobj.requestFocus();
                    Toast.makeText(Drsetting.this, "password must contain at least 8 characters,include A-Z,a-z,0-9 and a special character(@,#,$,%,&,*)", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Cursor con = db.rawQuery("update MyDoctorInfos set drpassword='" + drnewpasswd + "' where rowid=" + Home_Activitydtr.druserid + "", null);
                    while (con.moveToNext()) {
                    }
                    Toast.makeText(Drsetting.this, "password changed successfully", Toast.LENGTH_SHORT).show();
                    newnameobj.setText("");
                }
            }
            private int passwordCheck(String drpasswordstr) {
                int status = 0;
                String pwd = drpasswordstr;
                char[] pwdarr = pwd.toCharArray();
                int lnt = pwdarr.length;
                System.out.println(lnt);
                if (lnt < 8) {
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
        }).show();
    }
    private void drNewName() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Drsetting.this);
        builder.setTitle("Enter your new name:");

        LayoutInflater inflater=getLayoutInflater().from(this);
        View myview=inflater.inflate(R.layout.user_detailsupdate,null);
        final EditText newnameobj=myview.findViewById(R.id.newpasswordtxt2);
        newnameobj.requestFocus();
        builder.setView(myview);
        builder.setCancelable(false);
        builder.setNegativeButton("Dismiss", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
            }
        }).setPositiveButton("Update", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String drnamestr = newnameobj.getText().toString().toLowerCase().trim();
                if (drnamestr.equals("") || drnamestr.length() == 0) {
                    Toast.makeText(Drsetting.this, "Not updated.", Toast.LENGTH_SHORT).show();
                } else {
                    Cursor con = db.rawQuery("update MyDoctorInfos set drname='" + drnamestr + "' where rowid=" + Home_Activitydtr.druserid + "", null);
                    while (con.moveToNext()) {
                    }
                    Toast.makeText(Drsetting.this, "name updated.", Toast.LENGTH_SHORT).show();
                    newnameobj.setText("");
                }
            }
        }).show();
    }
}