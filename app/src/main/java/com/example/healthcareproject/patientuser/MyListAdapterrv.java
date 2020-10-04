package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.healthcareproject.R;

import java.util.List;

class MyListAdapterrv extends ArrayAdapter<Helorv> {

    List<Helorv> heroListrv;
    Context context;
    int resource;

    public MyListAdapterrv(Context context, int resource, List<Helorv> heroListrv) {
        super(context, resource, heroListrv);
        this.context = context;
        this.resource = resource;
        this.heroListrv = heroListrv;
    }
    @NonNull
    @Override
    public View getView(final int position, @Nullable final View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(resource, null, false);
        TextView name = view.findViewById(R.id.name);
        TextView numberof_starts = view.findViewById(R.id.starts);
        TextView comment = view.findViewById(R.id.comment);

        Helorv herorv = heroListrv.get(position);
        name.setText(herorv.getName());
        numberof_starts.setText(herorv.getStarts());
        comment.setText(herorv.getComent());
        return view;
    }
}