package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.healthcareproject.R;

import java.util.List;


public class SpecialityAdapter extends ArrayAdapter<specialityModel> {

    List<specialityModel> heroListp;
    Context context;
    int resource;
    String  druserid;
    public SpecialityAdapter(Context context, int resource, List<specialityModel> heroListp) {
        super(context, resource, heroListp);
        this.context = context;
        this.resource = resource;
        this.heroListp = heroListp;
    }

    @Override
    public View getView(final int position,final View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(resource, null, false);
        final specialityModel heros = heroListp.get(position);

        ImageView imageViewobj = view.findViewById(R.id.drimageView);
        TextView drspecialityobj = view.findViewById(R.id.textiddrspeiality);
        TextView drexperianceobj = view.findViewById(R.id.textiddrexperiance);
        TextView druseridobj = view.findViewById(R.id.textiddruserid);
        TextView reviewsobj = view.findViewById(R.id.drdrreviewsidid);
        TextView dradds=view.findViewById(R.id.draddsid);
        Button selecttbn = view.findViewById(R.id.drselectbtnid);

        imageViewobj.setImageDrawable(context.getResources().getDrawable(heros.getImage()));
        drspecialityobj.setText("Dr."+heros.getDrnamestr());
        drexperianceobj.setText(heros.getDrexperiancestr()+" years of experience");
        druseridobj.setText("Speciality: "+heros.getDrspecialitystr());
        druseridobj.setTypeface(Typeface.DEFAULT_BOLD);
        dradds.setText("Address: "+heros.getDrcitystr());
        if(heros.getRevnum2()!=0 && heros.getRevnum2()!=1){
            reviewsobj.setText(heros.getRevnum2()+"reviews");}
        selecttbn.setHint(String.valueOf(heros.getDruserid()));
        druserid=String.valueOf(heros.getDruserid());
        selecttbn.setOnClickListener(new View.OnClickListener()
        {
        @Override
                public void onClick(View view)
                {
                    Intent intent=new Intent(context, Doctor_Details.class);
                    Button btn = (Button) view.findViewById(view.getId());
                    intent.putExtra("druserid",btn.getHint().toString());
                    context.startActivity(intent);
                }});
        reviewsobj.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View v) {
                Intent intent2=new Intent(context,Reviews.class);
                intent2.putExtra("druserid",heros.getDruserid());
                context.startActivity(intent2);
            }
        });
        return view;
    }
}