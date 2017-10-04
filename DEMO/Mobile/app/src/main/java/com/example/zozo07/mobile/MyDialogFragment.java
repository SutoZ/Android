package com.example.zozo07.mobile;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.DialogFragment;
import android.widget.TimePicker;

/**
 * Created by Zolee on 2017.10.04..
 */

public class MyDialogFragment extends DialogFragment {

    private int timeHour;
    private int timeMinute;
    private Handler handler;

    //default constructor

    public MyDialogFragment(){

    }

    public MyDialogFragment(Handler handler){
        this.handler = handler;
    }
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Bundle bundle = getArguments();
        timeHour = bundle.getInt(MyConstans.MyConstants.HOUR);
        timeMinute = bundle.getInt(MyConstans.MyConstants.MINUTE);
        TimePickerDialog.OnTimeSetListener listener = new TimePickerDialog.OnTimeSetListener() {
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                timeHour = hourOfDay;
                timeMinute = minute;
                Bundle b = new Bundle();
                b.putInt(MyConstans.MyConstants.HOUR, timeHour);
                b.putInt(MyConstans.MyConstants.MINUTE, timeMinute);
                Message msg = new Message();
                msg.setData(b);
                handler.sendMessage(msg);
            }
        };
        return new TimePickerDialog(getActivity(), listener, timeHour, timeMinute, false);
    }

}
