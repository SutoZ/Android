package com.example.zozo07.mobile;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.app.Fragment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.concretepage.android.R;
import com.example.zozo07.mobile.AlarmReceiver;
import com.example.zozo07.mobile.MyConstans;
import com.example.zozo07.mobile.MyDialogFragment;

import java.util.Calendar;

/**
 * Created by Zozo07 on 2017.09.15..
 */

//
public class FragmentAlarm extends FragmentActivity{

    private static int timeHour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY);
    private static int timeMinute = Calendar.getInstance().get(Calendar.MINUTE);
    public static final String TAG = "FragmentAlarm";
    TextView textView1;
    private TextView textView2;

    public TextView getTextView2() {
        return textView2;
    }

    AlarmManager alarmManager;
    private PendingIntent pendingIntent;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_alarm);
        textView1 = (TextView) findViewById(R.id.msg1);
        textView1.setText(timeHour + ":" + timeMinute);
      //  textView2 = (TextView) findViewById(R.id.msg2);

        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent myIntent = new Intent(this, AlarmReceiver.class);
        pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);

        View.OnClickListener listener1 = new View.OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                Bundle bundle = new Bundle();
                bundle.putInt(MyConstans.MyConstants.HOUR, timeHour);
                bundle.putInt(MyConstans.MyConstants.MINUTE, timeMinute);
                MyDialogFragment fragment = new MyDialogFragment(new MyHandler());
                fragment.setArguments(bundle);
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.add(fragment, MyConstans.MyConstants.TIME_PICKER);
                transaction.commit();
            }
        };

        Button btnStart = (Button) findViewById(R.id.start);
        btnStart.setOnClickListener(listener1);
        View.OnClickListener listener2 = new View.OnClickListener() {
            public void onClick(View view) {
                textView2.setText("");
                cancelAlarm();
            }
        };
        Button btnStop = (Button) findViewById(R.id.stop);
        btnStop.setOnClickListener(listener2);
    }

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            timeHour = bundle.getInt(MyConstans.MyConstants.HOUR);
            timeMinute = bundle.getInt(MyConstans.MyConstants.MINUTE);
            textView1.setText(timeHour + ":" + timeMinute);
            setAlarm();
        }
    }

    private void setAlarm() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, timeHour);
        calendar.set(Calendar.MINUTE, timeMinute);
        alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
    }

    private void cancelAlarm() {
        if (alarmManager != null) {
            alarmManager.cancel(pendingIntent);
        }
    }

}
