
package com.example.zozo07.mobile;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.PersistableBundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.DialogFragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.concretepage.android.R;

import java.util.Objects;


public class MainActivity extends FragmentActivity implements View.OnClickListener {

    private DrawerLayout mDrawer;
    private Toolbar toolbar;
    private ActionBarDrawerToggle drawerToggle;
    private TextView tvStatus;

    private TextView tvDate;        //without static
    public static String PACKAGE_NAME;
    public static final String SPECIAL_OCCASION = "special_occasion";
    public static final String ALARM_STATUS = "alarm_status";

/*
    private Intent vacationDatePickerIntent;
    private Intent alarmActivityIntent;
*/

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PACKAGE_NAME = getApplicationContext().getPackageName();
        //Set a toolbar to replace the ActionBar.
        toolbar = (Toolbar) findViewById(R.id.toolbar);

        tvStatus = (TextView) findViewById(R.id.tvStatus);
        tvDate = (TextView) findViewById(R.id.tvDate);

        mDrawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerToggle = setupDrawerToggle();
        NavigationView nvDrawer = (NavigationView) findViewById(R.id.nvView);
        //Setup a drawer view.
        setupDrawerContent(nvDrawer);

        //Tie DrawerLayout events to the ActionBarToggle
        mDrawer.addDrawerListener(drawerToggle);

        Button btnSetDate = (Button) findViewById(R.id.btnSetDate);
        btnSetDate.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v) {
                DialogFragment newFragment = new VacationDatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "datePicker");
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putString(ALARM_STATUS, tvStatus.getText().toString());
        savedInstanceState.putString(SPECIAL_OCCASION, tvDate.getText().toString());
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        super.onResume();

        checkStatus();
    }

    private void checkStatus() {
        tvStatus.setText(R.string.notActive);
        Intent myDataIntent = getIntent();
        Bundle extras = myDataIntent.getExtras();
        //String currentIntentSource = currentIntent.getExtras().getString("activity");
        //String currentIntentSource = currentIntent.getStringExtra("activity");

        /*
        if (Objects.equals(currentIntentSource, "AlarmActivity")) {
            alarmActivityIntent = getIntent();
        } else if (Objects.equals(currentIntentSource, "DatePickerActivity")) {
            vacationDatePickerIntent = getIntent();
        }
*/
        if (extras != null) {
            if (myDataIntent.getExtras().getBoolean("active")) {
                String time = "Alarm set for " +
                        AlarmActivity.getCalendarHour() + ":" +
                        //AlarmActivity.getAlarmTimePicker().getCurrentMinute();
                        AlarmActivity.getCalendarMinute();
                tvStatus.setText(time);
            }
        }
        if (AlarmActivity.getYEAR() == 0) {
            tvDate.setText(R.string.no_occasion);
        } else {
            if (myDataIntent.getExtras() != null) {
                tvDate.setText(myDataIntent.getExtras().getString("occasion"));
            }
            else tvDate.setText(R.string.vacation_day);
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
                startActivity(new Intent(this, ChartActivity.class));
                break;
            default:
                break;
        }
    }
}










