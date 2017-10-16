
package com.example.zozo07.mobile;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import com.concretepage.android.R;
import android.support.v4.app.Fragment;



public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Set a toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        Button setAlarm = (Button) findViewById(R.id.setAlarm);
        setAlarm.setOnClickListener(this);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        //Setup a drawer view.
        setupDrawerContent(nvDrawer);

        //Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
    }

    @Override
    public void onClick(View v) {

    //    startActivity(new Intent(this, FragmentAlarm.class));
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
        return drawerToggle.onOptionsItemSelected(menuItem) || super.onOptionsItemSelected(menuItem);
    }

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

        switch (menuItem.getItemId()) {
            case R.id.alarms:
               startActivity(new Intent(this, AlarmActivity.class));
                break;
            case R.id.graphs:
                break;
            case R.id.sleep:
                break;
            case R.id.settings:     //Might be tricky.
                break;
        }
/*
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
        */
    }

}










