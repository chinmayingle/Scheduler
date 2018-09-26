package com.example.chinmay.scheduler;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import android.widget.ListView;

public class AndroidFragment2 extends Fragment {
    MyDbHandler dbhandler;
    View rootView;
    ListView l;
    public AndroidFragment2() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_android_fragment2, container, false);
        dbhandler = new MyDbHandler(getContext(),null,null,5);
        String [] list=dbhandler.retrieveListview(2);
        ListAdapter ll=new row(getContext(),list);
        l = (ListView) rootView.findViewById(R.id.listid);
        l.setAdapter(ll);

        return rootView;
    }


}
