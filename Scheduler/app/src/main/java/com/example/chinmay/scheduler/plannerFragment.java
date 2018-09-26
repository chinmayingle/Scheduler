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

public class plannerFragment extends Fragment {
    View myview;
   MyDbHandler dbhandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.planner_layout,container,false);
        //String [] subjects={"maths","science","english"};
        dbhandler = new MyDbHandler(getContext(), null, null, 5);


        String [] subjects=dbhandler.retrieveListview(1);
        ListAdapter la=new row(getContext(),subjects);
        ListView listview=(ListView)myview.findViewById(R.id.subjectlist);
        listview.setAdapter(la);

        FloatingActionButton myFab = (FloatingActionButton) myview.findViewById(R.id.add_sub);
        myFab.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                Log.i("chinmay.scheduler","in onclick222222");
                Intent i = new Intent(getContext(),AddSubjectActivity.class);
                Log.i("chinmay.scheduler","in onclick333333333");
                startActivity(i);

            }
        });

        return myview;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String [] subjects=dbhandler.retrieveListview(1);
        ListAdapter la=new row(getContext(),subjects);
        ListView listview=(ListView)myview.findViewById(R.id.subjectlist);
        listview.setAdapter(la);
    }

}
