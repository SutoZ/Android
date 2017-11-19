package com.example.zozo07.mobile;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.ViewInteraction;
import android.support.test.espresso.contrib.PickerActions;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.MediumTest;
import android.support.test.internal.util.Checks;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.test.ViewAsserts;
import android.util.Log;
import android.view.View;
import android.widget.TimePicker;

import com.concretepage.android.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static com.example.zozo07.mobile.HorizontalViewAssertion.alignHorizontalWith;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;

/**
 * Created by Zozo07 on 2017.11.16..
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static int BACKGROUND_COLOR = -16720385;
    private static int DIFFERENT_COLOR = -17570412;
    private static final String TAG = "MainActivityTest";

    //Design tests

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    @MediumTest
    public void testSetupConditions() throws Exception{
        assertNotNull(mainActivity);
    }

    private int hour, minute;


    @Test
    public void StatusTextViewOnMainActivity_Appears() throws InterruptedException {
        onView(withId(R.id.tvStatus)).perform(click());
        Log.d(TAG, "Textview for alarm status appears.");
        Thread.sleep(1000);
    }

    @Test
    public void DateTextViewOnMainActivity_Appears() throws InterruptedException {
        onView(withId(R.id.tvDate)).perform(click());
        Log.d(TAG, "Textview for special date appears.");
        Thread.sleep(1000);
    }

    @Test
    public void SetSpecialDateButtonOnMainActivity_Appears() throws InterruptedException {
        onView(withId(R.id.btnSetDate)).perform(click());
        Log.d(TAG, "Choose special date appears.");
        Thread.sleep(1000);
    }

    @Test
    public void Check_IfBackgroundIsHoloBlueBright() {
        onView(withId(R.id.drawer_layout)).check(matches(drawerLayoutColor(BACKGROUND_COLOR)));
    }

    @Test
    public void Should_FailToMatch_IfColorIsDifferenct() {
        onView(withId(R.id.drawer_layout)).check(matches(drawerLayoutColor(DIFFERENT_COLOR)));
    }


    public static Matcher<View> drawerLayoutColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, DrawerLayout>(DrawerLayout.class) {
            @Override
            protected boolean matchesSafely(DrawerLayout drawerLayout) {
                boolean result;
                Drawable background = drawerLayout.getBackground();
                result = color == ((ColorDrawable) background).getColor();
                return result;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: HOLO_BLUE_BRIGHT");
            }
        };
    }

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

        //Set default time

        hour = 10;
        minute = 45;

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour,minute));


        ViewInteraction toggleButton = onView(
                allOf(withId(R.id.alarmToggle), withText("Ki"), isDisplayed()));
        toggleButton.perform(click());

        onView(withText("Alarm set for " + Integer.toString(hour) + ":" + Integer.toString(minute))).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));


        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        //Modify predefined time

        hour = 12;
        minute = 30;

        ViewInteraction checkedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView2.perform(click());

        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour,minute));


        ViewInteraction toggleButton2 = onView(
                allOf(withId(R.id.alarmToggle), withText("Ki"), isDisplayed()));
        toggleButton2.perform(click());


        onView(withText("Alarm set for " + Integer.toString(hour) + ":" + Integer.toString(minute))).inRoot(new ToastMatcher())
                .check(matches(isDisplayed()));

    }
}
