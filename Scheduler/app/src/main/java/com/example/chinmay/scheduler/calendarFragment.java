package com.example.chinmay.scheduler;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

/**
 * Created by chinmay on 23/9/17.
 */

public class calendarFragment extends Fragment {
    View myview;

    private java.util.Calendar cal = java.util.Calendar.getInstance(Locale.ENGLISH);
    private SimpleDateFormat formatter = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
    public  GridView calendarGridView;
    private ImageView previousButton, nextButton;
    Button b11,b22;
    TextView tv;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        myview=inflater.inflate(R.layout.calendar_layout,container,false);


        List<Date> dayValueInCells = new ArrayList<Date>();
        Log.i("chinmay.gridview","calling eventdetails");
        java.util.Calendar mCal = (java.util.Calendar)cal.clone();
        mCal.set(java.util.Calendar.DAY_OF_MONTH, 1);

        int firstDayOfTheMonth = mCal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
        mCal.add(java.util.Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while(dayValueInCells.size() < 42){
            dayValueInCells.add(mCal.getTime());
            mCal.add(java.util.Calendar.DAY_OF_MONTH, 1);
        }
        //Log.i("chinmay.gridview","displaying them : \n"+dayValueInCells.toString()+"\n");
        tv=(TextView)myview.findViewById(R.id.display_current_date);
        String sDate = formatter.format(cal.getTime());
        tv.setText(sDate);
        Log.i("chinmay.gridview",sDate+"\n");

        GridView gv=(GridView)myview.findViewById(R.id.calendar_grid);
        gridv ad=new gridv(getContext(),dayValueInCells,cal);
//        mAdapter = new GridAdapter(context, dayValueInCells, cal, mEvents);


        b11=(Button)myview.findViewById(R.id.b1);
        b22=(Button)myview.findViewById(R.id.b2);
        b11.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("chinmay.gridview","in b1 click listener");

                        cal.add(java.util.Calendar.MONTH, -1);
                        setUpCalendarAdapter();
                    }
                }
        );

        b22.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        cal.add(java.util.Calendar.MONTH, 1);
                        Log.i("chinmay.gridview","in b2 click listener");

                        setUpCalendarAdapter();
                    }
                }
        );

        gv.setAdapter(ad);
        return myview;
    }


    private void setNextButtonClickEvent(){
        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cal.add(java.util.Calendar.MONTH, 1);
                setUpCalendarAdapter();
            }
        });

    }
    private void setGridCellClickEvents(){
        calendarGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Toast.makeText(getContext(), "Clicked " + position, Toast.LENGTH_LONG).show();
                // Toast.makeText(this, "this is first fragment", Toast.LENGTH_SHORT).show();
                Log.i("","clicked"+position);

            }
        });


    }

    private void setUpCalendarAdapter()
    {
        List<Date> dayValueInCells = new ArrayList<Date>();

        //mQuery = new DatabaseQuery(context);       //dbhandler
        Log.i("chinmay.gridview","calling eventdetails");
        //List<EventObjects> mEvents = mQuery.getAllFutureEvents();
        java.util.Calendar mCal = (java.util.Calendar)cal.clone();
        mCal.set(java.util.Calendar.DAY_OF_MONTH, 1);

        int firstDayOfTheMonth = mCal.get(java.util.Calendar.DAY_OF_WEEK) - 1;
        mCal.add(java.util.Calendar.DAY_OF_MONTH, -firstDayOfTheMonth);
        while(dayValueInCells.size() < 35){
            dayValueInCells.add(mCal.getTime());
            mCal.add(java.util.Calendar.DAY_OF_MONTH, 1);
        }
        Log.i("chinmay.gridview","displaying them : \n"+dayValueInCells.toString()+"\n");
        String sDate = formatter.format(cal.getTime());
        Log.i("chinmay.gridview",sDate+"\n");
        tv.setText(sDate);


        GridView gv=(GridView)myview.findViewById(R.id.calendar_grid);
        gridv ad=new gridv(getContext(),dayValueInCells,cal);
//        mAdapter = new GridAdapter(context, dayValueInCells, cal, mEvents);

        gv.setAdapter(ad);


    }

}
