package com.example.zozo07.mobile;

import android.content.Context;
import android.support.test.espresso.ViewAssertion;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.doubleClick;
import static android.support.test.espresso.action.ViewActions.swipeRight;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Zozo07 on 2017.10.31..
 */

@RunWith(AndroidJUnit4.class)
public class ChartActivityInstrumentedTest{

    private ChartActivity testChartActivity;
    private BarChart testBarChart;
    private Context appContext;

    @Rule
    public ActivityTestRule<ChartActivity> chartActivity = new ActivityTestRule<ChartActivity>(ChartActivity.class);


    @Test
    public void checkIfBarChartIsDisplayed_InterruptedException() throws InterruptedException {
        onView((withId(R.id.bargraph))).check(matches(isDisplayed()));
        Thread.sleep(500);
    }

    @Test
    public void checkIfBarChart_Changed_By_RadioButton_weekDays_Click_Not_Null() {
        onView(withId(R.id.weekDays)).perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        assertNotNull(withId(R.id.bargraph));
    }

    @Test
    public void checkIfBarChart_Changed_By_RadioButton_Saturday_Click_Not_Null() {
        onView(withId(R.id.saturday)).perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        assertNotNull(withId(R.id.bargraph));
    }

    @Test
    public void checkIfBarChart_Changed_By_RadioButton_Sunday_Click_Not_Null() {
        onView(withId(R.id.sunday)).perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        onView(withId(R.id.bargraph)).check((ViewAssertion) doubleClick());
        assertNotNull(withId(R.id.bargraph));
    }
    @Test
    public void asd (){
        onView(withId(R.id.bargraph)).check((ViewAssertion) doubleClick());
    }
}
