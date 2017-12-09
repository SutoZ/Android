package com.example.zozo07.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.filters.SdkSuppress;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiScrollable;
import android.support.test.uiautomator.UiSelector;
import android.widget.TimePicker;

import com.concretepage.android.R;
import com.squareup.spoon.Spoon;

import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.InstrumentationRegistry.getInstrumentation;
import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by Zozo07 on 2017.11.12..
 */

@RunWith(AndroidJUnit4.class)
@SdkSuppress(minSdkVersion = 18)
public class AlarmActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<MainActivity>(MainActivity.class);

    private UiDevice myDevice;

    @Test
    public void check_if_ToastMessage_Equals_SetTime_From_TimePicker_Alarm() throws InterruptedException {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView.perform(click());

        int hour = 10;
        int minute = 55;

        ViewInteraction button = onView(
                allOf(withId(R.id.btnOpenAlarm), withText("Open Alarm"), isDisplayed()));
        button.perform(click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour,minute));

        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button1), withText("Set"), isDisplayed()));
        button2.perform(click());

        Thread.sleep(1000);

        Spoon.screenshot(mActivityRule.getActivity(), "ToastMessage");

        ViewInteraction toggleButton = onView(
                allOf(withId(R.id.alarmToggle), withText("OFF"), isDisplayed()));
        toggleButton.perform(click());


        onView(withText("Alarm set for " + Integer.toString(hour) + ":" + Integer.toString(minute))).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));
    }

    @Test
    public void check_if_ToastMessage_Equals_SetTime_From_TimePicker_Vacation() {
        ViewInteraction button = onView(
                allOf(withId(R.id.btnSetDate), withText("Set Date"), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        button2.perform(click());

        int year = AlarmActivity.getYEAR();
        int month = AlarmActivity.getMONTH();
        int day = AlarmActivity.getDAY();

        onView(withText(containsString("Special occasion: " + year + "."  + month + "." + day)))
                .inRoot(new ToastMatcher()).check(matches(isDisplayed()));

    }
}
