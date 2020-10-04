package com.example.healthcareproject.patientuser;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import com.example.healthcareproject.R;

import java.util.List;

class MyListAdapteruser extends ArrayAdapter<Helouser>
{
    private List<Helouser> heroListur;
    private Context context;
    private int resource;
    private SQLiteDatabase db;
    private SQLiteDatabase objup;
    private int druserid,tokenrowid;
    private String pttokenno;

    public MyListAdapteruser(Context context, int resource, List<Helouser> heroListur) {
        super(context, resource, heroListur);
        this.context = context;
        this.resource = resource;
        this.heroListur = heroListur;
    }

    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        final Helouser herouser = heroListur.get(position);

        ImageView imageView = view.findViewById(R.id.userhistimage);
        TextView drname = view.findViewById(R.id.userhistdrname);
        TextView details = view.findViewById(R.id.userhistdetails);
        TextView feedback = view.findViewById(R.id.histfeedback);
        Button canclebtn = view.findViewById(R.id.userhistcancelappt);

        imageView.setImageDrawable(context.getResources().getDrawable(herouser.getImage()));
        drname.setText("Dr."+herouser.getDrname());
        druserid=herouser.getDruserid();
        pttokenno=herouser.getPttokenno();
        tokenrowid=herouser.getTokenrowid();
        feedback.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent=new Intent(context, Feedback.class);
            intent.putExtra("druserid",herouser.getDruserid());
            context.startActivity(intent);
        }
        });
        details.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(context, HistoryInfo.class);
                intent.putExtra("druserid",druserid);
                intent.putExtra("pttokenno",pttokenno);
                intent.putExtra("vday",herouser.getVisitingday());
                context.startActivity(intent);
            }
        });

        canclebtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Cancelfunc(position);
            }});
        return view;
    }
    private void Cancelfunc(final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to cancel this?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
                public void onClick(DialogInterface dialogInterface, int i)
            {
                db = context.openOrCreateDatabase("Healthapp",Context.MODE_PRIVATE,null);
                objup = context.openOrCreateDatabase("Healthapp",Context.MODE_PRIVATE,null);
                Cursor con = objup.rawQuery("UPDATE DrtokenInfo SET tokennumber='"+pttokenno+"' WHERE rowid="+tokenrowid+"",null);
                while(con.moveToNext()){}
                db.execSQL("DELETE FROM PatientsQueueInfo WHERE druserid="+druserid+" and ptuserid="+ Home_Activity.ptid+"");
                Toast.makeText(context,"Appointment canceled",Toast.LENGTH_SHORT).show();
                heroListur.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
            }
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}