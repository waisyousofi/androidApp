package com.example.healthcareproject.patientuser;

import android.app.AlertDialog;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Newpasswordoutside extends AppCompatActivity implements View.OnClickListener {

    Button submit;
    EditText newnameobj;
    SQLiteDatabase db;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.newpassword);
        register();
        db = openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
    }
    private void register() {
        submit = (Button) findViewById(R.id.newpasswdbtn);
        newnameobj = (EditText) findViewById(R.id.newpasswordtxt);
        submit.setOnClickListener(this);
    }

    @Override
    public void onClick(View v)
    {
        String useremail = getIntent().getStringExtra("email");
        String username = getIntent().getStringExtra("patientname");

        String namestr = newnameobj.getText().toString();

        char[] newpasswd=namestr.toCharArray();
        if (namestr.equals("") || namestr.length() == 0) {
            showMessage("Failed", "Please enter your new password");
        }
        else if(namestr.length()<8)
        {
            showMessage("Failed","Password must be at least 8 characters.");
        }
        else if(!(newpasswd[0]>='A' && newpasswd[0]<='Z'))
        {
            showMessage("Failed","First character must be in uppercase.");
        }
        else if(!(newpasswd[1]>='a' && newpasswd[1]<='z'))
        {
            showMessage("Failed","Second character must be in lowercase");
        }
        else
        {
            Cursor con = db.rawQuery("update MyPatiensInfo set password='"+namestr+"' where emailid='"+useremail+"' and name='"+username+"'", null);
            while (con.moveToNext()) {
            }
            showMessage("info", "password changed successfuly");
            newnameobj.setText("");
        }
    }
    public void showMessage(String title, String message) {
        android.app.AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}

