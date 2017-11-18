package com.example.zozo07.mobile;

import android.support.test.espresso.ViewInteraction;
import android.support.test.runner.AndroidJUnit4;

import com.concretepage.android.R;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertNotNull;

/**
 * Created by Zozo07 on 2017.11.14..
 */

@RunWith(AndroidJUnit4.class)
public class TaskDialogActivityTest {

    @Test
    public void check_If_OK_Button_Appears() {
        onView(withId(R.id.btnStop)).perform(click());
        ViewInteraction toggleButton = onView(allOf(withId(R.id.alarmToggle), withText("OK"), isDisplayed()));
        assertNotNull(toggleButton);
    }

    @Test
    public void check_If_Cancel_Button_Appears() {
        onView(withId(R.id.btnStop)).perform(click());
        ViewInteraction toggleButton = onView(allOf(withId(R.id.alarmToggle), withText("Cancel"), isDisplayed()));
        assertNotNull(toggleButton);
    }

    @Test
    public void check_If_TextBox_Appears() {
        onView(withId(R.id.btnStop)).perform(click());
        ViewInteraction toggleButton = onView(allOf(withId(R.id.tvToDo), isDisplayed()));
        assertNotNull(toggleButton);
    }
}
