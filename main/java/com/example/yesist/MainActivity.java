package com.example.yesist;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.ui.AppBarConfiguration;

import android.content.Context;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.FrameLayout;

import com.example.yesist.Frames.HomeFragment;
import com.example.yesist.Frames.InfoFragment;
import com.example.yesist.Frames.MoreFragment;
import com.example.yesist.Frames.NavFragment;
import com.example.yesist.Frames.StatsFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static Context mainContext;

    BottomNavigationView bottomNavigationView;
    FrameLayout frameLayout;
    Toolbar toolbar;

    public  static MainActivity mainActivity;


    ArrayList<Integer> queue;
    Fragment homeFragment,infoFragment,moreFragment,navFragment,statsFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);

        bottomNavigationView=findViewById(R.id.bottomNavigationView);
        frameLayout=findViewById(R.id.home_frame);
        homeFragment = new HomeFragment();
        infoFragment = new InfoFragment();
        moreFragment = new MoreFragment();
        navFragment = new NavFragment();
        statsFragment = new StatsFragment();

        queue=new ArrayList<Integer>();
        queue.add(R.id.bottomnav_home);
        queue.add(R.id.bottomnav_home);

        mainActivity=this;


        mainContext=this;
        bottomNavigationView.setSelectedItemId(R.id.bottomnav_home);
        loadFragment(homeFragment);

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                switch(menuItem.getItemId())
                {

                    case R.id.bottomnav_home:
                        queue.add(R.id.bottomnav_home);
                        loadFragment(homeFragment);
                        return true;
                    case R.id.bottomnav_info:
                        queue.add(R.id.bottomnav_info);
                        loadFragment(infoFragment);
                        return true;
                    case R.id.bottomnav_more:
                        queue.add(R.id.bottomnav_more);
                        loadFragment(moreFragment);
                        return true;
                    case R.id.bottomnav_nav:
                        queue.add(R.id.bottomnav_nav);
                        loadFragment(navFragment);
                        return true;
                    case R.id.bottomnav_stats:
                        queue.add(R.id.bottomnav_stats);
                        loadFragment(statsFragment);
                        return true;
                }
                return false;
            }
        });


    }
    public void setSupportActionBar(Toolbar toolbar) {
    }

    private void loadFragment(Fragment fragment) {
        FragmentManager fragmentManager=getSupportFragmentManager();
        FragmentTransaction transaction=fragmentManager.beginTransaction();
        transaction.replace(R.id.home_frame,fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
    @Override
    public void onBackPressed() {
        int count = getFragmentManager().getBackStackEntryCount();
        if (count == 0) {
            super.onBackPressed();
            //additional code
            if(queue.size()!=0)
            {

                queue.remove(queue.size()-1);

                if(queue.size()==0)
                {
                    finish();
                }
                else {
                    int top = queue.get(queue.size() - 1);
                    bottomNavigationView.setSelectedItemId(top);
                    queue.remove(queue.size() - 1);

                }

            }

        } else {
            getFragmentManager().popBackStack();
        }
    }
}