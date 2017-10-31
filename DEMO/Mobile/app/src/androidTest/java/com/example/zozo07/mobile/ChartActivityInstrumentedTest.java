package com.example.zozo07.mobile;

import android.app.Instrumentation;
import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.test.ActivityInstrumentationTestCase2;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by Zozo07 on 2017.10.31..
 */

@RunWith(AndroidJUnit4.class)
public class ChartActivityInstrumentedTest extends ActivityInstrumentationTestCase2<ChartActivity> {

    private ChartActivity testChartActivity;
    private BarChart testBarChart;
    private Instrumentation appContext;

    public ChartActivityInstrumentedTest() {
        super(ChartActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        appContext = InstrumentationRegistry.getInstrumentation();
       // injectInsrumentation(InstrumentationRegistry.getInstrumentation());
        testChartActivity = getActivity();
        testBarChart = (BarChart) testChartActivity.findViewById(R.id.bargraph);


    }

    @Test
    public void barChart_NotNull() {
        BarData barData = testBarChart.getBarData();
        assertNotNull(barData);
    }
}
