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

import com.bumptech.glide.Glide;
import com.example.healthcareproject.R;

import java.util.List;

class MyListAdapternuts  extends ArrayAdapter<Helonuts> {

        List<Helonuts> heroListnuts;
        Context context;
        int resource;

    public MyListAdapternuts(Context context, int resource, List<Helonuts> heroListnuts) {
        super(context, resource, heroListnuts);
        this.context = context;
        this.resource = resource;
        this.heroListnuts = heroListnuts;
    }

    @Override
    public View getView(final int position, View convertView,ViewGroup parent) {
    LayoutInflater layoutInflater = LayoutInflater.from(context);
    View view = layoutInflater.inflate(resource, null, false);

        ImageView imageView = view.findViewById(R.id.imageView6);
        TextView fruitname = view.findViewById(R.id.fruit);
        Button readabout=(Button)view.findViewById(R.id.readmore);


        final Helonuts heronuts = heroListnuts.get(position);
        Glide.with(context).load(heronuts.getImage()).into(imageView);
        fruitname.setText(heronuts.getName());
        readabout.setHint(heronuts.getBenefits());
        readabout.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {

                Intent intent=new Intent(context, Readingactivity.class);
                Button btn = (Button) view.findViewById(view.getId());

                intent.putExtra("information",btn.getHint().toString());
                context.startActivity(intent);
            }});
        return view;
        }
}