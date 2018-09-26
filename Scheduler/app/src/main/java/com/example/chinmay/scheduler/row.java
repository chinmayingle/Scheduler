package com.example.chinmay.scheduler;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by chinmay on 1/10/17.
 */

public class row extends ArrayAdapter<String>    {
    public row(@NonNull Context context,  String[] list) {
        super(context,R.layout.custom_row ,list);
       // MyDbHandler dbHandler;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater crinflat=LayoutInflater.from(getContext());
        View custom=crinflat.inflate(R.layout.custom_row,parent,false);

       // dbHandler= new MyDbHandler(this,null,null, 5);
        String single_entity=getItem(position);
        TextView tv=(TextView)custom.findViewById(R.id.row_view);
        Button b = (Button)custom.findViewById(R.id.buttonrow);


     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View view) {

         }
     });


        tv.setText(single_entity);
        return custom;
    }
}
