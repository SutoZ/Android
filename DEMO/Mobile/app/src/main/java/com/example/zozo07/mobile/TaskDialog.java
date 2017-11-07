package com.example.zozo07.mobile;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.concretepage.android.R;

/**
 * Created by Zozo07 on 2017.11.07..
 */

public class TaskDialog extends Dialog implements android.view.View.OnClickListener {

    private Activity activity;
    private Dialog d;
    private Button ok, cancel;
    private TextView tvTask;
    private EditText etSolution;
    int num1 = 5;
    int num2 = 6;


    public TaskDialog(Activity activity) {
        super(activity);

        this.activity = activity;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog);

        ok = (Button) findViewById(R.id.btnOK);
        cancel = (Button) findViewById(R.id.btnCancel);
        tvTask = (TextView) findViewById(R.id.tvTask);
        etSolution = (EditText) findViewById(R.id.etSolution);


        tvTask.setText(num1 + "+" + num2 + " ?");

        ok.setOnClickListener(this);
        cancel.setOnClickListener(this);
    }

    private int doTheMath(int num1, int num2){
        return num1 + num2;
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btnOK:
                if (doTheMath(num1, num2) == Integer.parseInt(etSolution.getText().toString())) {
                    AlarmReceiver.getMediaPlayer().stop();
                    activity.finish();
                }
            case R.id.btnCancel:
                dismiss();
        }
    }
}
