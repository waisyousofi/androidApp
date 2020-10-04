package com.example.healthcareproject.doctoruser;

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

public class Forgotpasswddr extends AppCompatActivity implements View.OnClickListener{

    private EditText name,email;
    private Button submt,back;
    private SQLiteDatabase db;
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.forgotpassword_layoutdr);
        register();
    }
    public void register()
    {
        name=(EditText)findViewById(R.id.registered_namedr);
        email=(EditText)findViewById(R.id.registered_emailiddr);

        back=(Button) findViewById(R.id.backToLoginBtndr);
        submt=(Button) findViewById(R.id.forgotbtndr);

        back.setOnClickListener(this);
        submt.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.forgotbtndr:
                check_validation();
                break;

            case R.id.backToLoginBtndr:
                Intent login=new Intent(Forgotpasswddr.this, DoctorLogin.class);
                startActivity(login);
                finish();
                break;
        }
    }

    private void check_validation() {
        String drnamestr=name.getText().toString().toLowerCase().trim();
        String dremailstr=email.getText().toString().trim();

        if((drnamestr.equals(""))||(drnamestr.length()==0 ))
        {
            name.setError("please enter your registered user-name");
            name.requestFocus();
        }
        else if((dremailstr.equals(""))||(dremailstr.length()==0))
        {
            email.setError("please enter your registered email address");
        }
        else
        {
            db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
            Cursor con=db.rawQuery("select drname,dremailid from MyDoctorInfos where drname='"+drnamestr+"' and dremailid='"+dremailstr+"'",null);
            int flag=0;
            while(con.moveToNext())
            {
                if((con.getString(0).equals(drnamestr)) && (con.getString(1).equals(dremailstr)))
                {
                    flag=1;
                    Intent Intent = new Intent(this, Newpasswordoutsidedr.class);
                    Intent.putExtra("email",dremailstr);
                    Intent.putExtra("doctor_name",drnamestr);
                    startActivity(Intent);
                    finish();
                    break;
                }
            }
            if(flag==0)
            {showMessage("Failed","Please try again,wrong user name or email id");}
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
