
package com.example.zozo07.mobile;

import android.content.Intent;
import android.content.res.Configuration;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.concretepage.android.R;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private TextView tvStatus;

    public static void setTvDate(TextView newDate) {
        tvDate = newDate;
    }

    private static TextView tvDate;
    public static String PACKAGE_NAME;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        //Set a toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);


        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvDate = (TextView) findViewById(R.id.tvDate);

        // checkStatus();

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        //Setup a drawer view.
        setupDrawerContent(nvDrawer);

        //Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkStatus();
    }

    private void checkStatus() {
        if (AlarmActivity.getActive()) {
            tvStatus.setText("Alarm set for " +
                    AlarmActivity.getAlarmTimePicker().getCurrentHour() + ":" +
                    AlarmActivity.getAlarmTimePicker().getCurrentMinute());
        } else {
            tvStatus.setText(R.string.notActive);
        }
        if (AlarmActivity.getYEAR() == 0) {
            tvDate.setText(R.string.no_occasion);
        } else {
        /*    tvDate.setText("Special occasion: "
                    + AlarmActivity.getYEAR() + "."
                    + AlarmActivity.getMONTH() + "." + AlarmActivity.getDAY());
                    */
        }

    }

    @Override
    public void onClick(View v) {

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

    public void selectDrawerItem(MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case R.id.alarms:
                startActivity(new Intent(this, AlarmActivity.class));
                break;
            case R.id.graphs:
                break;
            default:
                break;
        }
    }

    public void showVacationDatePickerDialog(View view) {
        DialogFragment newFragment = new VacationDatePickerFragment();
        newFragment.show(getSupportFragmentManager(), "datePicker");
    }
}










