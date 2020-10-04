package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Feedback extends AppCompatActivity implements View.OnClickListener{
    private EditText feedbackobj;
    private Button ratebtnobj,sendbtnobj;
    private double ratevalue=0;
    private SQLiteDatabase db;
    private String username,comment,ratestr;
    private int druserid;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.feedback);
        register();
        druserid=getIntent().getIntExtra("druserid",0);
    }


    private void register() {
        feedbackobj = (EditText) findViewById(R.id.feedbackstr);
        ratebtnobj = (Button) findViewById(R.id.ratebtn);
        sendbtnobj = (Button) findViewById(R.id.sendbtn);
        ratebtnobj.setOnClickListener(this);
        sendbtnobj.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        int id=v.getId();
        switch (id)
        {
            case R.id.ratebtn:
                Rate_btn();
                break;
            case R.id.sendbtn:
                Dbpart();
                break;
        }
    }
    private void Dbpart() {
        comment = feedbackobj.getText().toString().trim();
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS allFeedback(pntname VARCHAR NOT NULL,comment VARCHAR NOT NULL,givenstar VARCHAR NOT NULL,druserid INTEGER NOT NULL);");
        if(comment.equals(""))
        {Toast.makeText(getApplicationContext(),"please enter your feedback",Toast.LENGTH_LONG).show();}
        else {
            String startsstr = String.valueOf(ratevalue);
            db.execSQL("INSERT INTO allFeedback VALUES('"+Home_Activity.ptname+"','"+comment+"','"+startsstr+"',"+druserid+");");
            Toast.makeText(getApplicationContext(), "feedback sent.", Toast.LENGTH_LONG).show();
            feedbackobj.setText("");
        }
    }

    private void Rate_btn() {

        try {
            AlertDialog.Builder builder = new AlertDialog.Builder(Feedback.this);
            View layout = null;
            LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            layout = inflater.inflate(R.layout.rating, null);
            final RatingBar ratingBar = (RatingBar) layout.findViewById(R.id.ratingBar);
            builder.setTitle("Rate Doctor's Services");
            builder.setMessage("Thank you for rating,it helps the community.");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int id) {
                     ratevalue= ratingBar.getRating();
                    ratestr=String.valueOf(ratevalue);
                    Toast.makeText(Feedback.this, "Given starts: "+ratestr, Toast.LENGTH_LONG).show();
                }
            });
            builder.setNegativeButton("No,Thanks", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.setCancelable(false);
            builder.setView(layout);
            builder.show();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
