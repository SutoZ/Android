package com.example.zozo07.mobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.concretepage.android.R;


/**
 * Created by Zozo07 on 2017.08.22..
 */

public class AlarmPtest extends Activity implements  android.view.View.OnClickListener {
    /**
     * Called when the activity is first created.
     */

    Button SetAlarm, SetTimer;
    int hours = 1, min = 30;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Vars();

    }


    private void AlarmClock() {
        // TODO Auto-generated method stub
        Intent i = new Intent(android.provider.AlarmClock.ACTION_SET_ALARM);
        i.putExtra(android.provider.AlarmClock.EXTRA_HOUR, hours);
        i.putExtra(android.provider.AlarmClock.EXTRA_MINUTES, min);
        startActivity(i);

    }


    private void Vars() {
        // TODO Auto-generated method stub
        SetAlarm = (Button) findViewById(R.id.SetAlarm);
        SetTimer = (Button) findViewById(R.id.TimerAlarm);

        SetAlarm.setOnClickListener(this);
        SetTimer.setOnClickListener(this);
    }


    public void onClick(View v) {
        // TODO Auto-generated method stub
        switch (v.getId()) {
            case R.id.SetAlarm:
                Toast alarmToast = Toast.makeText(this, "you click alarm", Toast.LENGTH_LONG);
                alarmToast.show();
                AlarmClock();
            case R.id.TimerAlarm:
                Toast TimerToast = Toast.makeText(this, "you get 15 min", Toast.LENGTH_LONG);
                TimerToast.show();
                hours = 0;
                min = 15;
                AlarmClock();
        }
    }
}