package com.example.zozo07.mobile;


import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import com.concretepage.android.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class ResetAlarmTest {       //???

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void modifyAlarm() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView.perform(click());

        ViewInteraction toggleButton = onView(
                allOf(withId(R.id.alarmToggle), withText("OFF"), isDisplayed()));
        toggleButton.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction checkedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView2.perform(click());

        ViewInteraction toggleButton2 = onView(
                allOf(withId(R.id.alarmToggle), withText("OFF"), isDisplayed()));
        toggleButton2.perform(click());

    }

}
