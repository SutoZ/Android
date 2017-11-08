package com.example.zozo07.mobile;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.widget.DatePicker;
import android.widget.TimePicker;

import java.util.Calendar;

/**
 * Created by Zolee on 2017.11.08..
 */

public class VacationDatePickerFragment extends DialogFragment implements
        DatePickerDialog.OnDateSetListener{



    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it
        return new DatePickerDialog(getActivity(), this, year, month, day);
    }


    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        AlarmActivity.setYEAR(year);
        AlarmActivity.setMONTH(month + 1);      //Be Careful!!
        AlarmActivity.setDAY(dayOfMonth);
    }
}
