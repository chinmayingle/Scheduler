package com.example.chinmay.scheduler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import java.util.StringTokenizer;

//planner
public class AddSubjectActivity extends AppCompatActivity {

    Button t,t2;
    String s1,s2,total_time,stime,etime;
   static final   int d_id=0,d_idd=1;
    int hours;
    int mins;
    public Spinner spinner;
    MyDbHandler dbhandler;
    TextView tv,tv5;
    ToggleButton type;
    CheckBox mon,tue,wed,thu,fri,sat,sun;
    TextView display;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addsubject);
        Log.i("aa","nashib");

        Button b=(Button)findViewById(R.id.subbut);
         t=(Button)findViewById(R.id.start_time);
        t2=(Button)findViewById(R.id.end_time);
        //spinner = (Spinner) findViewById(R.id.spinner);
        tv= (TextView)findViewById( R.id.editText4);
        type = (ToggleButton) findViewById(R.id.type);
         mon=(CheckBox)findViewById(R.id.mon);
        if(mon.isChecked())
        {
            mon.setChecked(false);
        }
        tue=(CheckBox)findViewById(R.id.tue);
        if(tue.isChecked())
        {
            tue.setChecked(false);
        }
        wed=(CheckBox)findViewById(R.id.wed);
        if(wed.isChecked())
        {
            wed.setChecked(false);
        }
        thu=(CheckBox)findViewById(R.id.thu);
        if(thu.isChecked())
        {
            thu.setChecked(false);
        }
        fri=(CheckBox)findViewById(R.id.fri);
        if(fri.isChecked())
        {
            fri.setChecked(false);
        }
        sat=(CheckBox)findViewById(R.id.sat);
        if(sat.isChecked())
        {
            sat.setChecked(false);
        }
        sun=(CheckBox)findViewById(R.id.sun);
        if(sun.isChecked())
        {
            sun.setChecked(false);
        }
        tv5= (TextView)findViewById( R.id.textView5);

        dbhandler = new MyDbHandler(this, null, null, 5);






        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //setContentView(R.layout.content_main);

                dbhandler.addSubject(tv.getText().toString() );
                dbhandler.addDetails(tv.getText().toString(),type.isChecked(),1,stime,etime,mon.isChecked(),tue.isChecked(),wed.isChecked(),thu.isChecked(),fri.isChecked(),sat.isChecked(),sun.isChecked());
                //dbhandler.addDuration("01/06/2017","0");
                String mydisplay=dbhandler.databaseToString();
                tv5.setText(mydisplay);
                finish();

            }
        });

        t.setOnClickListener(

               new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {

                        /*
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                        }

                        new DatePickerDialog(AddSubjectActivity.this, date, myCalendar
                                .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                                */
                        showDialog(d_id);
                       stime=total_time;

                     }
                }
        );

        t2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(d_idd);
                        Log.i("aaa","calling dialog");
                       etime=total_time;

                    }
                }
        );



    }



    //get parameter from database and returns
   /* public List setSpinner()
    {
        List<String> list = new ArrayList<String>();
        list.add("sayali");
        list.add("chinmay");
        list.add("tejasvi");
        list.add("shambhavi");
        list.add("anuj");
        list.add("omkar");
       *//*for(int i=0;i<mylist.size();i++)
       {
           list.add(mylist.get(i));
       }*//*
        return list;
    }*/
    @Override
    protected Dialog onCreateDialog(int id)
    {
        if(id==d_id)
        {
            return new TimePickerDialog(AddSubjectActivity.this,ktimepl,hours,mins,false);

        }
        if(id==d_idd)
        {
            return new TimePickerDialog(AddSubjectActivity.this,ktimepl2,hours,mins,false);
        }
        return null;

    }

    protected TimePickerDialog.OnTimeSetListener ktimepl =
            new TimePickerDialog.OnTimeSetListener()
            {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hours=i;
                    mins=i1;
                     s1= Integer.toString(hours);
                     s2= Integer.toString(mins);
                    total_time= s1+":"+s2;
                    stime=total_time;
                    Log.i("aaaa",total_time);


                    Toast.makeText(AddSubjectActivity.this,hours+":"+mins,Toast.LENGTH_LONG).show();
                    Log.i("TimePicker" , total_time);

                }
            };

    protected TimePickerDialog.OnTimeSetListener ktimepl2 =
            new TimePickerDialog.OnTimeSetListener()
            {
                @Override
                public void onTimeSet(TimePicker timePicker, int i, int i1) {
                    hours=i;
                    mins=i1;
                    s1= Integer.toString(hours);
                    s2= Integer.toString(mins);
                    total_time= s1+":"+s2;
                    etime=total_time;
                    Log.i("aaaa",total_time);


                    Toast.makeText(AddSubjectActivity.this,hours+":"+mins,Toast.LENGTH_LONG).show();
                    Log.i("TimePicker" , total_time);

                }
            };




    final Calendar myCalendar = Calendar.getInstance();

    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);

            t.setText(sdf.format(myCalendar.getTime()));

        }


    };




}
