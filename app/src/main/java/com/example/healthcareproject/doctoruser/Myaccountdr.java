package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Myaccountdr extends AppCompatActivity implements View.OnClickListener{

    private Button logout;
    private TextView name, emailadds, mobno, address,drspeciality,genderdr;
    private SQLiteDatabase db;
    private ImageView drimage;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctroprofile);
        register();
    }

    private void register()
    {
        logout=(Button)findViewById(R.id.drlogout);
        name= (TextView)findViewById(R.id.drnametxt);
        emailadds= (TextView)findViewById(R.id.dremailtxtdr);
        mobno= (TextView)findViewById(R.id.drphonetextdr);
        address=(TextView)findViewById(R.id.draddressdr);
        drspeciality=(TextView)findViewById(R.id.drspecialitytxt);
        genderdr=(TextView)findViewById(R.id.drgenderdr);
        logout.setOnClickListener(this);
        drimage=findViewById(R.id.dricon);
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        Cursor con=db.rawQuery("Select * from MyDoctorInfos where rowid="+Home_Activitydtr.druserid+"",null);
        while (con.moveToNext())
        {
            name.setText(con.getString(0));
            emailadds.setText("EmailId: "+con.getString(1));
            drspeciality.setText("Your speciality : "+con.getString(2));
            mobno.setText("Mobile No: "+con.getString(4));
            address.setText("Address: "+con.getString(5));
            genderdr.setText("Gender: "+con.getString(7));
            if(con.getString(7).equals("female")){
                drimage.setImageDrawable(getResources().getDrawable(R.drawable.femdr));

            }else{
                drimage.setImageDrawable(getResources().getDrawable(R.drawable.dr));
            }
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.drlogout:
                Intent intent=new Intent(Myaccountdr.this, DoctorLogin.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK|Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
                break;
            default:
                return;
        }
    }
}