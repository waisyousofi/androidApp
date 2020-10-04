package com.example.healthcareproject.doctoruser;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.InputType;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;
import com.example.healthcareproject.patientuser.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DoctorLogin extends AppCompatActivity implements View.OnClickListener {


    private EditText dremailidobj,drpasswordobj;
    private Button loginButtondtr;
    private TextView signUpdtr,forgotpwd;
    private CheckBox show_hide_passworddtr;
    private LinearLayout loginLayoutdtr;
    private SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layoutdtr);
        initViews();
        setListeners();
    }
    public void initViews() {

        dremailidobj = (EditText) findViewById(R.id.login_emailiddtr);
        drpasswordobj= (EditText) findViewById(R.id.login_passworddtr);
        loginButtondtr = (Button) findViewById(R.id.singinbtndtr);
        signUpdtr = (TextView) findViewById(R.id.createAccountdtr);
        show_hide_passworddtr = (CheckBox) findViewById(R.id.show_hide_passworddtr);
        loginLayoutdtr = (LinearLayout) findViewById(R.id.login_layoutdtr);
        forgotpwd=(TextView)findViewById(R.id.forgot_passworddr);
    }

    public void setListeners()
    {
        loginButtondtr.setOnClickListener(this);
        signUpdtr.setOnClickListener(this);
        forgotpwd.setOnClickListener(this);
        show_hide_passworddtr.setOnCheckedChangeListener(new OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton button,boolean isChecked) {
                if (isChecked) {
                    show_hide_passworddtr.setText(R.string.hide_pwd);
                    drpasswordobj.setInputType(InputType.TYPE_CLASS_TEXT);
                    drpasswordobj.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    show_hide_passworddtr.setText(R.string.show_pwd);
                    drpasswordobj.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    drpasswordobj.setTransformationMethod(PasswordTransformationMethod.getInstance());// hide password
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        switch (v.getId()) {
            case R.id.singinbtndtr:
                checkValidation();
                break;
            case R.id.createAccountdtr:
                Intent intent2 = new Intent(this, Doctorsignup.class);
                startActivity(intent2);
                break;
            case R.id.forgot_passworddr:
                Intent intent=new Intent(DoctorLogin.this, Forgotpasswddr.class);
                startActivity(intent);
                break;
        }
    }

    public void checkValidation() {
        String dremailstr = dremailidobj.getText().toString();
        String drpasswordstr = drpasswordobj.getText().toString();
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(dremailstr);

        if ((dremailstr.equals("") || dremailstr.length() == 0) || (drpasswordstr.equals("") || drpasswordstr.length() == 0) ){
            showMessage("Failed","Enter both the credentials");
        }
        else if (!m.find()){
            showMessage("Failed","your email-id is invalid");
        }
        else{
//            db.execSQL("INSERT INTO MyDoctorInfos VALUES('"+drnamestr+"','"+dremailstr+"','"+drspecialitystr+"','"+drexperiacestr+"','"+drmobilenumberstr+"','"+ drlocationstr+"','"+drpasswordstr+"','"+drm+"');");
            Cursor con=db.rawQuery("select rowid,* from MyDoctorInfos",null);
            int flag=0;
            while(con.moveToNext()){
                if(con.getString(2).equals(dremailstr) && con.getString(7).equals(drpasswordstr))
                {
                    int druserid=con.getInt(con.getColumnIndexOrThrow("rowid"));
                    String drclinicadds=getIntent().getStringExtra("drclinicadds");
                    flag=1;
                    Intent homeIntent = new Intent(this, Home_Activitydtr.class);
                    homeIntent.putExtra("druserid",String.valueOf(druserid));
                    homeIntent.putExtra("drclinicadds",drclinicadds);
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(homeIntent);
                    break;
                }
            }
            if(flag==0)
            {showMessage("Not-Registered","Please register your details,First.");}
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