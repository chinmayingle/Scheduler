package com.example.chinmay.scheduler;

import android.app.DatePickerDialog;
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
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

//taskk activityadding
public class AddTaskActivity extends AppCompatActivity {
    int i = 1;
    //db_handler databas
    MyDbHandler dbhandler;
    int month,day,yeard;
    String m1,d1,y1,final_date;
    TextView  tv9;
    EditText tv1;
    Spinner spinner;

    Button b,bt;
    final Calendar myCalendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addtask);
       // dbhandler = new MyDbHandler(this, null, null, 1);
        bt=(Button)findViewById(R.id.btask);
        tv1=(EditText) findViewById(R.id.taskname);
        tv9=(TextView)findViewById(R.id.textView9);
        dbhandler = new MyDbHandler(this, null, null, 3);
        Log.i("AAA", "IN ADDACTIVITY");
        b = (Button) findViewById(R.id.button_date_task);
        spinner = (Spinner) findViewById(R.id.spinner);
        b.setOnClickListener(new View.OnClickListener() {
                                 @Override
                                 public void onClick(View view) {
                                     Log.i("chinmay.scheduler", "click on task");

                                     if (view != null) {
                                         InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                                         imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
                                         Log.i("a","not null in");
                                     }

                                     new DatePickerDialog(AddTaskActivity.this, date, myCalendar
                                             .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                                             myCalendar.get(Calendar.DAY_OF_MONTH)).show();




                                   }
                             }

        );

        bt.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String subject= spinner.getSelectedItem().toString();
                        dbhandler.addTask( subject,tv1.getText().toString(),final_date);
                        Log.i("hello","are you reaching hereeeeee");
                        String displaytasks= dbhandler.databaseToString();
                        tv9.setText(displaytasks);
                       finish();
                    }
                }
        );

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






    }
    final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
              Log.i("Date Pickerrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr","in date picker");
            myCalendar.set(Calendar.YEAR, year);
            myCalendar.set(Calendar.MONTH, monthOfYear);
            myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            String myFormat = "yyyy-MM-dd";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.US);
            yeard=year;month=monthOfYear;day=dayOfMonth;
            Log.i("a","date selected is:"+yeard+" "+month+" "+day);
            m1= Integer.toString(month);
            y1= Integer.toString(yeard);
            d1= Integer.toString(day);

            final_date=d1+":"+m1+":"+y1;
             Log.i("finallllll  dateeee is",final_date);
            b.setText(sdf.format(myCalendar.getTime()));


        }

    };
}


