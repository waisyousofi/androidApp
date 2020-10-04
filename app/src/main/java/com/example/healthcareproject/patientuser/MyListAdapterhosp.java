package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.healthcareproject.R;

import java.util.List;


public class MyListAdapterhosp extends ArrayAdapter<Helohospital> {

    List<Helohospital> heroListhosp;
    Context context;
    int resource;

    public MyListAdapterhosp(Context context, int resource, List<Helohospital> heroListhosp) {
        super(context, resource, heroListhosp);
        this.context = context;
        this.resource = resource;
        this.heroListhosp = heroListhosp;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        TextView hospadds = view.findViewById(R.id.hospadds);
        TextView hosptype = view.findViewById(R.id.hosptype);
        TextView hospmob = view.findViewById(R.id.hosmobno);
        TextView hosemail=view.findViewById(R.id.hosemailid);
        TextView hosrevw=view.findViewById(R.id.reviewid);


        final Helohospital herohosp = heroListhosp.get(position);
        hospadds.setText("Address :"+herohosp.getHospadds());
        hosptype.setText("Specialities :"+herohosp.getHosptype());
        hospmob.setText("Contact us :"+herohosp.getHosmobno());
        hosemail.setText(herohosp.getEmailid());
        if(herohosp.getRevnum()!=0 && herohosp.getRevnum()!=1){
            hosrevw.setText(herohosp.getRevnum()+" reviews");}


        hosrevw.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent2=new Intent(context, Reviews.class);
                intent2.putExtra("druserid",herohosp.getDrid());
                context.startActivity(intent2);
            }
        });

        return view;
    }
}