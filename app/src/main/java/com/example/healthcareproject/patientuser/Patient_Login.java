package com.example.healthcareproject.patientuser;

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
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Patient_Login extends AppCompatActivity implements View.OnClickListener {


    public EditText emailed, password;
    Button loginButton;
    TextView signUp,forgotpasswd;
    CheckBox show_hide_password;
    LinearLayout loginLayout;
    SQLiteDatabase db;

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_layout);
        initViews();
        setListeners();
    }


    public void initViews() {

        emailed = findViewById(R.id.login_emailid);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.singinbtn);
        signUp = findViewById(R.id.createAccount);
        show_hide_password =  findViewById(R.id.show_hide_password);
        loginLayout = findViewById(R.id.login_layout);
        forgotpasswd= findViewById(R.id.forgot_password);
    }

    public void setListeners()
    {
        loginButton.setOnClickListener(this);
        signUp.setOnClickListener(this);
        forgotpasswd.setOnClickListener(this);
        show_hide_password.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(CompoundButton button,
                                         boolean isChecked) {

                if (isChecked) {
                    show_hide_password.setText(R.string.hide_pwd);
                    password.setInputType(InputType.TYPE_CLASS_TEXT);
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    show_hide_password.setText(R.string.show_pwd);
                    password.setInputType(InputType.TYPE_CLASS_TEXT |InputType.TYPE_TEXT_VARIATION_PASSWORD);
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }
    @Override
    public void onClick(View v) {
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        switch (v.getId()) {
            case R.id.singinbtn:
                checkValidation();
                break;
            case R.id.createAccount:
                Intent intent2 = new Intent(this, SignUp_Activity.class);
                startActivity(intent2);
                break;
            case R.id.forgot_password:
                Intent intent3=new Intent(this, Forgotpasswd.class);
                startActivity(intent3);
                break;
        }
    }

    public void checkValidation() {

        String getEmailId = emailed.getText().toString();
        String getPassword = password.getText().toString();
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(getEmailId);


        if ((getEmailId.equals("") || getEmailId.length() == 0) || (getPassword.equals("") || getPassword.length() == 0) ){
            showMessage("Failed","Enter both credentials");
        }
        else if (!m.find()){
            showMessage("Failed","your email-id is invalid");
        }
        else{
            Cursor con=db.rawQuery("select rowid,* from MyPatiensInfo",null);
            int flag=0;
            while(con.moveToNext()){
                int userid=con.getInt(con.getColumnIndexOrThrow("rowid"));
                if(con.getString(2).equals(getEmailId) && con.getString(5).equals(getPassword))
                {
                    flag=1;
                    Intent homeIntent = new Intent(this, Home_Activity.class);
                    homeIntent.putExtra("ptid",String.valueOf(userid));
                    homeIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                    startActivity(homeIntent);
                    break;
                }
            }
            if(flag==0)
            {
                showMessage("Not Registered User","Please register your details,First.");
            }
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