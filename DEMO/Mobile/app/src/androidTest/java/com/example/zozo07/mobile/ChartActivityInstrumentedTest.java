package com.example.zozo07.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
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
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Zozo07 on 2017.10.31..
 */

@RunWith(AndroidJUnit4.class)
public class ChartActivityInstrumentedTest{

    private ChartActivity testChartActivity;
    private BarChart testBarChart;
    private Context appContext;
/*
    @Rule
    public ActivityTestRule<ChartActivity> chartActivity = new ActivityTestRule<ChartActivity>(ChartActivity.class);
/*

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

    /*
    @Test
    public void recordedTest_openWeekDaysChart() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.weekDays), withText("Weekdays"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
    }
*/



    @Test
    public void asd() {


        appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        appContext.startActivity(intent);

        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView.perform(click());

        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.weekDays), withText("Weekdays"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());

    }






}
