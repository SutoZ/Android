package com.example.zozo07.mobile;


import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.ViewInteraction;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.test.uiautomator.UiDevice;
import android.test.suitebuilder.annotation.LargeTest;

import com.concretepage.android.R;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.replaceText;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withParent;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

@LargeTest
@RunWith(AndroidJUnit4.class)
public class StopAlarm {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule = new ActivityTestRule<>(MainActivity.class);

    private UiDevice device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());

    private String fileName;

    @Test
    public void stopAlarm() throws InterruptedException {
        ViewInteraction appCompatImageButton = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton.perform(click());

        ViewInteraction checkedTextView = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView.perform(click());

        ViewInteraction button = onView(
                allOf(withId(R.id.btnOpenAlarm), withText("Open Alarm"), isDisplayed()));
        button.perform(click());

        ViewInteraction button2 = onView(
                allOf(withId(android.R.id.button1), withText("OK"), isDisplayed()));
        button2.perform(click());

        Thread.sleep(1000);

        fileName = "StatusTextView";
    //    device.takeScreenshot(new File(IMG_DIR + fileName + ".png"));


        ViewInteraction toggleButton = onView(allOf(withId(R.id.alarmToggle), withText("OFF"), isDisplayed()));
        toggleButton.perform(click());


        ViewInteraction appCompatImageButton2 = onView(
                allOf(withContentDescription("Open navigation drawer"),
                        withParent(withId(R.id.toolbar)),
                        isDisplayed()));
        appCompatImageButton2.perform(click());

        ViewInteraction checkedTextView2 = onView(
                allOf(withId(R.id.design_menu_item_text), withText("Alarms"), isDisplayed()));
        checkedTextView2.perform(click());

        ViewInteraction button3 = onView(
                allOf(withId(R.id.btnStop), withText("STOP"), isDisplayed()));
        button3.perform(click());

        ViewInteraction editText = onView(
                allOf(withId(R.id.etSolution), isDisplayed()));
        editText.perform(click());


        ViewInteraction editText2 = onView(
                allOf(withId(R.id.etSolution), isDisplayed()));

        int num1 = TaskDialogActivity.getNum1();
        int num2 = TaskDialogActivity.getNum2();
        int sign = TaskDialogActivity.getSign();
        int solution = 0;

        switch (sign) {
            case 0:
                solution = num1 + num2;
                break;
            case 1:
                solution = num1 - num2;
                break;
            case 2:
                solution = num1 * num2;
        }

        editText2.perform(replaceText(Integer.toString(solution)), closeSoftKeyboard());

        ViewInteraction button4 = onView(
                allOf(withId(R.id.btnOK), withText("OK"), isDisplayed()));
        button4.perform(click());

        assertEquals(solution, TaskDialogActivity.getSolution());
        assertFalse(AlarmActivity.getActive());
    }
}
