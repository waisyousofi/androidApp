package com.example.healthcareproject.patientuser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Forgotpasswd extends AppCompatActivity implements View.OnClickListener{

    EditText name,email;
    Button submt,back;

    SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword_layout);
        register();
    }
    public void register()
    {
        name=(EditText)findViewById(R.id.registered_name);
        email=(EditText)findViewById(R.id.registered_emailid);

        back=(Button) findViewById(R.id.backToLoginBtn);
        submt=(Button) findViewById(R.id.forgotbtn);

        back.setOnClickListener(this);
        submt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.forgotbtn:
                check_validation();
                break;

            case R.id.backToLoginBtn:
                Intent login=new Intent(Forgotpasswd.this, Patient_Login.class);
                startActivity(login);
                break;
        }
    }

    private void check_validation() {
        String namestr=name.getText().toString();
        String emailstr=email.getText().toString();

        if((name.getText().toString().equals(""))||(name.getText().toString().length()==0 ))
        {
            showMessage("Invalid","please enter your registered name .");
        }
        else if((email.getText().toString().equals(""))||(email.getText().toString().length()==0))
        {
            showMessage("Invalid","please enter your registered email id .");
        }
        else
        {
            db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
            Cursor con=db.rawQuery("select * from MyPatiensInfo",null);
            int flag=0;
            while(con.moveToNext())
            {

                if((con.getString(0).equals(namestr)) && (con.getString(1).equals(emailstr)))
                {
                    flag=1;
                    Intent Intent = new Intent(this, Newpasswordoutside.class);
                    Intent.putExtra("email",emailstr);
                    Intent.putExtra("patientname",namestr);
                    startActivity(Intent);
                    break;
                }
            }
            if(flag==0)
            {
                showMessage("Failed","Please try again,wrong user name or email id");
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
