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
 * Created by shambhavi on 28/9/17.
 */

public class examFragment extends Fragment {
    View myview;
    MyDbHandler dbHandler;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.exam,container,false);
//        String [] exams={"maths","science","english"};
        Log.i("aaaa","in exxams");

        dbHandler = new MyDbHandler(getContext(),null,null,5);

        String [] exams=dbHandler.retrieveListview(3);
        ListAdapter la=new row(getContext(),exams);
        ListView listview=(ListView)myview.findViewById(R.id.my_eaxm_list);
        listview.setAdapter(la);

        FloatingActionButton myFab = (FloatingActionButton) myview.findViewById(R.id.exam_add);
        myFab.setOnClickListener(new View.OnClickListener() {


            public void onClick(View v) {
                Log.i("chinmay.scheduler","in onclick");
                Intent i = new Intent(getContext(),AddExamActivity.class);
                startActivity(i);

            }
        });
        return myview;
    }

    @Override
    public void onResume()
    {
        super.onResume();
        String [] exams=dbHandler.retrieveListview(3);
        ListAdapter la=new row(getContext(),exams);
        ListView listview=(ListView)myview.findViewById(R.id.my_eaxm_list);
        listview.setAdapter(la);
    }

}
