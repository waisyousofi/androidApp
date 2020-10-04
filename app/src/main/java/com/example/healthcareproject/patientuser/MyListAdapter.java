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

import com.example.healthcareproject.R;

import java.util.List;


public class MyListAdapter extends ArrayAdapter<Helo> {

    List<Helo> heroList;
    Context context;
    int resource;
    String  druserid;
    public MyListAdapter(Context context, int resource, List<Helo> heroList) {
        super(context, resource, heroList);
        this.context = context;
        this.resource = resource;
        this.heroList = heroList;
    }

    @Override
    public View getView(final int position,final View convertView,ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        final View view = layoutInflater.inflate(resource, null, false);

        final Helo hero = heroList.get(position);

        ImageView imageViewobj = view.findViewById(R.id.drimageView);
        TextView drspecialityobj = view.findViewById(R.id.textiddrspeiality);
        TextView drexperianceobj = view.findViewById(R.id.textiddrexperiance);
        TextView druseridobj = view.findViewById(R.id.textiddruserid);
        TextView reviewsobj = view.findViewById(R.id.drdrreviewsidid);
        TextView dradds=view.findViewById(R.id.draddsid);

        Button selecttbn = view.findViewById(R.id.drselectbtnid);


        imageViewobj.setImageDrawable(context.getResources().getDrawable(hero.getImage()));
        drspecialityobj.setText("Dr."+hero.getDrnamestr());
        drexperianceobj.setText(hero.getDrexperiancestr()+" years of experience");
        druseridobj.setText("Speciality: "+hero.getDrspecialitystr());
        dradds.setText("Address: "+hero.getDrcitystr());
        if(hero.getReviewnum()!=0 && hero.getReviewnum()!=1){
            reviewsobj.setText(hero.getReviewnum()+"reviews");}
        selecttbn.setHint(String.valueOf(hero.getDruserid()));
        druserid=String.valueOf(hero.getDruserid());
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
                Intent intent2=new Intent(context, Reviews.class);
                intent2.putExtra("druserid",hero.getDruserid());
                context.startActivity(intent2);
            }
        });
        return view;
    }
}