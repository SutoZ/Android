package com.example.zozo07.mobile;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.concretepage.android.R;

import java.util.Calendar;
import java.util.Date;


public class AlarmActivity extends Activity implements View.OnClickListener {

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    private static AlarmActivity inst;     //without static
    private TextView alarmTextView;
    private static boolean active = false;
    private static Calendar vacationCalendar;

    private TextView displayTime;

    public static int getCalendarHour() {
        return calendarHour;
    }

    public static int getCalendarMinute() {
        return calendarMinute;
    }

    private static int calendarHour;
    private static int calendarMinute;
    private String format;
    private Calendar timePickerCalendar;
    private TimePickerDialog timepickerdialog;


   // public static final int MAIN_ACTIVITY_RESULT_CODE = 1;

//    public static TimePicker getAlarmTimePicker() {return alarmTimePicker; }

    public static boolean setActive(boolean newValue) {
        return active = newValue;
    }


    private static int YEAR;
    private static int MONTH;
    private static int DAY;

    public static int getYEAR() {
        return YEAR;
    }

    public static int getMONTH() {
        return MONTH;
    }

    public static int getDAY() {
        return DAY;
    }

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
        Context context = getApplicationContext();
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
                    finish();
                }
            };
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
    }

    private void findViews() {
        alarmTextView = (TextView) findViewById(R.id.alarmText);
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Button btnStop = (Button) findViewById(R.id.btnStop);
        btnStop.setOnClickListener(this);

        displayTime = (TextView) findViewById(R.id.tvTime);


        Button accessTime = (Button) findViewById(R.id.btnOpenAlarm);
        accessTime.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                timePickerCalendar = Calendar.getInstance();
                calendarHour = timePickerCalendar.get(Calendar.HOUR_OF_DAY);
                calendarMinute = timePickerCalendar.get(Calendar.MINUTE);

                timepickerdialog = new TimePickerDialog(AlarmActivity.this,
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,
                                                  int minute) {
                                if (hourOfDay == 0) {
                                    hourOfDay += 12;        //might have to comment out
                                    format = "AM";
                                } else if (hourOfDay == 12) {
                                    format = "PM";
                                } else if (hourOfDay > 12) {
                                    //    hourOfDay -= 12;
                                    format = "PM";
                                } else {
                                    format = "AM";
                                }
                                displayTime.setText(hourOfDay + ":" + minute + format);
                                calendarHour = hourOfDay;
                                calendarMinute = minute;
                            }
                        }, calendarHour, calendarMinute, true);
                timepickerdialog.show();
            }
        });
    }

    @Override
    public void onClick(View v) {
        TaskDialogActivity taskDialogActivity = new TaskDialogActivity(AlarmActivity.this);
        taskDialogActivity.show();
    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            //vacationCalendar = Calendar.getInstance();
            timePickerCalendar = Calendar.getInstance();
            int hour = 0, minute = 0;

            hour = calendarHour;
            minute = calendarMinute;

            timePickerCalendar.set(Calendar.HOUR_OF_DAY, hour);
            timePickerCalendar.set(Calendar.MINUTE, minute);

            active = true;

            Toast.makeText(getApplicationContext(), "Alarm set for " + hour + ":" + minute, Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0); //important!
            alarmManager.set(AlarmManager.RTC, timePickerCalendar.getTimeInMillis(), pendingIntent);


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
