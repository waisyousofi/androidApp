package com.example.healthcareproject.patientuser;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.healthcareproject.R;

import java.util.ArrayList;
import java.util.List;

public class MyListAdapter22 extends BaseAdapter{


    private Context context;
    private List<MyModel> myModelList=null;
    private ArrayList<MyModel> myModelArrayList;
    private LayoutInflater inflater;

    public MyListAdapter22(Context context, List<MyModel> myModelList) {
        this.context = context;
        this.myModelList = myModelList;
        inflater=LayoutInflater.from(context);
        myModelArrayList=new ArrayList<>();
        myModelArrayList.addAll(myModelList);
    }

    @Override
    public int getCount() {
        return myModelList.size();
    }

    @Override
    public MyModel getItem(int position) {
        return myModelArrayList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    public class  Myviewholder{
        TextView textitem;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final Myviewholder myviewholder;
        if (convertView==null){
            myviewholder=new Myviewholder();
            convertView=inflater.inflate(R.layout.searchviewitemex2,null);
            myviewholder.textitem=convertView.findViewById(R.id.searchtextitemex22);
            convertView.setTag(myviewholder);
        }else {
            myviewholder=(Myviewholder)convertView.getTag();
        }
        myviewholder.textitem.setText(myModelList.get(position).getAnimalname());
        return convertView;
    }

    public void myfilter(String searchstr){
        searchstr=searchstr.trim().toLowerCase();//default language Locale.getDefault();
        myModelList.clear();
        if(searchstr.length()==0){
            myModelList.addAll(myModelArrayList);
        }else{
            for (MyModel model:myModelArrayList){
                if (model.getAnimalname().trim().toLowerCase().contains(searchstr)){
                    myModelList.add(model);
                }
            }
        }
        notifyDataSetChanged();
    }
}

