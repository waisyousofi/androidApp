package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.healthcareproject.R;

import java.util.List;

class MyListAdapterall extends ArrayAdapter<Heloall> {

    List<Heloall> heroListall;
    Context context;
    int resource;
    String dremlid2,visitadds2,drspeciality2,pntemlid2,pntname2,pntcontactno2,pntaddress2,gender2;

    public void transfer(String dremlid,String visitadds,String drspeciality,String pntemlid,String pntname,String pntcontactno,String pntaddress,String gender)
    {
        dremlid2=dremlid;
        visitadds2=visitadds;
        drspeciality2=drspeciality;
        pntemlid2=pntemlid;
        pntname2=pntname;
        pntcontactno2=pntcontactno;
        pntaddress2=pntaddress;
        gender2=gender;
    }

    public MyListAdapterall(Context context, int resource, List<Heloall> heroListall) {
        super(context, resource, heroListall);
        this.context = context;
        this.resource = resource;
        this.heroListall = heroListall;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent)
    {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView4);

        TextView Drname = view.findViewById(R.id.Drname);
        TextView Drspeciality = view.findViewById(R.id.Drspeciality);
        TextView Drexp = view.findViewById(R.id.Drexp);
        TextView Dradds = view.findViewById(R.id.Dradds);

        Button selectbtn = view.findViewById(R.id.Doctorsbtn);
        Heloall heroall= heroListall.get(position);

        imageView.setImageDrawable(context.getResources().getDrawable(heroall.getImage()));

        Drname.setText("Name :Dr."+heroall.getName());
        Drspeciality.setText("Speciality :"+heroall.getDrspeciality());
        Drexp.setText("Experience :"+heroall.getDrexperiance());
        Dradds.setText("Address :"+heroall.getDrlocation());

        selectbtn.setHint(heroall.getEmail());
        final String draddresstopass=heroall.getDrlocation();
        final String drspecialitytopass=heroall.getDrspeciality();


        selectbtn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(context, Doctor_Details.class);
                Button btn = (Button) view.findViewById(view.getId());
                intent.putExtra("emailid2",btn.getHint().toString());
                intent.putExtra("draddrs",draddresstopass);
                intent.putExtra("spty",drspecialitytopass);
                intent.putExtra("email",pntemlid2);
                intent.putExtra("patientname",pntname2);
                intent.putExtra("contactno",pntcontactno2);
                intent.putExtra("pntaddress",pntaddress2);
                intent.putExtra("gender",gender2);

                context.startActivity(intent);
            }});
        return view;
    }
}