package com.example.zozo07.mobile;

import android.content.Context;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.MediumTest;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.widget.TimePicker;

import com.concretepage.android.R;
import com.squareup.spoon.Spoon;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;


@RunWith(AndroidJUnit4.class)
public class AlarmActivitySingleActivityTest {

    private Context appContext;

    @Rule
    public ActivityTestRule<AlarmActivity> alarmActivity = new ActivityTestRule<AlarmActivity>(AlarmActivity.class);


    @MediumTest
    public void testSetupConditions() throws Exception{
        assertNotNull(appContext);
        assertNotNull(alarmActivity);
    }

    @Test
    public void AppContext_checkPackageName_fromContext_With_GivenInputPackageName_Exception() throws Exception {
        String PACKAGE_NAME = "com.example.zozo07.mobile";
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals(PACKAGE_NAME, appContext.getPackageName());
    }

    @Test
    public void PowerButtonOnAlarmActivity_Appears() {
        onView(withId(R.id.alarmToggle)).perform(click());
    }

    @Test
    public void TimePickerOnAlarmActivity_Appears() {
        onView(withId(R.id.btnOpenAlarm)).perform(click());
    }

    @Test
    public void StopButtonOnAlarmActivity_Appears() {
        onView(withId(R.id.btnStop)).perform(click());
    }

    @Test
    public void setTimeOnTimePicker() {
        int hour = 10;
        int minute = 55;

        onView(withId(R.id.btnOpenAlarm)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour, minute));

        Spoon.screenshot(alarmActivity.getActivity(), "Setting_Time");

        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button1), withText("Set"), isDisplayed()));
        button2.perform(click());

        Spoon.screenshot(alarmActivity.getActivity(), "TimeSetOnTimePicker");
    }
}
