package com.example.healthcareproject.patientuser;

import android.os.Bundle;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.healthcareproject.R;

public class Readingactivity extends AppCompatActivity {
    TextView textView;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.reading_activity2);
        textView=(TextView)findViewById(R.id.information);
        String information=getIntent().getStringExtra("information");
        textView.setText(information);

    }
}
