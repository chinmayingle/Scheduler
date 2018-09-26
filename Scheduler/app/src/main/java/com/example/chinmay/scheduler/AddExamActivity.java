package com.example.chinmay.scheduler;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class AddExamActivity extends AppCompatActivity {

    int day,month,yeard;
    final Calendar myCalendar = Calendar.getInstance();

    Button starttime,endtime,addbutton,examdate;
    String stime,etime,edate,total_time,s1,s2,m1,y1,d1,final_date1;
    static final int d_id=0;
    static final int d_idd=1;
    int hours;
    int mins;
    TextView examdisplay;
    MyDbHandler dbhandler;


    public Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addexam);
        examdate = (Button) findViewById(R.id.examdate);
        starttime=(Button)findViewById(R.id.starttime);
        addbutton=(Button)findViewById(R.id.examfadd);
        spinner = (Spinner) findViewById(R.id.spinner);
        endtime=(Button)findViewById(R.id.endtime);
        dbhandler = new MyDbHandler(this, null, null, 5);
        examdisplay=(TextView)findViewById(R.id.textView4);

        //SPINNER-START
        List<String> list = new ArrayList<String>();
        list=dbhandler.retrieveSpinner();
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, list
        );
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dataAdapter);

        //ONCLICK
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                // your code here
                //textView.setText(parentView.getSelectedItem().toString());
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
                //textView.setText("nothing selected");
            }

        });

        //SPINNER-END

        addbutton.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                       // dbhandler.addSubject("subject");
                        String subject= spinner.getSelectedItem().toString();
                        dbhandler.addExam(subject,final_date1,stime,etime);
                        String display = dbhandler.displayExam();
                        examdisplay.setText(display);
                      //  finish();
                    }
                }
        );

        endtime.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        showDialog(d_idd);
                    }
                }
        );
        examdate.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {

                                     if (view != null) {
                                         InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                         imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                         Log.i("a","not null in");
                                     }

                                     new DatePickerDialog(AddExamActivity.this, date, myCalendar
                                             .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                             myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                                 }
                             }

        );
        starttime.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (view != null) {
                            InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                            Log.i("a","not null in");
                        }
                        showDialog(d_id);
                    }
                }
        );


    }

    protected Dialog onCreateDialog(int id)
    {
        if(d_id==id)
        {
            return new TimePickerDialog(AddExamActivity.this,ktimepl,hours,mins,false);

        }if(d_idd==id)
    {
        return new TimePickerDialog(AddExamActivity.this,ktimepl2,hours,mins,false);

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
                    Toast.makeText(AddExamActivity.this,hours+"111"+mins,Toast.LENGTH_LONG).show();
                    starttime.setText(i+":"+i1);
                    stime=total_time;
                    Log.i("starttime is---------:",stime);

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
                    Toast.makeText(AddExamActivity.this,hours+"111"+mins,Toast.LENGTH_LONG).show();
                    endtime.setText(i+":"+i1);
                    etime=total_time;
                    Log.i("endtime is---------:",etime);

                }
            };



    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {

            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            yeard=year;month=monthOfYear;day=dayOfMonth;
            m1= Integer.toString(month);
            y1= Integer.toString(yeard);
            d1= Integer.toString(day);

            final_date1=d1+":"+m1+":"+y1;
            Log.i("a","date selected is:"+yeard+" "+month+" "+day);
            examdate.setText(sdf.format(myCalendar.getTime()));


        }

    };
}

