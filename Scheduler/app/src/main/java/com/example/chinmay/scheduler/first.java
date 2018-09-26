package com.example.chinmay.scheduler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by chinmay on 21/9/17.
 */


//today
public class first extends Fragment {
    View myview;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview =inflater.inflate(R.layout.content_main,container,false);
        return myview;
    }

}
