package com.example.healthcareproject.doctoruser;

import android.content.Context;
import android.content.DialogInterface;
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
import androidx.appcompat.app.AlertDialog;

import com.example.healthcareproject.R;

import java.util.List;


public class MyListAdapterdrhome extends ArrayAdapter<Helodrhome> {

    List<Helodrhome> heroListdr;
    Context context;
    int resource;
    int viewposition;
    private SQLiteDatabase db;
    private SQLiteDatabase objup;
    String pttokennostr;
    String getEmailId3, patientname3, contactno3, pntaddress3, gender3;
    int ptuserid,tokenrowidnum;


    public MyListAdapterdrhome(Context context, int resource, List<Helodrhome> heroListdr) {
        super(context, resource, heroListdr);
        this.context = context;
        this.resource = resource;
        this.heroListdr = heroListdr;
    }


    @NonNull
    @Override
    public View getView(final int position,View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        ImageView imageView = view.findViewById(R.id.imageView2);
        TextView pname = view.findViewById(R.id.txtdrname);
        TextView pcontact = view.findViewById(R.id.contactno);
        TextView pgender = view.findViewById(R.id.gender);
        TextView pemaild = view.findViewById(R.id.emailid);
        TextView ptokenno = view.findViewById(R.id.tokennotxt);
        TextView pvisitday = view.findViewById(R.id.visitday);
        TextView pvisitln = view.findViewById(R.id.visitlocn);
        Button metbtn = view.findViewById(R.id.met);

        Helodrhome herodr = heroListdr.get(position);
        imageView.setImageDrawable(context.getResources().getDrawable(herodr.getImage()));
        pname.setText("Patient name :"+herodr.getName());
        pcontact.setText("Mob-no:"+herodr.getPcontactno());
        pgender.setText("Gender :"+herodr.getPgender());
        pemaild.setText("Email-id :"+herodr.getEmail());
        ptokenno.setText("Token number :"+herodr.getTokenno());
        pvisitday.setText("Visiting Day :"+herodr.getVisitday());
        pvisitln.setText("Address:"+herodr.getVisitingadd());

        patientname3=herodr.getName();
        contactno3=herodr.getPcontactno();
        getEmailId3=herodr.getEmail();
        ptuserid=herodr.getPtuserid();
        pttokennostr=herodr.getTokenno();
        tokenrowidnum=herodr.getTokenrowid();
        metbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Delete_func(position);
            }
        });

        return view;
    }

    private void Delete_func(final int position)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle("Are you sure you want to remove this patient from the queue?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialogInterface, int i)
            {
                db = context.openOrCreateDatabase("Healthapp",Context.MODE_PRIVATE,null);
                db.execSQL("DELETE from PatientsQueueInfo where ptuserid="+ptuserid+" and druserid="+ Home_Activitydtr.druserid+"");
                objup = context.openOrCreateDatabase("Healthapp",Context.MODE_PRIVATE,null);
                Cursor con = objup.rawQuery("UPDATE DrtokenInfo SET tokennumber='"+pttokennostr+"' WHERE rowid="+tokenrowidnum+"",null);
                while(con.moveToNext()){Toast.makeText(context,"updated",Toast.LENGTH_LONG).show();}
                Toast.makeText(context,"removed from the queue",Toast.LENGTH_LONG).show();
                heroListdr.remove(position);
                notifyDataSetChanged();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {}
        });
        AlertDialog alertDialog = builder.create();
        alertDialog.show();
    }
}