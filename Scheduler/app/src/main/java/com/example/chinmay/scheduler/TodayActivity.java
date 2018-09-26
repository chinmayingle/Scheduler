package com.example.chinmay.scheduler;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Toast;

import it.neokree.materialtabs.MaterialTab;
import it.neokree.materialtabs.MaterialTabHost;
import it.neokree.materialtabs.MaterialTabListener;

public class TodayActivity extends ActionBarActivity implements MaterialTabListener  {


    MaterialTabHost tabHost;
    ViewPager viewPager;
    ViewPagerAdapter androidAdapter;
    //Toolbar toolBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_today);

        //android toolbar
        Log.i("here","here");
        // toolBar = (android.support.v7.widget.Toolbar) this.findViewById(R.id.toolBar);
        //this.setSupportActionBar(toolBar);
        //this.setwindowActionBar(false);
        Log.i("here","here1");
        //tab host
        tabHost = (MaterialTabHost) this.findViewById(R.id.tabHost);
        viewPager = (ViewPager) this.findViewById(R.id.viewPager);
        Log.i("here","here2");

        //adapter view
        androidAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(androidAdapter);
        viewPager.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageSelected(int tabposition) {
                tabHost.setSelectedNavigationItem(tabposition);
                Log.i("here","here3");

            }
        });

        //for tab position
        for (int i = 1; i <= androidAdapter.getCount(); i++) {
            tabHost.addTab(
                    tabHost.newTab()
                            .setText(androidAdapter.getPageTitle(i))
                            .setTabListener(this)
            );
        }
    }

    //tab on selected
    @Override
    public void onTabSelected(MaterialTab materialTab) {

        viewPager.setCurrentItem(materialTab.getPosition());
    }

    //tab on reselected
    @Override
    public void onTabReselected(MaterialTab materialTab) {

    }

    //tab on unselected
    @Override
    public void onTabUnselected(MaterialTab materialTab) {

    }

    // view pager adapter
    private class ViewPagerAdapter extends FragmentStatePagerAdapter {

        public ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }


        //fragment class used here!!!
        public Fragment getItem(int num) {
            switch (num)
            {
                case 0 :
                {
                    return new AndroidFragment();
                }
                case 1:
                {
                    //Toast.makeText(TodayActivity.this,"2",Toast.LENGTH_LONG).show();
                    return new AndroidFragment2();
                }
                case 2:
                {
                    return new AndroidFragment3();
                }
            }

            return new AndroidFragment();
        }

        @Override
        public int getCount() {
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int tabposition) {
            switch(tabposition)
            {
                case 1 :
                {

                    return "exam";

                }
                case 2:
                {

                    return "classes";

                }
                case 3:
                {
                    return "to-do";
                }
            }
            return "null";
        }
    }


}
