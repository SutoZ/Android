package com.example.zozo07.mobile;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RadioButton;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.ArrayList;

public class ChartActivity extends AppCompatActivity{

    private BarChart barChart;
    private RadioButton rbA, rbC, rbD;

    private BarDataSet barDataSet;
    private ArrayList<BarEntry> barEntries;
    private ArrayList<String> theTitles;
    private BarData theData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);

        barChart = (BarChart) findViewById(R.id.bargraph);
        rbA = (RadioButton) findViewById(R.id.weekDays);
        rbC = (RadioButton) findViewById(R.id.saturday);
        rbD = (RadioButton) findViewById(R.id.sunday);
        Button btnCreate = (Button) findViewById(R.id.btnCreate);



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


        btnCreate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                createBarGraph(new BarEntry(50f, 0), new BarEntry(100f, 1), new BarEntry(20f, 2), barEntries, theTitles, barDataSet, theData);
            }
        });

        registerButtons();

    }

    private void createBarGraph(BarEntry e, BarEntry e2, BarEntry e3, ArrayList<BarEntry> barEntries, ArrayList<String> theTitles, BarDataSet barDataSet, BarData theData) {
        barEntries = new ArrayList<>();
        barEntries.add(e);
        barEntries.add(e2);
        barEntries.add(e3);

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
        createBarGraph(new BarEntry(30f, 0), new BarEntry(60f, 1), new BarEntry(100f, 2), barEntries, theTitles, barDataSet, theData);
    }

    private void saturday() {
        createBarGraph(new BarEntry(20f, 0), new BarEntry(50f, 1), new BarEntry(90f, 2), barEntries, theTitles, barDataSet, theData);
    }

    private void weekDays() {
        createBarGraph(new BarEntry(80f, 0), new BarEntry(70f, 1), new BarEntry(20f, 2), barEntries, theTitles, barDataSet, theData);
    }

    protected void killBarChart() {
        barEntries = null;
        barDataSet = null;
        theTitles = null;
    }
}
