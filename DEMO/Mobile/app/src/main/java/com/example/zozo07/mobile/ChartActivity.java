package com.example.zozo07.mobile;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity{

    private BarChart barChart;
    private BarDataSet barDataSet;
    private ArrayList<BarEntry> barEntries;
    private ArrayList<String> theTitles;
    private BarData theData;
    private TextView tvAlarm, tvSleep, tvVacation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        barChart = (BarChart) findViewById(R.id.bargraph);
        tvAlarm = (TextView) findViewById(R.id.tvAlarm);
        tvSleep = (TextView) findViewById(R.id.tvSleep);
        tvVacation = (TextView) findViewById(R.id.tvVacation);


        barEntries = new ArrayList<>();
        barEntries.add(new BarEntry(50, 0));
        barEntries.add(new BarEntry(80, 1));
        barEntries.add(new BarEntry(100, 2));

        barDataSet = new BarDataSet(barEntries, "Data");

        theTitles = new ArrayList<>();
        theTitles.add("Alarm");
        theTitles.add("Sleep");
        theTitles.add("Vacation");


        theData = new BarData(theTitles, barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);

        registerButtons();
    }


    private void createBarGraph(BarEntry data, BarEntry sleep, BarEntry vacation, ArrayList<BarEntry> barEntries, ArrayList<String> theTitles, BarDataSet barDataSet, BarData theData, int[] colors) {
        barEntries = new ArrayList<>();
        barEntries.add(data);
        barEntries.add(sleep);
        barEntries.add(vacation);

        barDataSet = new BarDataSet(barEntries, "Data");

        theTitles = new ArrayList<>();
        theTitles.add("Alarm");
        theTitles.add("Sleep");
        theTitles.add("Vacation");

        barDataSet.setColors(colors);

        theData = new BarData(theTitles, barDataSet);
        barChart.setData(theData);

        barChart.setTouchEnabled(true);
        barChart.setDragEnabled(true);
        barChart.setScaleEnabled(true);
    }

    public void registerButtons() {
        register(R.id.weekDays);
        register(R.id.saturday);
        register(R.id.sunday);
    }

    private void register(int buttonResourceId){
        findViewById(buttonResourceId).setOnClickListener(buttonClickListener);
    }

    private OnClickListener buttonClickListener = new OnClickListener() {

        @Override
        public void onClick(View v){
            switch (v.getId()) {
                case R.id.weekDays:
                    killBarChart();
                    weekDays();
                    break;
                case R.id.saturday:
                    killBarChart();
                    saturday();
                    break;
                case R.id.sunday:
                    killBarChart();
                    sunday();
                    break;
                default:
                    break;
            }
        }
    };

    private void sunday() {
        tvAlarm.setText("30");
        tvSleep.setText("60");
        tvVacation.setText("100");
        createBarGraph(new BarEntry(30f, 0), new BarEntry(60f, 1), new BarEntry(100f, 2), barEntries, theTitles, barDataSet, theData, new int[] {Color.RED, Color.GREEN, Color.BLUE});
    }

    private void saturday() {
        tvAlarm.setText("20");
        tvSleep.setText("50");
        tvVacation.setText("90");
        createBarGraph(new BarEntry(20f, 0), new BarEntry(50f, 1), new BarEntry(90f, 2), barEntries, theTitles, barDataSet, theData, new int[] {Color.GREEN, Color.BLUE, Color.RED});
    }

    private void weekDays() {
        tvAlarm.setText("80");
        tvSleep.setText("70");
        tvVacation.setText("20");
        createBarGraph(new BarEntry(80f, 0), new BarEntry(70f, 1), new BarEntry(20f, 2), barEntries, theTitles, barDataSet, theData, new int[] {Color.BLUE, Color.YELLOW, Color.MAGENTA});
    }

    protected void killBarChart() {
        barEntries = null;
        barDataSet = null;
        theTitles = null;
    }
}
