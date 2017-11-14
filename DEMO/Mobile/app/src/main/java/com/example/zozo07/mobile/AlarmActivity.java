package com.example.zozo07.mobile;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.concretepage.android.R;

import java.util.Calendar;
import java.util.Date;


public class AlarmActivity extends Activity  implements View.OnClickListener{

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private static TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    private Context context;
    private static boolean active = false;
    private static Calendar vacationCalendar;
    private Intent myIntent;

    public static final int MAIN_ACTIVITY_RESULT_CODE = 1;

    public static TimePicker getAlarmTimePicker() {return alarmTimePicker; }

    public static boolean setActive(boolean newValue) {return active = newValue; }


    private static int YEAR;
    private static int MONTH;
    private static int DAY;

    public static int getYEAR() { return YEAR; }

    public static int getMONTH() { return MONTH; }

    public static int getDAY() { return DAY; }

    public static AlarmActivity instance() {
        return inst;
    }

    public static void setMONTH(int MONTH) {
        AlarmActivity.MONTH = MONTH;
    }

    public static void setDAY(int DAY) {
        AlarmActivity.DAY = DAY;
    }

    public static void setYEAR(int YEAR) {
        AlarmActivity.YEAR = YEAR;
    }


    @Override
    public void onStart() {
        super.onStart();
        inst = this;
        context = getApplicationContext();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my);       //Must be the first line after super!

        findViews();

        Date currentTime = new Date();
        vacationCalendar = Calendar.getInstance();
        vacationCalendar.setTime(currentTime);

        if ((int) vacationCalendar.get(Calendar.YEAR) == YEAR &&
                (int) vacationCalendar.get(Calendar.MONTH) + 1 == MONTH &&
                (int) vacationCalendar.get(Calendar.DAY_OF_MONTH) == DAY) {

            DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // User clicked "Discard" button, close the current activity.
                    NavUtils.navigateUpFromSameTask(AlarmActivity.this);
                    finish();
                }
            };

            showCancelWhenEditDialog(discardButtonClickListener);
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if ((int) vacationCalendar.get(Calendar.YEAR) == YEAR &&
                (int) vacationCalendar.get(Calendar.MONTH) + 1 == MONTH &&
                (int) vacationCalendar.get(Calendar.DAY_OF_MONTH) == DAY) {

            DialogInterface.OnClickListener discardButtonClickListener = new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    // User clicked "Discard" button, close the current activity.
                    finish();
                }
            };

            // Show dialog that there are unsaved changes
            showCancelWhenEditDialog(discardButtonClickListener);
        }
    }

    private void showCancelWhenEditDialog(DialogInterface.OnClickListener onDiscardButtonClickListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("It's vacation day! You cannot set a timer!");
        builder.setCancelable(false);
        builder.setPositiveButton("Discard", onDiscardButtonClickListener);

        active = false;

        AlertDialog alertDialog = builder.create();
        alertDialog.show();
        myIntent = new Intent(getBaseContext(), MainActivity.class);
        myIntent.putExtra("active", active);
        myIntent.putExtra("activity", "AlarmActivity");
     startActivityForResult(myIntent, MAIN_ACTIVITY_RESULT_CODE);

    }

    private void findViews() {
        alarmTimePicker = (TimePicker) findViewById(R.id.alarmTimePicker);
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        ToggleButton alarmToggle = (ToggleButton) findViewById(R.id.alarmToggle);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {       //btnStop!!
        TaskDialogActivity taskDialogActivity = new TaskDialogActivity(AlarmActivity.this);
        taskDialogActivity.show();
    }

    public void onToggleClicked(View view) {        //START!!
        if (((ToggleButton) view).isChecked()) {
            vacationCalendar = Calendar.getInstance();
            int hour, minute = 0;

            hour = alarmTimePicker.getCurrentHour();
            minute = alarmTimePicker.getCurrentMinute();

            if (Build.VERSION.SDK_INT >= 23){
                vacationCalendar.set(Calendar.HOUR_OF_DAY, hour);
                vacationCalendar.set(Calendar.MINUTE, minute);
            }
            else{
                vacationCalendar.set(Calendar.HOUR_OF_DAY, hour);
                vacationCalendar.set(Calendar.MINUTE, minute);
            }

            active = true;


            Toast.makeText(getApplicationContext(), "Alarm set for " + hour + ":" + minute, Toast.LENGTH_LONG).show();
            myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0); //important!
            alarmManager.set(AlarmManager.RTC, vacationCalendar.getTimeInMillis(), pendingIntent);

            myIntent = new Intent(AlarmActivity.this, MainActivity.class);
            myIntent.putExtra("active", active);
            myIntent.putExtra("activity", "AlarmActivity");
            startActivity(myIntent);

        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }
}
