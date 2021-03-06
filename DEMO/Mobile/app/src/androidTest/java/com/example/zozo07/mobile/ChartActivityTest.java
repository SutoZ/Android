package com.example.zozo07.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewAssertion;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.core.deps.guava.base.Optional;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.test.ActivityInstrumentationTestCase2;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.concretepage.android.R;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.w3c.dom.Text;

import java.io.File;

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
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.core.StringContains.containsString;
import static org.hamcrest.Matchers.allOf;

/**
 * Created by Zozo07 on 2017.10.31..
 */

@RunWith(AndroidJUnit4.class)
public class ChartActivityTest {


    private UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    private static String IMG_DIR = "/storage/emulated/0/Pictures/Screenshots/";
    private String fileName;
    @Rule
    public ActivityTestRule<ChartActivity> chartActivity = new ActivityTestRule<ChartActivity>(ChartActivity.class);

    @MediumTest
    public void testSetupConditions() {
        assertNotNull(chartActivity);
    }

    @Test
    public void BarGraphOnChartActivity_Appears() {
        onView(withId(R.id.bargraph)).perform(click());
    }

    @Test
    public void WeekDayRadioButtonOnChartActivity_Appears() {
        onView(withId(R.id.weekDays)).perform(click());
    }

    @Test
    public void SaturdayRadioButtonOnChartActivity_Appears() {
        onView(withId(R.id.saturday)).perform(click());
    }

    @Test
    public void SundayRadioButtonOnChartActivity_Appears() {
        onView(withId(R.id.sunday)).perform(click());
    }

    @Test
    public void VacationTextViewOnChartActivity_Appears() {
        onView(withId(R.id.tvVacation)).perform(click());
    }

    @Test
    public void AlarmTextViewOnChartActivity_Appears() {
        onView(withId(R.id.tvAlarm)).perform(click());
    }

    @Test
    public void SleepTextViewOnChartActivity_Appears() {
        onView(withId(R.id.tvSleep)).perform(click());
    }

    @Test
        public void checkIfBarChartIsDisplayed_InterruptedException() {
            onView((withId(R.id.bargraph))).check(matches(isDisplayed()));
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
            assertNotNull(withId(R.id.bargraph));
        }

    @Test
    public void weekDay_Alarm_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.weekDays), withText("Weekdays"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String WEEKDAY_ALARM = "80";
        onView(withId(R.id.tvAlarm)).check(ViewAssertions.matches(withText(WEEKDAY_ALARM)));

        fileName = "WeekDayAlarm";
        device.takeScreenshot(new File(IMG_DIR + fileName + ".png"));

    }

    @Test
    public void saturday_Alarm_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.saturday), withText("Saturday"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String SATURDAY_ALARM = "20";
        onView(withId(R.id.tvAlarm)).check(ViewAssertions.matches(withText(SATURDAY_ALARM)));
    }

    @Test
    public void sunday_Alarm_value_Equals_textViewValue() {


        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.sunday), withText("Sunday"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String SUNDAY_ALARM = "30";
        onView(withId(R.id.tvAlarm)).check(ViewAssertions.matches(withText(SUNDAY_ALARM)));
    }

    @Test
    public void weekDay_Sleep_value_Equals_textViewValue() {

             ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.weekDays), withText("Weekdays"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String WEEKDAY_SLEEP = "70";
        onView(withId(R.id.tvSleep)).check(ViewAssertions.matches(withText(WEEKDAY_SLEEP)));
    }

    @Test
    public void saturday_Sleep_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.saturday), withText("Saturday"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String SATURDAY_SLEEP = "50";
        onView(withId(R.id.tvSleep)).check(ViewAssertions.matches(withText(SATURDAY_SLEEP)));

        fileName = "Saturday_Sleep";
        device.takeScreenshot(new File(IMG_DIR + fileName + ".png"));
    }

    @Test
    public void sunday_Sleep_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.sunday), withText("Sunday"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String SUNDAY_SLEEP = "60";
        onView(withId(R.id.tvSleep)).check(ViewAssertions.matches(withText(SUNDAY_SLEEP)));
    }

    @Test
    public void weekDay_Vacation_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.weekDays), withText("Weekdays"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String WEEKDAY_VACATION = "20";
        onView(withId(R.id.tvVacation)).check(ViewAssertions.matches(withText(WEEKDAY_VACATION)));
    }

    @Test
    public void saturday_Vacation_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.saturday), withText("Saturday"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String SATURDAY_VACATION = "90";
        onView(withId(R.id.tvVacation)).check(ViewAssertions.matches(withText(SATURDAY_VACATION)));
    }

    @Test
    public void sunday_Vacation_value_Equals_textViewValue() {
        ViewInteraction appCompatRadioButton = onView(
                allOf(withId(R.id.sunday), withText("Sunday"),
                        withParent(allOf(withId(R.id.radioGroup),
                                withParent(withId(R.id.chart_activity)))),
                        isDisplayed()));
        appCompatRadioButton.perform(click());
        onView(withId(R.id.bargraph)).perform(click());
        String SUNDAY_VACATION = "100";
        onView(withId(R.id.tvVacation)).check(ViewAssertions.matches(withText(SUNDAY_VACATION)));

        fileName = "Sunday_Vacation";
        device.takeScreenshot(new File(IMG_DIR + fileName + ".png"));
    }
}
