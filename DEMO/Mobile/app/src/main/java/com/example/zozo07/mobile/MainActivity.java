
package com.example.zozo07.mobile;

import android.app.FragmentManager;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentActivity;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.app.FragmentManager;
import com.concretepage.android.R;
import android.app.Fragment;
import Components.FragmentAlarm;

//import Components.FragmentAlarm;

//import Components.FragmentAlarm;

public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private NavigationView nvDrawer;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set a toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button setAlarm = (Button) findViewById(R.id.setAlarm);
        setAlarm.setOnClickListener(this);
        //Set a Toolbar to act as the ActionBar for this Activity window.

        //NEEDED At all???
    //    setSupportActionBar(toolbar);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        nvDrawer = (NavigationView) findViewById(R.id.nvView);
        //Setup a drawer view.
        setupDrawerContent(nvDrawer);

        //Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
    }


    @Override
    public void onClick(View v) {

        FragmentManager fm = getFragmentManager();
        Fragment frAlarm = fm.findFragmentByTag(FragmentAlarm.TAG);
        if (frAlarm == null) {
            frAlarm = new Fragment();
            fm.beginTransaction()
                    .replace(R.id.fragment_container, frAlarm, FragmentAlarm.TAG)
                    //.addToBackStack(null);  // uncomment this line if you want to be able to return to the prev. fragment with "back" button
                    .commit();
        }
    }


    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        //Sync the toggle state after onRestoreInstanceState has occured.
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        //Pass any configurationchange to the drawer toggle.
        drawerToggle.onConfigurationChanged(newConfig);
    }

    private ActionBarDrawerToggle setupDrawerToggle() {
        // NOTE: Make sure you pass in a valid toolbar reference.
        return new ActionBarDrawerToggle(this, mDrawer, toolbar, R.string.drawer_open, R.string.drawer_close);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem menuItem) {
        if (drawerToggle.onOptionsItemSelected(menuItem)){
            return  true;
        }
        return super.onOptionsItemSelected(menuItem);
    }

    //Setup a handler to respond to click events on the navigation elements and swap out the fragment.


    /*
    private void setupDrawerContent(NavigationView navigationView) {

        //MIGHT BE BAD!!!
navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) navigationView);
        //navigationView.setNavigationItemSelectedListener((NavigationView.OnNavigationItemSelectedListener) this);
                //new NavigationView.onNavigationItemSelectedListener(this);
        //Might be completey unnesecary.
        //View headerLayout = navigationView.getHeaderView(0);
    }
*/

    private void setupDrawerContent(NavigationView navigationView) {
        navigationView.setNavigationItemSelectedListener(
                new NavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(MenuItem menuItem) {
                        selectDrawerItem(menuItem);
                        return true;
                    }
                });
    }

    public boolean onNavigationItemSelected(MenuItem menuItem) {
        selectDrawerItem(menuItem);
        return true;
    }

    public void selectDrawerItem(MenuItem menuItem) {
        //Create a new fragment and specify the fragment to show based on nav item clicked.
        Fragment fragment = null;
        Class fragmentclass;
        switch (menuItem.getItemId()) {
            case R.id.calendar:
                fragmentclass = CalendarFragment.class;
                break;
            case R.id.graphs:
                fragmentclass = GraphFragment.class;
                break;
            case R.id.sleep:
                fragmentclass = SleepFragment.class;
                break;
            case R.id.settings:     //Might be tricky.
                fragmentclass = SettingsFragment.class;
                break;
        }

        try {
            //Insert a fragment by replacing any existing fragment.
            FragmentManager fragmentManager = getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.flContent, fragment).commit();

            // Highlight the selected item has been done by NavigationView
            menuItem.setChecked(true);
            //Set action bar title.
            setTitle(menuItem.getTitle());

            //Close the drawer.
            mDrawer.closeDrawers();


        } catch (Exception e) {
            e.printStackTrace();    //write error to console.
        }
    }
}










