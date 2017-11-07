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
import android.util.Log;
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
    private TimePicker alarmTimePicker;
    private static AlarmActivity inst;
    private TextView alarmTextView;
    private Context context;


    private static int YEAR = 2017;
    private static int MONTH = 12;
    private static int DAY = 25;

    public static AlarmActivity instance() {
        return inst;
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
        setContentView(R.layout.activity_my);       //super után ez a legelső!!!

        findViews();

        Date currentTime = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentTime);

        if ((int)calendar.get(Calendar.YEAR) == YEAR &&
                (int)calendar.get(Calendar.MONTH) + 1 == MONTH &&
                (int)calendar.get(Calendar.DAY_OF_MONTH) == DAY) {

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

    private void showCancelWhenEditDialog(DialogInterface.OnClickListener onDiscardButtonClickListener) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("It's vacation day! You cannot set a timer!");
        builder.setCancelable(false);
        builder.setPositiveButton("Discard", onDiscardButtonClickListener);


        AlertDialog alertDialog = builder.create();
        alertDialog.show();
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
    public void onClick(View v) {
        //AlarmService.cancelTask(context);
        //stopService(new Intent(AlarmActivity.this, AlarmReceiver.class));

        TaskDialog taskDialog = new TaskDialog(AlarmActivity.this);
        taskDialog.show();


       // AlarmReceiver.getMediaPlayer().stop();
       // finish();
    }

    public void onToggleClicked(View view) {
        if (((ToggleButton) view).isChecked()) {
            Calendar calendar = Calendar.getInstance();

            int hour, minute = 0;

            hour = alarmTimePicker.getCurrentHour();
            minute = alarmTimePicker.getCurrentMinute();

            if (Build.VERSION.SDK_INT >= 23){
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }
            else{
                calendar.set(Calendar.HOUR_OF_DAY, hour);
                calendar.set(Calendar.MINUTE, minute);
            }

            Toast.makeText(getApplicationContext(), "Alarm set for " + hour + ":" + minute, Toast.LENGTH_LONG).show();
            Intent myIntent = new Intent(AlarmActivity.this, AlarmReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(AlarmActivity.this, 0, myIntent, 0); //important!
            alarmManager.set(AlarmManager.RTC, calendar.getTimeInMillis(), pendingIntent);
        } else {
            alarmManager.cancel(pendingIntent);
            setAlarmText("");
        }
    }

    public void setAlarmText(String alarmText) {
        alarmTextView.setText(alarmText);
    }
}
