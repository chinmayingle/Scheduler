package com.example.chinmay.scheduler;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.LoginFilter;
import android.util.Log;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;


public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private static final String REGISTER_URL="https://iamprojectuchiha.000webhostapp.com/con.php";


    String urlSuffix = "?username=" + "kedar" + "&password=" + "kedar123" ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        /*
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        */

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        //CharSequence ch =getTitle();
        //Toast.makeText(this, "this is first fragment", Toast.LENGTH_SHORT).show();


    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        android.app.FragmentManager fragmentManager = getFragmentManager();
        //FragmentManager fragm=getFragmentManager()

        if (id == R.id.first_layout) {
            Log.i("com.example.chinmay","in first i.e 1111");
            Toast.makeText(this, "this is first fragment", Toast.LENGTH_SHORT).show();
            //fragmentManager.beginTransaction().replace(R.id.content_frame ,new first()).commit();
            Intent intent = new Intent(getApplicationContext(),TodayActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_slideshow) {
            Toast.makeText(this, "calendar fragment", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction().replace(R.id.content_frame,new calendarFragment()).commit();

        } else if (id == R.id.nav_manage) {
            Toast.makeText(this, "assignment fragment", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction().replace(R.id.content_frame,new assignmentFragment()).commit();


        } else if (id == R.id.four) {
            Toast.makeText(this, "planner fragment", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction().replace(R.id.content_frame,new plannerFragment()).commit();


        } else if (id == R.id.exams) {
            Toast.makeText(this, "exam fragment", Toast.LENGTH_LONG).show();
            fragmentManager.beginTransaction().replace(R.id.content_frame,new examFragment()).commit();

        }else if(id == R.id.main)
        {
            Toast.makeText(this, "main fragment", Toast.LENGTH_LONG).show();
             //register( "drake", "cool");
            RegisterUser ur=new RegisterUser();
            ur.execute(urlSuffix);

            Log.i("aaa","before");
            fragmentManager.beginTransaction().replace(R.id.content_frame,new zero()).commit();


        }else
        {
            Log.i("com.example.chinmay","in first i.e 1111");
            Toast.makeText(this, "works??", Toast.LENGTH_SHORT).show();
            fragmentManager.beginTransaction().replace(R.id.content_frame ,new first()).commit();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public Context getMainActivityContext()
    {
        return MainActivity.this;
    }


/*
class data extends AsyncTask<String,void,String>
{
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(String... strings) {
        String a=param


        return null;
    }

    @Override
    protected void onPostExecute(String s) {


    }
}
*/
/*
class RegisterUser extends AsyncTask<String, Void, String> {



    ProgressDialog loading;

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //loading = ProgressDialog.show(Signup.this, "Please Wait", null, true, true);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        loading.dismiss();
        //Toast.makeText(getApplicationContext(),"Registered", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected String doInBackground(String... params) {
        String s = params[0];
        BufferedReader bufferReader=null;
        try {
            URL url=new URL(REGISTER_URL+s);
            HttpURLConnection con=(HttpURLConnection)url.openConnection();
            bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
            String result;
            result=bufferReader.readLine();

            return  result;

            RegisterUser ur=new RegisterUser();
            ur.execute(urlSuffix);
        }

        }catch (Exception e){
            return null;
        }
    }

}
   */

        class RegisterUser extends AsyncTask<String, Void, String> {
//            ProgressDialog loading;

            @Override
            protected void onPreExecute() {
                super.onPreExecute();
                Log.i("aaa","in preexecute");
                //loading = ProgressDialog.show(Signup.this, "Please Wait", null, true, true);
            }

            @Override
            protected void onPostExecute(String s) {
                super.onPostExecute(s);
                Log.i("aaa","postexcute");
                //loading.dismiss();
                //Toast.makeText(getApplicationContext(),"Registered", Toast.LENGTH_SHORT).show();
            }

            @Override
            protected String doInBackground(String... params) {
               /* Log.i("aaa","doinbackground");
                String s = params[0];
                BufferedReader bufferReader=null;
                try {
                    Log.i("aaa","i");
                    URL url=new URL(REGISTER_URL+s);
                    HttpURLConnection con=(HttpURLConnection)url.openConnection();
                    con.setRequestMethod("POST");
                    con.setDoOutput(true);
                    con.setDoInput(true);
                    OutputStream os=con.getOutputStream();


                    bufferReader=new BufferedReader(new InputStreamReader(con.getInputStream()));
                    String result;
                    result=bufferReader.readLine();
                    Log.i("aaa","what "+ result);
                    return  result;


                }catch (Exception e){
                    Log.i("aa","exception @!!!!!!!");
                    return null;
                }*/
                String response=" ";

                try{

                   URL url=new URL(REGISTER_URL);
                    Log.i("aaa","try b");
                   HttpURLConnection httpURLConnection=(HttpURLConnection)url.openConnection();
                   httpURLConnection.setRequestMethod("POST");
                   httpURLConnection.setDoOutput(true);
                   httpURLConnection.setDoInput(true);
                   OutputStream OS=httpURLConnection.getOutputStream();

                   BufferedWriter bufferedWriter=new BufferedWriter(new OutputStreamWriter(OS,"UTF-8"));

/*
                   String data= URLEncoder.encode("phone_no","UTF-8") +"="+URLEncoder.encode("kritika","UTF-8")+"&"+
                           URLEncoder.encode("password","UTF-8") +"="+URLEncoder.encode("kritika123","UTF-8");*/


                   bufferedWriter.write(urlSuffix);
                   /*
                    bufferedWriter.flush();
                   bufferedWriter.close();
                   OS.close();
                   InputStream IS=httpURLConnection.getInputStream();
                    Log.i("aaa","--------------");
                   BufferedReader bufferedReader=new BufferedReader(new InputStreamReader(IS,"iso-8859-1"));
                   String line=" ";

                    while((line=bufferedReader.readLine())!=null)
                   {
                       response+=line;
                   }
                   bufferedReader.close();
                   IS.close();
                   */
                   //   httpURLConnection.disconnect();
               }catch(Exception e)
                {
                    Log.i("aaa","in exceptions");
                }
                return response;



            }

        }
        /*RegisterUser ur=new RegisterUser();
        ur.execute(urlSuffix);
        Log.i("aa","before execute");
*/
    }

