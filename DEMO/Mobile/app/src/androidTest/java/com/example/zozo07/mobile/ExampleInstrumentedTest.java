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
    }
    @Test
    public void useAppContext() throws Exception{
        appContext = InstrumentationRegistry.getTargetContext();
        assertEquals("com.example.zozo07.mobile", appContext.getPackageName());

    }
    @Test
    public void startAlarm() throws InterruptedException {
      Intent intent = new Intent(appContext, MainActivity.class);
        appContext.startActivity(intent);
        onView(withId(R.id.toolbar)).perform(click());
        Thread.sleep(3000);
    }

}
