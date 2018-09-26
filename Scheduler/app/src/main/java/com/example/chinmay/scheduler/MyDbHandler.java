package com.example.chinmay.scheduler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * Created by chinmay on 29/9/17.
 */

public class MyDbHandler extends SQLiteOpenHelper{

    private static final int DATABASE_VERSION = 5;
    private static final String DATABASE_NAME = "scheduler.db";
    public static final String TABLE_SUBJECTS = "subjects";
    public static final String TABLE_DETAILS = "details";
    public static final String TABLE_DURATION = "duration";
    public static final String TABLE_EXAM="exam";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_DID = "_did";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_TYPE = "type";
    public static final String COLUMN_START = "start";
    public static final String COLUMN_END = "end";
   // public static final String COLUMN_TIME = "time";
    //public static final String COLUMN_D = "";
    public static final String COLUMN_MON="mon";
    public static final String COLUMN_TUE="tue";
    public static final String COLUMN_WED="wed";
    public static final String COLUMN_THU="thu";
    public static final String COLUMN_FRI="fri";
    public static final String COLUMN_SAT="sat";
    public static final String COLUMN_SUN="sun";


    public static final String COLUMN_EID="_eid";
    public static final String COLUMN_DATE="date";



    public static final String COLUMN_STARTTIME="starttime";
    public static final String COLUMN_ENDTIME="endtime";

    public static final String TABLE_TODO="to_do";
    public static final String COLUMN_TID="_Tid";
    public static final String COLUMN_TITLE="title";
    public static final String COLUMN_DUEDATE="duedate";




    public MyDbHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {


        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
        Log.i("DBH: ","constructor");
    }

    public void onOpen(SQLiteDatabase db)
    {
        Log.i("blah","opened!");
    }
    @Override
    public void onCreate(SQLiteDatabase db) {


        //subjects table
        Log.i("DBH: ","oncreate");
        String query = "CREATE TABLE IF NOT EXISTS " + TABLE_SUBJECTS + "(" +
                COLUMN_ID + " INTEGER , " +
                COLUMN_NAME + " TEXT PRIMARY KEY " +
                ");";
        db.execSQL(query);

        //duartion table
        query = "CREATE TABLE IF NOT EXISTS " + TABLE_DURATION + "(" +
                COLUMN_DID + " INTEGER PRIMARY KEY AUTOINCREMENT , " +
                COLUMN_START + " TEXT ," +
                COLUMN_END + "TEXT "+
                ");";
        db.execSQL(query);


        //detail() TABLE
        String query1 = "CREATE TABLE IF NOT EXISTS " + TABLE_DETAILS + " ( " +
                COLUMN_NAME + " TEXT , " +
                COLUMN_TYPE + " BOOLEAN , " +
                //COLUMN_START + " TEXT, " +
                COLUMN_DID + " INTEGER, " +        //---------------------------------------
                COLUMN_STARTTIME + " TEXT, " +
                COLUMN_ENDTIME + " TEXT, " +
                COLUMN_MON + " INTEGER, " +
                COLUMN_TUE + " INTEGER, " +
                COLUMN_WED + " INTEGER, " +
                COLUMN_THU + " INTEGER, " +
                COLUMN_FRI + " INTEGER, " +
                COLUMN_SAT + " INTEGER, " +
                COLUMN_SUN + " INTEGER, " +
                "FOREIGN KEY (" + COLUMN_NAME + ") REFERENCES " + TABLE_SUBJECTS + "("
                + COLUMN_NAME + ") ON DELETE CASCADE, PRIMARY KEY ("
                + COLUMN_NAME + "," + COLUMN_TYPE + ") , " +
                "FOREIGN KEY (" + COLUMN_DID + ") REFERENCES " + TABLE_DURATION + "("
                + COLUMN_DID + ") ON DELETE CASCADE"+
                ");";
        db.execSQL(query1);

        // create table examFragment( exam_id int , exam_name varchar(20), primary key(exam_id));
        query="CREATE TABLE IF NOT EXISTS " + TABLE_EXAM +" ( "+
                COLUMN_EID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME +" TEXT ," +
                COLUMN_DATE +" TEXT ,"+
                COLUMN_STARTTIME + " TEXT ," +
                COLUMN_ENDTIME + " TEXT ," +
                "FOREIGN KEY (" + COLUMN_NAME + ") REFERENCES " + TABLE_SUBJECTS + "("
                + COLUMN_NAME + ") ON DELETE CASCADE ) ;";
        db.execSQL(query);

        //toe to_do table
        query="CREATE TABLE IF NOT EXISTS " + TABLE_TODO +" ( "+
                COLUMN_TID + " INTEGER PRIMARY KEY AUTOINCREMENT ," +
                COLUMN_NAME +" TEXT , " +
                COLUMN_TITLE +" TEXT , "+
                COLUMN_DUEDATE + " TEXT ," +
                "FOREIGN KEY (" + COLUMN_NAME + ") REFERENCES " + TABLE_SUBJECTS + "("
                + COLUMN_NAME + ") ON DELETE CASCADE ) ;";
        db.execSQL(query);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        Log.i("DBH: ","onupgrade");
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_SUBJECTS);
        //onCreate(db);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_DURATION);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_TODO);
        onCreate(db);
    }

    public void addSubject(String name) {
        Log.i("DBH: ","adding subject");
        ContentValues values = new ContentValues();
        values.put(COLUMN_ID, 1);
        values.put(COLUMN_NAME, name);


        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_SUBJECTS, null, values);
        Log.i("DBH: ","added subject");
        //db.execSQL("DROP TABLE IF EXISTS " + TABLE_EXAM);

        //db.close();

    }

    public void addDetails(String name, boolean type, int duration_id, String start_time, String end_time, boolean mon,boolean tue,boolean wed,boolean thu,boolean fri,boolean sat,boolean sun) {

        Log.i("DBH: ","adding details");

        ContentValues values = new ContentValues();
        //values.put(COLUMN_ID, id);
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_TYPE, type);
        //values.put(COLUMN_START, start);
        //values.put(COLUMN_END, end);
        values.put(COLUMN_DID, duration_id);
        values.put(COLUMN_STARTTIME, start_time);
        values.put(COLUMN_ENDTIME, end_time);
        values.put(COLUMN_MON,mon);
        values.put(COLUMN_TUE,tue);
        values.put(COLUMN_WED,wed);
        values.put(COLUMN_THU,thu);
        values.put(COLUMN_FRI,fri);
        values.put(COLUMN_SAT,sat);
        values.put(COLUMN_SUN,sun);
        //values.put(COLUMN_DAYS, days);
        SQLiteDatabase db = getWritableDatabase();

        db.insert(TABLE_DETAILS,null,values);
        //db.execSQL("DELETE  FROM details where 1;");
        Log.i("DBH: ","details added");
        //db.execSQL(" insert into "+ TABLE_DETAILS + " ( " + COLUMN_ID +" ) "+
        //" values ( SELECT " + COLUMN_ID +" from " +TABLE_SUBJECTS + " ORDER BY "+ COLUMN_ID + " DESC LIMIT 1 " + " );");
        //db.close();
    }

    public void addTask(String sname,String title,String duedate)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,sname);
        values.put(COLUMN_TITLE,title);
        values.put(COLUMN_DUEDATE,duedate);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_TODO,null,values);
        db.close();
       // db.execSQL("DELETE FROM to_do where 1;");
    }
    public void addExam(String subject_name, String date,String start_time,String end_time)
    {

        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,subject_name);
        values.put(COLUMN_DATE,date);
        values.put(COLUMN_STARTTIME,start_time);
        values.put(COLUMN_ENDTIME,end_time);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_EXAM,null,values);
        Log.i("exam add hoo raha hai","ADDDDDDDDDDDDDDDDDDD");
        db.close();

    }

    public  void addDuration(String start, String end )
    {
        ContentValues values=new ContentValues();

        values.put(COLUMN_START, start);
        values.put(COLUMN_END,end);
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_DURATION,null,values);
        db.close();
    }


    /*public void deleteSubject(String name){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_SUBJECTS + " WHERE " + COLUMN_NAME + "=\"" + name + "\";");
    }

    public void deleteEntry(int id, String type){
        SQLiteDatabase db = getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_DETAILS + " WHERE "  + COLUMN_TYPE+ "=\"" + type +"and" + COLUMN_ID+ "=\"" + id + "\";");
    }*/




    public String databaseToString(){
        Log.i("DBH: ","dbts start");
        String dbString = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_TODO + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results


        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_TID)) != null) {
               /* dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_TYPE));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DID));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_STARTTIME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_ENDTIME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_MON));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_TUE));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_WED));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_THU));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_FRI));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_SAT));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_SUN));
                dbString+=" ";*/
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_TID));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_TITLE));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DUEDATE));
                dbString+=" ";
               // dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DAYS));
                dbString += "\n";
                Log.i("display: ","fetching");
            }
            recordSet.moveToNext();
        }
        db.close();
        Log.i("DBH: ","dbts stop");
        return dbString;


    }




    public String displayExam(){
        Log.i("DBH: ","DE:: start");
        String dbString = "";
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_EXAM + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();

        //Position after the last row means the end of the results


        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            //Log.i("displayExam","about to diaply");
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_EID)) != null) {
                ///Log.i("displayExam","in display");
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_EID));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DATE));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_STARTTIME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_ENDTIME));
                dbString+=" ";
                // dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DAYS));
                dbString += "\n";
                //Log.i("display: ","fetching");
            }
            recordSet.moveToNext();
        }
        //db.close();
        ///Log.i("DBH: ","DE:: stop");
        db.close();
        return dbString;


    }

    /*public int retrieveId(String starttime, String endtime){
        //Log.i("DBH: ","DE:: start");

        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT "+ COLUMN_DID + " FROM " + TABLE_DURATION + " WHERE 1";// why not leave out the WHERE  clause?

        //Cursor points to a location in your results
        Cursor recordSet = db.rawQuery(query, null);
        //Move to the first row in your results
        recordSet.moveToFirst();
        //Position after the last row means the end of the results
        while (!recordSet.isAfterLast()) {
            // null could happen if we used our empty constructor
            Log.i("displayExam","about to diaply");
            if (recordSet.getString(recordSet.getColumnIndex(COLUMN_EID)) != null) {
                Log.i("displayExam","in display");
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_EID));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DATE));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_STARTTIME));
                dbString+=" ";
                dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_ENDTIME));
                dbString+=" ";
                // dbString += recordSet.getString(recordSet.getColumnIndex(COLUMN_DAYS));
                dbString += "\n";
                Log.i("display: ","fetching");
            }
            recordSet.moveToNext();
        }
        //db.close();
        Log.i("DBH: ","DE:: stop");
        return dbString;


    }*/

    public List retrieveSpinner()
    {
        List<String> list = new ArrayList<String>();

            String dbString = "";
            SQLiteDatabase db = getReadableDatabase();
            String query = "SELECT "+ COLUMN_NAME + " FROM " + TABLE_SUBJECTS+ " WHERE 1";
            Cursor recordSet = db.rawQuery(query, null);
            recordSet.moveToFirst();

            while (!recordSet.isAfterLast()) {
                Log.i("displayExam","about to diaply");
                if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME)) != null) {
                    Log.i("displayExam","in display");
                    dbString = recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                    list.add(dbString);
                    Log.i("display: ","fetching");
                }
                recordSet.moveToNext();
            }
            Log.i("DBH: ","DE:: stop");
        db.close();
        return list;
    }

    public  void  deleteEntry(String row1, String table, String name)
    {

        SQLiteDatabase db = getWritableDatabase();
        String query = " DELETE  FROM " + table + " WHERE " + row1 + " = " + name  ;
        db.execSQL(query);
        db.close();
    }
    public String[]  retrieveListview(int option)
    {
        String[] list = new String[]{};
        String dbString = "";
        SQLiteDatabase db = getReadableDatabase();
        int i=0;
        Calendar cal = Calendar.getInstance();
        //int count=getRowCount(TABLE_EXAM);
        String today= cal.get(Calendar.DAY_OF_MONTH) +":"+ (cal.get(Calendar.MONTH)) +":" + cal.get(Calendar.YEAR);
        Log.i("today: ",today);

        if(option==1) // for subjects in listview
        {
            int count=getRowCount(TABLE_SUBJECTS);
            //Log.i("DBHandler",)
            list= new String[count];

            String query = "SELECT "+ COLUMN_NAME + " FROM " + TABLE_SUBJECTS+ " WHERE 1";
            Cursor recordSet = db.rawQuery(query, null);
            recordSet.moveToFirst();

            while (!recordSet.isAfterLast()) {
                Log.i("displayExam","about to display");
                if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME)) != null) {
                    Log.i("displayExam","in display");
                    dbString = recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                    if(recordSet.isAfterLast())
                    {
                        i--;
                    }
                    list[i]= dbString;
                    i++;
                    Log.i("display: ","fetching");
                }
                recordSet.moveToNext();
            }
            Log.i("DBH: ","DE:: stop");
        }
        else if(option==2) //// for asignments in listview
        {
            int count=getRowCount(TABLE_TODO);
            list= new String[count];
            String query = "SELECT "+ COLUMN_TITLE + " FROM " + TABLE_TODO+ " WHERE 1";
            Cursor recordSet = db.rawQuery(query, null);
            recordSet.moveToFirst();

            while (!recordSet.isAfterLast()) {
                Log.i("displayExam","about to diaply");
                if (recordSet.getString(recordSet.getColumnIndex(COLUMN_TITLE)) != null) {
                    Log.i("displayExam","in display");
                    dbString = recordSet.getString(recordSet.getColumnIndex(COLUMN_TITLE));
                    // list.add(dbString);
                    list[i]= dbString;
                    i++;
                    Log.i("display: ","fetching");
                }
                recordSet.moveToNext();
            }
            Log.i("DBH: ","DE:: stop");
        }
        else if (option==3)  // for exams in listview
        {
            int count=getRowCount(TABLE_EXAM);
            list= new String[count];
            String query = "SELECT "+ COLUMN_NAME + " FROM " + TABLE_EXAM + " WHERE 1";
            Cursor recordSet = db.rawQuery(query, null);
            recordSet.moveToFirst();

            while (!recordSet.isAfterLast()) {
                Log.i("displayExam","about to display");
                if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME)) != null) {
                    Log.i("displayExam","in display");
                    dbString = recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));
                    // list.add(dbString);
                    list[i]= dbString;
                    i++;
                    Log.i("display: ","fetching");
                }
                recordSet.moveToNext();
            }
            Log.i("DBH: ","DE:: stop");
        }
        else if(option==4) // todays exams
        {
            String query1 = "SELECT " + COLUMN_NAME  + " FROM " + TABLE_EXAM + " WHERE " + COLUMN_DATE + " BETWEEN \"" + today + "\" AND \"" + today + "\";" ;
            Cursor recordSet = db.rawQuery(query1, null);
            list= new String[recordSet.getCount()];

            recordSet.moveToFirst();

            while (!recordSet.isAfterLast()) {
              //  Log.i("displayExam","about to display");

                if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME)) != null) {
                    Log.i("displayExam","in display");

                    dbString = recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));

                    //dbString+=recordSet.getString(recordSet.getColumnIndex(COLUMN_DATE));
                    // list.add(dbString);
                    list[i]= dbString;
                    i++;
                    Log.i("display: ","fetching");
                }
                recordSet.moveToNext();
                Log.i("displayExam","in displayYYYYYYYYYYYYYYYYYYYYYYY");
            }
            Log.i("DBH: ","DE:: stop");
        }
        else if(option==5)//todays classes
        {

            String query1 = " SELECT " + COLUMN_NAME  + " , " + COLUMN_TYPE + " FROM " + TABLE_DETAILS + " WHERE " + COLUMN_DATE + " BETWEEN \"" + today + "\" AND \"" + today + "\";" ;
            Cursor recordSet = db.rawQuery(query1, null);
            list= new String[recordSet.getCount()];

            recordSet.moveToFirst();

            while (!recordSet.isAfterLast()) {
                //  Log.i("displayExam","about to display");

                if (recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME)) != null) {
                    Log.i("displayExam","in display");

                    dbString = recordSet.getString(recordSet.getColumnIndex(COLUMN_NAME));

                    //dbString+=recordSet.getString(recordSet.getColumnIndex(COLUMN_DATE));
                    // list.add(dbString);
                    list[i]= dbString;
                    i++;
                    Log.i("display: ","fetching");
                }
                recordSet.moveToNext();
                Log.i("displayExam","in displayYYYY");
            }
            Log.i("DBH: ","DE:: stop");
        }
       db.close();
        return list;
    }

    public int getRowCount(String table) {
        SQLiteDatabase db = getReadableDatabase();
        String query = "SELECT * FROM " + table;
        Cursor recordSet1 = db.rawQuery(query, null);
        Integer count = recordSet1.getCount();
        recordSet1.close();
       // db.close();
        //Log.i("count: ",  count.toString());
        return count;
    }
}