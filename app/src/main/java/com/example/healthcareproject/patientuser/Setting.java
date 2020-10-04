package com.example.healthcareproject.patientuser;
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

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Setting extends AppCompatActivity implements View.OnClickListener {
    private Button chname,chpassword,chemail,chaddress,chmob,deleteacntbtn;
    public String useremail;
    private SQLiteDatabase db;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        register();
    }
    private void register() {
        chname = (Button) findViewById(R.id.changename);
        chpassword = (Button) findViewById(R.id.changepassword);
        chemail = (Button) findViewById(R.id.change_email);
        chaddress = (Button) findViewById(R.id.changenadds);
        chmob = (Button) findViewById(R.id.changemob);
        deleteacntbtn=findViewById(R.id.deleteacnt);
        deleteacntbtn.setOnClickListener(this);
        chmob.setOnClickListener(this);
        chname.setOnClickListener(this);
        chpassword.setOnClickListener(this);
        chemail.setOnClickListener(this);
        chaddress.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        useremail=getIntent().getStringExtra("useremail1");
        switch (v.getId()) {
            case R.id.changename:
                ptNewName();
                break;
            case R.id.changepassword:
                ptNewpassword();
                break;
            case R.id.change_email:
                ptNewEmailid();
                break;
            case R.id.changenadds:
                ptNewAddress();
                break;
            case R.id.changemob:
                ptNewMobileno();
                break;
            case R.id.deleteacnt:
                deleteAcount();
                break;
        }
    }

    private void deleteAcount() {

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
                        Cursor con = db.rawQuery("delete from MyPatiensInfo where rowid='" + Home_Activity.ptid + "'", null);
                        Cursor condete = db.rawQuery("delete from PatientsQueueInfo where ptuserid=" + Home_Activity.ptid + "", null);
                        while (con.moveToNext()) {
                        }
                        while (condete.moveToNext()) {
                        }
                        Toast.makeText(getApplicationContext(), "Your account is been removed.", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Setting.this, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                        finish();
                    }catch (Exception e){Toast.makeText(getApplicationContext(), "unable to remove your account.", Toast.LENGTH_SHORT).show();}
            }
        }).show();
    }
    private void ptNewMobileno() {
        AlertDialog.Builder builder=new AlertDialog.Builder(Setting.this);
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
                String addslstr =newnameobj.getText().toString().trim();
                if(!(addslstr.length()==10)) {
                    Toast.makeText(getApplicationContext(),"Not updated !\nMobile number must be 10 digits only.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor con = db.rawQuery("update MyPatiensInfo set phoneno='" + addslstr + "' where rowid='" + Home_Activity.ptid + "'", null);
                    while (con.moveToNext()) {}
                    Toast.makeText(getApplicationContext(),"Mobile number updated.",Toast.LENGTH_SHORT).show();
                    con.close();
                }
            }
        }).show();
    }
    private void ptNewAddress() {

        AlertDialog.Builder builder=new AlertDialog.Builder(Setting.this);
        builder.setTitle("Enter your new address:");

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
                String addslstr = newnameobj.getText().toString();
                if(addslstr.equals("")||addslstr.length()==0)
                {
                    Toast.makeText(getApplicationContext(),"Not updated.",Toast.LENGTH_SHORT).show();
                }
                else {
                    Cursor con = db.rawQuery("update MyPatiensInfo set address='" + addslstr + "' where rowid='" +Home_Activity.ptid + "'", null);
                    while (con.moveToNext()) {}
                    con.close();
                    Toast.makeText(getApplicationContext(),"address updated.",Toast.LENGTH_SHORT).show();
                }
            }
        }).show();
    }
    private void ptNewEmailid() {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Enter your new password:");

        LayoutInflater inflater = getLayoutInflater().from(Setting.this);
        View myview = inflater.inflate(R.layout.user_detailsupdate, null);
        final EditText newnameobj = myview.findViewById(R.id.newpasswordtxt2);
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
                    Toast.makeText(Setting.this, "Not updated.", Toast.LENGTH_SHORT).show();
                } else if (!m.find()) {
                    Toast.makeText(Setting.this, "invalid format", Toast.LENGTH_SHORT).show();
                    newnameobj.requestFocus();
                    return;
                } else {
                    Cursor con = db.rawQuery("update MyPatiensInfo set emailid='" + namestr + "' where rowid='" + Home_Activity.ptid + "'", null);
                    while (con.moveToNext()) {
                    }
                    Toast.makeText(getApplicationContext(), "Email-id Updated!", Toast.LENGTH_SHORT).show();
                }
            }
        }).show();
    }

    private void ptNewpassword(){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
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
                String namestr = newnameobj.getText().toString().trim();
                int i=passwordCheck(namestr);
                if (namestr.equals("") || namestr.length() == 0) {
                    Toast.makeText(Setting.this, "Not updated.", Toast.LENGTH_SHORT).show();
                }
                else if(i!=0){
                    newnameobj.setError("password must be at least 8 characters,include A-Z,a-z,0-9 and a special character(@,#,$,%,&,*).",MainActivity.errorpic);
                    newnameobj.requestFocus();
                }
                else
                {
                    Cursor con = db.rawQuery("update MyPatiensInfo set password='" + namestr + "' where rowid='" + Home_Activity.ptid + "'", null);
                    while (con.moveToNext()) {
                    }
                    Toast.makeText(Setting.this, "Password updated.", Toast.LENGTH_SHORT).show();
                    newnameobj.setText("");
                    con.close();
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
        }).show();
    }
    private void ptNewName() {

        AlertDialog.Builder builder=new AlertDialog.Builder(Setting.this);
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
                })
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String namestr = newnameobj.getText().toString().trim();
                        if(namestr.equals("")||namestr.length()==0)
                        {
                            Toast.makeText(Setting.this, "Not updated.", Toast.LENGTH_SHORT).show();
                        }
                        else {
                            Cursor con = db.rawQuery("update MyPatiensInfo set name='" + namestr + "' where rowid="+Home_Activity.ptid+"", null);
                            while (con.moveToNext()) {
                            }
                            Toast.makeText(Setting.this, "Name changed successfully", Toast.LENGTH_SHORT).show();
                            newnameobj.setText("");
                            con.close();
                        }
                    }
                }).show();
    }
}