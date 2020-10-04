package com.example.healthcareproject.patientuser;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.drawerlayout.widget.DrawerLayout;

import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.TextSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;
import com.example.healthcareproject.R;
import com.google.android.material.navigation.NavigationView;

import java.util.HashMap;

public class Home_Activity extends AppCompatActivity implements View.OnClickListener,
        BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    SliderLayout sliderLayout;
    TextView user_name;
    HashMap<String,String> Hash_file_maps ;
    Button appointment,doctorsbtn,hospitals,nutration;
    private DrawerLayout dl;
    private ActionBarDrawerToggle t;
    private NavigationView nv;
    SQLiteDatabase db;
    static String ptname,ptemail,ptcity,ptpassword,ptgender,ptmobno;
    static int ptid;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_activity);
        String ptidstr=getIntent().getStringExtra("ptid");
        ptid=Integer.parseInt(ptidstr);

        db=openOrCreateDatabase("Healthapp", Context.MODE_PRIVATE, null);
        Cursor con=db.rawQuery("select * from MyPatiensInfo where rowid="+ptid+"",null);
        while(con.moveToNext()){
            ptname=con.getString(0);
            ptemail=con.getString(1);
            ptmobno=con.getString(2);
            ptcity=con.getString(3);
            ptpassword=con.getString(4);
            ptgender=con.getString(5);
        }
        con.close();
        dl = (DrawerLayout) findViewById(R.id.homelayout);
        t = new ActionBarDrawerToggle(this, dl, R.string.open, R.string.close);
        dl.addDrawerListener(t);
        t.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        nv = (NavigationView) findViewById(R.id.nv);
        nv.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.account:
                        Intent intent1 = new Intent(Home_Activity.this, Myaccount.class);
                        startActivity(intent1);
                        break;
                    case R.id.settings:
                        Intent intent2 = new Intent(Home_Activity.this, Userhistory.class);
                        startActivity(intent2);
                        break;
                    case R.id.mycart:
                        Intent intent3 = new Intent(Home_Activity.this, Setting.class);
                        startActivity(intent3);
                        break;
                    default:
                        return true;
                }
                return true;
            }
        });
        doctorSliders();
        register();
    }

    private void doctorSliders() {

        Hash_file_maps = new HashMap<String, String>();
        sliderLayout = (SliderLayout)findViewById(R.id.slider);
        String[] femaleimgurl={

                "https://images.pexels.com/photos/3861450/pexels-photo-3861450.jpeg?cs=srgb&dl=woman-in-white-lab-gown-holding-a-black-and-gray-camera-3861450.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845998/pexels-photo-3845998.jpeg?cs=srgb&dl=woman-in-white-dress-shirt-using-white-microscope-3845998.jpg&fm=jpg",
                "https://images.pexels.com/photos/3846009/pexels-photo-3846009.jpeg?cs=srgb&dl=eye-clinic-3846009.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845766/pexels-photo-3845766.jpeg?cs=srgb&dl=dental-check-up-3845766.jpg&fm=jpg",
                "https://images.pexels.com/photos/3861450/pexels-photo-3861450.jpeg?cs=srgb&dl=woman-in-white-lab-gown-holding-a-black-and-gray-camera-3861450.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845766/pexels-photo-3845766.jpeg?cs=srgb&dl=dental-check-up-3845766.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845998/pexels-photo-3845998.jpeg?cs=srgb&dl=woman-in-white-dress-shirt-using-white-microscope-3845998.jpg&fm=jpg",
                "https://images.pexels.com/photos/3846009/pexels-photo-3846009.jpeg?cs=srgb&dl=eye-clinic-3846009.jpg&fm=jpg",
                "https://images.pexels.com/photos/3861450/pexels-photo-3861450.jpeg?cs=srgb&dl=woman-in-white-lab-gown-holding-a-black-and-gray-camera-3861450.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845998/pexels-photo-3845998.jpeg?cs=srgb&dl=woman-in-white-dress-shirt-using-white-microscope-3845998.jpg&fm=jpg",
                "https://images.pexels.com/photos/3846009/pexels-photo-3846009.jpeg?cs=srgb&dl=eye-clinic-3846009.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845766/pexels-photo-3845766.jpeg?cs=srgb&dl=dental-check-up-3845766.jpg&fm=jpg",
                "https://images.pexels.com/photos/3861450/pexels-photo-3861450.jpeg?cs=srgb&dl=woman-in-white-lab-gown-holding-a-black-and-gray-camera-3861450.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845766/pexels-photo-3845766.jpeg?cs=srgb&dl=dental-check-up-3845766.jpg&fm=jpg",
                "https://images.pexels.com/photos/3845998/pexels-photo-3845998.jpeg?cs=srgb&dl=woman-in-white-dress-shirt-using-white-microscope-3845998.jpg&fm=jpg",
                "https://images.pexels.com/photos/3846009/pexels-photo-3846009.jpeg?cs=srgb&dl=eye-clinic-3846009.jpg&fm=jpg"
        };
        String[] maleimgurl={
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500",
                "https://images.pexels.com/photos/2182979/pexels-photo-2182979.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500"
        };
        int findex=0;
        int mindex=0;
        Cursor con2=db.rawQuery("select drname,drspeciality,drgender from MyDoctorInfos",null);
        while (con2.moveToNext()){
            String docname,docspeciality,docgender;
            docname=con2.getString(0);
            docspeciality=con2.getString(1);
            docgender=con2.getString(2);
            if(docgender.equals("female")){
                Hash_file_maps.put(docname+","+docspeciality,femaleimgurl[findex]);
                findex++;
            }else{
                Hash_file_maps.put(docname+","+docspeciality,maleimgurl[mindex]);
                mindex++;
            }
        }
        con2.close();
        for(String name : Hash_file_maps.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            textSliderView
                    .description(name)
                    .image(Hash_file_maps.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(new BaseSliderView.OnSliderClickListener() {
                        @Override
                        public void onSliderClick(BaseSliderView slider) {
                            Toast.makeText(Home_Activity.this,"Please find and select :"+slider.getDescription()+" from here.", Toast.LENGTH_LONG).show();
                            Intent intentb = new Intent(Home_Activity.this, Bookingprocess.class);
                            startActivity(intentb);
                        }
                    });
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle()
                    .putString("extra",name);
            sliderLayout.addSlider(textSliderView);
        }
        sliderLayout.setPresetTransformer(SliderLayout.Transformer.Accordion);
        sliderLayout.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderLayout.setCustomAnimation(new DescriptionAnimation());
        sliderLayout.setDuration(3000);
        sliderLayout.addOnPageChangeListener(this);
    }
    private void register()
    {
        appointment=(Button)findViewById(R.id.booking);
        doctorsbtn=(Button)findViewById(R.id.showdoctors);
        hospitals=(Button)findViewById(R.id.showhospital);
        nutration=(Button)findViewById(R.id.nutration);

        appointment.setOnClickListener(this);
        doctorsbtn.setOnClickListener(this);
        hospitals.setOnClickListener(this);
        nutration.setOnClickListener(this);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(t.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onStop() {
        sliderLayout.stopAutoCycle();
        super.onStop();
    }
    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}
    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }
    @Override
    public void onPageScrollStateChanged(int state) {}
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {

            case R.id.booking:
                Intent intent1 = new Intent(Home_Activity.this, Bookingprocess.class);
                startActivity(intent1);
                break;
            case R.id.showdoctors:
                Intent intent2 = new Intent(Home_Activity.this, All_Doctors.class);
                startActivity(intent2);
                break;
            case R.id.showhospital:
                Intent intent3 = new Intent(Home_Activity.this, Hospitals.class);
                startActivity(intent3);
                break;
            case R.id.nutration:
                Intent intent4 = new Intent(Home_Activity.this, Nutration.class);
                startActivity(intent4);
                break;
            default:
                return;
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