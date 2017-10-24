package com.example.zozo07.mobile;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.concretepage.android.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Context appContext;


    @Before
    public void random(){
        appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, AlarmActivity.class);
        //starting Alarm function.
        appContext.startActivity(intent);
    }
    @Test
    public void useAppContext() throws Exception{
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.zozo07.mobile", appContext.getPackageName());

    }
    @Test
    public void openAlarmActivity() throws InterruptedException {
        onView(withId(R.id.alarmToggle)).perform(click());
        Thread.sleep(1000);
    }

    @Test
    public void setHourOnTimePicker() {
        onView(withId(R.id.alarmTimePicker)).perform(click());
        onView(withText("05")).perform(click());
    }

}
