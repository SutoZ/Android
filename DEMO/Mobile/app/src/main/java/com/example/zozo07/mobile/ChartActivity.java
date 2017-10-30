package com.example.zozo07.mobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

/**
 * Created by Zozo07 on 2017.10.30..
 */

public class ChartActivity extends Activity{

    private BarChart barChart;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.id.chart_activity);

        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(50f, 0));
        barEntries.add(new BarEntry(100f, 0));
        barEntries.add(new BarEntry(20f, 0));

        BarDataSet barDataSet = new BarDataSet(barEntries, "Data");

        ArrayList<String> theTitles = new ArrayList<>();
        theTitles.add("Alarm");
        theTitles.add("Sleep");
        theTitles.add("Vacation");

        BarData theData = new BarData(theTitles, barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }
}
