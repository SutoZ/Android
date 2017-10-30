package com.example.zozo07.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;

/**
 * Created by Zozo07 on 2017.10.30..
 */

public class ChartActivity extends Activity{

    private BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.chart_activity);
    }
}
