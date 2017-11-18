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
import static android.support.test.espresso.Espresso.pressBack;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class random {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void random() {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Calendar"), isDisplayed()));
        checkedTextView.perform(click());

        ViewInteraction checkedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView2.perform(click());

        ViewInteraction checkedTextView3 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView3.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.btnSetDate), withText("Set Date"), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button1), withText("Beállítás"), isDisplayed()));
        button2.perform(click());

        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction checkedTextView4 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView4.perform(click());

        ViewInteraction checkedTextView5 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView5.perform(click());

        pressBack();

        ViewInteraction checkedTextView6 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView6.perform(click());

        ViewInteraction checkedTextView7 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView7.perform(click());

        ViewInteraction checkedTextView8 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Sleep"), isDisplayed()));
        checkedTextView8.perform(click());

        ViewInteraction checkedTextView9 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Settings"), isDisplayed()));
        checkedTextView9.perform(click());

        ViewInteraction checkedTextView10 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Sleep"), isDisplayed()));
        checkedTextView10.perform(click());

        ViewInteraction checkedTextView11 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Graphs"), isDisplayed()));
        checkedTextView11.perform(click());

    }

}
