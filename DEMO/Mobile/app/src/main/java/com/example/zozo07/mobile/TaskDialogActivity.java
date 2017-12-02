package com.example.zozo07.mobile;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.concretepage.android.R;

import java.util.Random;

/**
 * Created by Zozo07 on 2017.11.07..
 */

class TaskDialogActivity extends Dialog implements android.view.View.OnClickListener {

    private Activity activity;
    private EditText etSolution;

    private static int num1 = 0;
    private static int num2 = 0;

    static int getSign() {
        return sign;
    }

    private static int sign;
    private int solution = 0;

    static int getNum1() {
        return num1;
    }

    static int getNum2() {
        return num2;
    }


    TaskDialogActivity(Activity activity) {
        super(activity);

        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        Button ok = (Button) findViewById(R.id.btnOK);
        Button cancel = (Button) findViewById(R.id.btnCancel);
        TextView tvTask = (TextView) findViewById(R.id.tvToDo);
        etSolution = (EditText) findViewById(R.id.etSolution);

        Random r = new Random();
        int interval = 11;
        num1 = r.nextInt(interval);
        num2 = r.nextInt(interval);

        sign = r.nextInt(3);

        switch (sign){
            case 0:
                tvTask.setText(num1 + "+" + num2 + " ?");
                break;
            case 1:
                tvTask.setText(num1 + "-" + num2 + " ?");
                break;
            case 2:
                tvTask.setText(num1 + "*" + num2 + " ?");
        }
        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private int doTheMath(int num1, int num2, int sign) {

        solution = 0;
        switch (sign){
            case 0:
                solution = num1 + num2;
                break;
            case 1:
                solution = num1 - num2;
                break;
            case 2:
                solution = num1 * num2;
        }
        return solution;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnOK:

                solution = doTheMath(num1, num2, sign);

                if (solution == Integer.parseInt(etSolution.getText().toString())) {
                    AlarmActivity.setActive(false);
                    AlarmReceiver.getMediaPlayer().stop();
                    activity.finish();
                }
            case R.id.btnCancel:
                dismiss();
        }
    }
}
