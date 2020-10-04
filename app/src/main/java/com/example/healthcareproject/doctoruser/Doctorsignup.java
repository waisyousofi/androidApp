package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.patientuser.CountriesDate;
import com.example.healthcareproject.patientuser.CountriesDate2;
import com.example.healthcareproject.MainActivity;
import com.example.healthcareproject.R;
import com.example.healthcareproject.patientuser.Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Doctorsignup extends AppCompatActivity implements View.OnClickListener {

    View view;
    public EditText fullName, emailId, mobileNumber, location, password, confirmPassword,drexperiance,drspecailityobj,clinicadds;
    TextView login;
    Button signUpButton;
    CheckBox terms_conditions;
    SQLiteDatabase db;
    Drawable drerror= MainActivity.errorpic;
    RadioButton rMale, rFemale;
    Spinner countries;
    RadioGroup radioGroup;
    private final String drm="male";
    private final String drf="female";
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.doctorsignup);
        initViews();
        setListeners();

        //
        rMale = (RadioButton) findViewById(R.id.radioMdr);
        rFemale = (RadioButton) findViewById(R.id.radioFdr);
        radioGroup = (RadioGroup) findViewById(R.id.radioGrpdr);

        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {

                if (rMale.isChecked()) {

                } else if (rFemale.isChecked()){
                }
            }
        });
        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        db.execSQL("CREATE TABLE IF NOT EXISTS MyDoctorInfos(drname VARCHAR NOT NULL,dremailid VARCHAR NOT NULL,drspeciality VARCHAR NOT NULL,drexperianceyrs VARCHAR NOT NULL,drphoneno VARCHAR NOT NULL,drcityadds VARCHAR NOT NULL,drpassword VARCHAR NOT NULL,drgender VARCHAR NOT NULL);");
    }
    public void initViews() {
        fullName = (EditText) findViewById(R.id.fullNamedtr);
        emailId = (EditText) findViewById(R.id.userEmailIddtr);
        drspecailityobj = (EditText) findViewById(R.id.specialitydtr);
        mobileNumber = (EditText) findViewById(R.id.mobileNumberdtr);
        location = (EditText) findViewById(R.id.locationdtr);
        password = (EditText) findViewById(R.id.passworddtr);
        drexperiance = (EditText) findViewById(R.id.experianceno);
        confirmPassword = (EditText) findViewById(R.id.confirmPassworddtr);
        signUpButton = (Button) findViewById(R.id.signUpBtndtr);
        login = (TextView) findViewById(R.id.already_userdtr);
        terms_conditions = (CheckBox) findViewById(R.id.terms_conditionsdtr);
        clinicadds=findViewById(R.id.drclinicadds);
        countries=(Spinner)findViewById(R.id.drcodes);
        ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, CountriesDate.Cname);
        countries.setAdapter(adapter);
    }
    public void setListeners() {
        signUpButton.setOnClickListener(this);
        login.setOnClickListener(this);
    }
    @Override
    public void onClick(View v)
    {
        switch (v.getId()) {
            case R.id.signUpBtndtr:
                // Call checkValidation method
                checkValidation();
                break;

            case R.id.already_userdtr:

                // Replace login fragment
                Intent supintent=new Intent(this, DoctorLogin.class);
                startActivity(supintent);
                break;
        }

    }
    private String getItsText(EditText editTextobj){
        String textstr=editTextobj.getText().toString().toLowerCase().trim();
        return textstr;
    }
    public void checkValidation() {
        String drnamestr = getItsText(fullName);String dremailstr = emailId.getText().toString();String drspecialitystr=getItsText(drspecailityobj);
        String drexperiacestr=getItsText(drexperiance);String drmobilenumberstr =getItsText(mobileNumber);
        String drlocationstr =getItsText(location);String drpasswordstr =password.getText().toString();String drconfirmpasswordstr = confirmPassword.getText().toString();
        String drclinicadds=clinicadds.getText().toString().trim();
        int pwdstatus=passwordCheck(drpasswordstr);
        Pattern p = Pattern.compile(Utils.regEx);
        Matcher m = p.matcher(dremailstr);
        if (drnamestr.equals("") || drnamestr.length() == 0 || dremailstr.equals("") || dremailstr.length() == 0
                || drmobilenumberstr.equals("") || drmobilenumberstr.length()== 0
                || drlocationstr.equals("") || drlocationstr.length() == 0
                || drpasswordstr.equals("") || drpasswordstr.length() == 0
                || drconfirmpasswordstr.equals("")
                || drconfirmpasswordstr.length() == 0||drclinicadds.equals("")){
            showMessage("Failed","All the fields are required!");
            return;
        }
		else if (!m.find()) {
            emailId.setError("enter a valid email-id",drerror);
            emailId.requestFocus();
            return;
        }
        else if (!drconfirmpasswordstr.equals(drpasswordstr)) {
            password.setError("matchless passwords",drerror);
            password.requestFocus();
            return;
        }
        else if (!terms_conditions.isChecked()){
            Toast.makeText(this, "Please select terms and conditions", Toast.LENGTH_SHORT).show();
            terms_conditions.requestFocus();
            return;
        }
        else if(!(drmobilenumberstr.length()==10))
        {
            mobileNumber.setError("enter only 10 digits",drerror);
            mobileNumber.requestFocus();
            return;
        }
        else if(pwdstatus!=0)
        {
            password.setError("password must be at least 8 characters,include A-Z,a-z,0-9 and a special character(@,#,$,%,&,*).",drerror);
            password.requestFocus();
            return;
        }else if(drclinicadds.equals("") || drclinicadds.length()==0){

        }
        else if (rMale.isChecked())
        {
            String code= CountriesDate2.Ccode[countries.getSelectedItemPosition()];
            String mobileno=code+drmobilenumberstr;
            db.execSQL("INSERT INTO MyDoctorInfos VALUES('"+drnamestr+"','"+dremailstr+"','"+drspecialitystr+"','"+drexperiacestr+"','"+mobileno+"','"+ drlocationstr+"','"+drpasswordstr+"','"+drm+"');");
            Toast.makeText(getApplicationContext(),"Your have successfully registered.",Toast.LENGTH_SHORT).show();
            clearText();
            Intent intent1=new Intent(Doctorsignup.this,DoctorLogin.class);
            intent1.putExtra("drclinicadds",drclinicadds);
            startActivity(intent1);
        }
        else if(rFemale.isChecked())
        {
            String code=CountriesDate.Ccode[countries.getSelectedItemPosition()];
            String mobileno=code+drmobilenumberstr;
            db.execSQL("INSERT INTO MyDoctorInfos VALUES('"+drnamestr+"','"+dremailstr+"','"+drspecialitystr+"','"+drexperiacestr+"','"+mobileno+"','"+ drlocationstr+"','"+drpasswordstr+"','"+drf+"');");
            Toast.makeText(getApplicationContext(),"Your have successfully registered.",Toast.LENGTH_SHORT).show();
            clearText();
            Intent intent1=new Intent(Doctorsignup.this,DoctorLogin.class);
            intent1.putExtra("drclinicadds",drclinicadds);
            startActivity(intent1);
        }
        else
        {
            showMessage("nothing ","happened");
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
    public void clearText(){
        fullName.setText("");
        emailId.setText("");
        location.setText("");
        mobileNumber.setText("");
        password.setText("");
        confirmPassword.setText("");
        drspecailityobj.setText("");
        drexperiance.setText("");
    }
    public void showMessage(String title,String message){
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}