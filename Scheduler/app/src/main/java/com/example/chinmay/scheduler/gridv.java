package com.example.chinmay.scheduler;

/**
 * Created by chinmay on 2/10/17.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class gridv extends ArrayAdapter {
    private static final String TAG = gridv.class.getSimpleName();
    private LayoutInflater mInflater;
    private List<Date> monthlyDates;//list view of dates
    private Calendar currentDate;
   // private List<EventObjects> allEvents; //list view of events per month/day need to  find out???

    public gridv(Context context, List<Date> monthlyDates, Calendar currentDate) {
        super(context, R.layout.new_layout);
        this.monthlyDates = monthlyDates;
        this.currentDate = currentDate;
//        this.allEvents = allEvents;
        mInflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Date mDate = monthlyDates.get(position);

        Calendar dateCal = Calendar.getInstance();
        dateCal.setTime(mDate);
        int dayValue = dateCal.get(Calendar.DAY_OF_MONTH);
        int displayMonth = dateCal.get(Calendar.MONTH) + 1;
        int displayYear = dateCal.get(Calendar.YEAR);
        int currentMonth = currentDate.get(Calendar.MONTH) + 1;
        int currentYear = currentDate.get(Calendar.YEAR);
        View view = convertView;
        if(view == null){
            view = mInflater.inflate(R.layout.new_layout, parent, false);
        }
        if(displayMonth == currentMonth && displayYear == currentYear){
            view.setBackgroundColor(Color.parseColor("#ffffff"));
            Button b1=(Button)view.findViewById(R.id.b1);
            b1.setVisibility(view.INVISIBLE);

        }else{
            view.setBackgroundColor(Color.parseColor("#000000"));
        }
        //Add day to calendar
        TextView cellNumber = (TextView)view.findViewById(R.id.tv1);
        cellNumber.setText(String.valueOf(dayValue));
        //Add events to the calendar
        //TextView eventIndicator = (TextView)view.findViewById(R.id.event_id);
        Calendar eventCalendar = Calendar.getInstance();
        /*
        for(int i = 0; i < allEvents.size(); i++){
            eventCalendar.setTime(allEvents.get(i).getDate());
            if(dayValue == eventCalendar.get(Calendar.DAY_OF_MONTH) && displayMonth == eventCalendar.get(Calendar.MONTH) + 1
                    && displayYear == eventCalendar.get(Calendar.YEAR)){
                eventIndicator.setBackgroundColor(Color.parseColor("#FF4081"));
            }
        }*/
        return view;
    }
    @Override
    public int  getCount() {
        return monthlyDates.size();
    }
    @Nullable
    @Override
    public Object getItem(int position) {
        return monthlyDates.get(position);
    }
    @Override
    public int getPosition(Object item) {
        return monthlyDates.indexOf(item);
    }
}
