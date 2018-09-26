package com.example.chinmay.scheduler;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;


public class AndroidFragment extends Fragment {

    MyDbHandler dbhandler;
    View rootView;
    ListView l;
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        rootView=inflater.inflate(R.layout.fragment_android_fragment, container, false);
        dbhandler = new MyDbHandler(getContext(),null,null,5);
        String [] list=dbhandler.retrieveListview(4);
        ListAdapter ll=new row(getContext(),list);
        l = (ListView) rootView.findViewById(R.id.listid);
        l.setAdapter(ll);

        return rootView;       // return textView;
    }
};