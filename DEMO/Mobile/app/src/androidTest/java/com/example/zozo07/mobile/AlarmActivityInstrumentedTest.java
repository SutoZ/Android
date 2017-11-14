package com.example.zozo07.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.concretepage.android.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.*;


@RunWith(AndroidJUnit4.class)
public class AlarmActivityInstrumentedTest {

    private Context appContext;


    @Rule
    public ActivityTestRule<AlarmActivity> chartActivity = new ActivityTestRule<AlarmActivity>(AlarmActivity.class);

/*
    @Before
    public void initiatingTestEnvironment_WithProperActivityStart() {
        appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, AlarmActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_FORWARD_RESULT);
        appContext.startActivity(intent);
    }

*/
    @Test
    public void AppContext_checkPackageName_fromContext_With_GivenInputPackageName_Exception() throws Exception {
        String PACKAGE_NAME = "com.example.zozo07.mobile";
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals(PACKAGE_NAME, appContext.getPackageName());
    }




    @Test
    public void openAlarmActivityAppears_InterruptedException() throws InterruptedException {
        onView(withId(R.id.alarmToggle)).perform(click());
        Thread.sleep(1000);
    }

    @Test
    public void setTimeOnTimePicker_InterruptedException() throws InterruptedException {
        int hour = 10;
        int minute = 55;

        onView(withId(R.id.alarmTimePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minute));
        Thread.sleep(2000);
    }

    @Test
    public void checkIfBarChartX_AxisIsDisplayed_InterruptedException() throws InterruptedException {
        appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, ChartActivity.class);
        appContext.startActivity(intent);
        onView((withId(R.id.bargraph))).check(matches(withText("Alarm")));
        Thread.sleep(500);
    }

}
