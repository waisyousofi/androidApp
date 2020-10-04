package com.example.healthcareproject.patientuser;

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

import com.example.healthcareproject.MainActivity;
import com.example.healthcareproject.R;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SignUp_Activity extends AppCompatActivity implements View.OnClickListener {

	View view;
	private Spinner countries;
	public EditText fullName, emailId, mobileNumber, location, password, confirmPassword;
	TextView login;
	Button signUpButton;
	CheckBox terms_conditions;
	SQLiteDatabase db;
	RadioButton Male, Female;
	RadioGroup radioGroup;
	String male="male",female="female";
	Drawable pterror= MainActivity.errorpic;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.signup_layout);
		countries=(Spinner)findViewById(R.id.codes);
		ArrayAdapter<String> adapter=new ArrayAdapter<>(this,R.layout.support_simple_spinner_dropdown_item, CountriesDate.Cname);
		countries.setAdapter(adapter);

		initViews();
		setListeners();
		//
		Male = (RadioButton) findViewById(R.id.radioM);
		Female = (RadioButton) findViewById(R.id.radioF);
		radioGroup = (RadioGroup) findViewById(R.id.radioGrp);

		radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(RadioGroup group, int checkedId) {

				if (Male.isChecked()) {
					male= "male";
				} else {
					female = "female";
				}
			}
		});

		db=openOrCreateDatabase("Healthapp",Context.MODE_PRIVATE, null);
		db.execSQL("CREATE TABLE IF NOT EXISTS MyPatiensInfo(name VARCHAR NOT NULL,emailid VARCHAR NOT NULL,phoneno VARCHAR NOT NULL,address VARCHAR NOT NULL,password VARCHAR NOT NULL,gender VARCHAR NOT NULL);");
	}

	public void initViews() {
		fullName = (EditText) findViewById(R.id.fullName);
		emailId = (EditText) findViewById(R.id.userEmailId);
		mobileNumber = (EditText) findViewById(R.id.mobileNumber);
		location = (EditText) findViewById(R.id.location);
		password = (EditText) findViewById(R.id.password);
		confirmPassword = (EditText) findViewById(R.id.confirmPassword);
		signUpButton = (Button) findViewById(R.id.signUpBtn);
		login = (TextView) findViewById(R.id.already_user);
		terms_conditions = (CheckBox) findViewById(R.id.terms_conditions);
	}

	public void setListeners() {
		signUpButton.setOnClickListener(this);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.signUpBtn:

			checkValidation();
			break;

		case R.id.already_user:

			Intent supintent=new Intent(this, Patient_Login.class);
			startActivity(supintent);
			break;
		}

	}

	public void checkValidation() {

		String getFullName = fullName.getText().toString().trim();
		String getEmailId = emailId.getText().toString().trim();
		String getMobileNumber = mobileNumber.getText().toString().trim();
		String getLocation = location.getText().toString().trim();
		String getPassword = password.getText().toString().trim();
		String getConfirmPassword = confirmPassword.getText().toString().trim();

		Pattern p = Pattern.compile(Utils.regEx);
		Matcher m = p.matcher(getEmailId);
		int status=passwordCheck(getPassword);
		if (getFullName.equals("") || getFullName.length() == 0 || getEmailId.equals("") || getEmailId.length() == 0
				|| getMobileNumber.equals("") || getMobileNumber.length() == 0
				|| getLocation.equals("") || getLocation.length() == 0
				|| getPassword.equals("") || getPassword.length() == 0
				|| getConfirmPassword.equals("")
				|| getConfirmPassword.length() == 0){
			showMessage("Failed","all the fields are needed");
		}
		else if (!m.find()) {
			emailId.setError("Invalid email-id format",pterror);
			emailId.requestFocus();
			return;
		}
		else if (!getConfirmPassword.equals(getPassword)) {
			password.setError("Matchless password.",pterror);
			emailId.requestFocus();
			return;
		}
		else if (!terms_conditions.isChecked()) {
			Toast.makeText(this, "Please select terms and conditions", Toast.LENGTH_SHORT).show();
			terms_conditions.requestFocus()	;
			return;
		}
		else if(!(getMobileNumber.length()==10))
		{
			mobileNumber.setError("enter only 10 digits",pterror);
			mobileNumber.requestFocus();
			return;
		}
		else if(status!=0)
		{
			password.setError("password must be at least 8 characters,include A-Z,a-z,0-9 and a special character(@,#,$,%,&,*).",pterror);
			password.requestFocus();
			return;
		}
		else if (Male.isChecked())
		{
			String code=CountriesDate.Ccode[countries.getSelectedItemPosition()];
			String mobileno=code+getMobileNumber;
			db.execSQL("INSERT INTO MyPatiensInfo VALUES('"+getFullName+"','"+getEmailId+"','"+mobileno+"','"+getLocation+"','"+getPassword+"','"+male+"');");
			Toast.makeText(getApplicationContext(),"Your have successfully registered",Toast.LENGTH_SHORT).show();
			clearText();
			Intent intent1=new Intent(SignUp_Activity.this,Patient_Login.class);
			startActivity(intent1);
		}
		else if(Female.isChecked())
		{
			String code=CountriesDate.Ccode[countries.getSelectedItemPosition()];
			String mobileno=code+getMobileNumber;
			db.execSQL("INSERT INTO MyPatiensInfo VALUES('"+getFullName+"','"+getEmailId+"','"+mobileno+"','"+getLocation+"','"+getPassword+"','"+female+"');");
			Toast.makeText(getApplicationContext(),"Your have successfully registered",Toast.LENGTH_SHORT).show();
			clearText();
			Intent intent1=new Intent(SignUp_Activity.this,Patient_Login.class);
			startActivity(intent1);
		}
		else
		{
			showMessage("nothing ","happened");
		}
	}
	private int passwordCheck(String passwordstr){
		int status=0;
		String pwd = passwordstr;
		char[] pwdarr = pwd.toCharArray();
		int lnt = pwdarr.length;
		System.out.println(lnt);
		if(lnt<8) {
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
	public void clearText()
	{
		fullName.setText("");
		emailId.setText("");
		location.setText("");
		mobileNumber.setText("");
		password.setText("");
		confirmPassword.setText("");
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