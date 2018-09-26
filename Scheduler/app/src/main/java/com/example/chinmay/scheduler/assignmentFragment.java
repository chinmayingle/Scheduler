package com.example.chinmay.scheduler;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;


/**
 * Created by chinmay on 23/9/17.
 */

public class assignmentFragment extends Fragment {
    View myview;
    ListView l;
    MyDbHandler dbhandler;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        //Intent i= (inte)

        myview=inflater.inflate(R.layout.assignment_layout,container,false);
        Log.i("chinmay.scheduler","in threee");
        dbhandler = new MyDbHandler(getContext(),null,null,5);
        //String [] list= new String[]{"leo","cr","nyc"};
        //String food[]={"pizza","burger","donut","pasta"};
        String [] list=dbhandler.retrieveListview(2);
        ListAdapter ll=new row(getContext(),list);
        //ListAdapter dd=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,food);
        //ListAdapter criadap=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,food);
        l = (ListView) myview.findViewById(R.id.listid);

        //ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1,list);
        l.setAdapter(ll);


        FloatingActionButton myFab = (FloatingActionButton) myview.findViewById(R.id.add_task);
        myFab.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                Log.i("chinmay.scheduler","in onclick");
                Intent i = new Intent(getContext(),AddTaskActivity.class);
                startActivity(i);

            }
        });

        return myview;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String [] list=dbhandler.retrieveListview(2);
        ListAdapter ll=new row(getContext(),list);
        l = (ListView) myview.findViewById(R.id.listid);
        l.setAdapter(ll);
    }
}
