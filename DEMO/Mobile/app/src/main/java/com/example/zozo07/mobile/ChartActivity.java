package com.example.zozo07.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity {

    private BarChart barChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        barChart = (BarChart) findViewById(R.id.bargraph);

        ArrayList<BarEntry> barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(50f, 0));
        barEntries.add(new BarEntry(100f, 1));
        barEntries.add(new BarEntry(20f, 2));

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
