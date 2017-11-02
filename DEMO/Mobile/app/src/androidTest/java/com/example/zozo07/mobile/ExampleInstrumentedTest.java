package com.example.zozo07.mobile;

import android.content.Context;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.contrib.PickerActions;
import android.widget.TimePicker;

import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.Test;

import com.concretepage.android.R;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withClassName;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.*;

/**
 * Instrumentation test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
//@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private Context appContext;
    private TimePicker timePicker;


    @Before
    public void initiatingTestEnvironment_WithProperActivityStart(){
        appContext = InstrumentationRegistry.getTargetContext();
        Intent intent = new Intent(appContext, AlarmActivity.class);
        //starting Alarm function.
        appContext.startActivity(intent);
    }

    @Test
    public void AppContext_checkPackageName_fromContext_With_GivenInputPackageName_Exception() throws Exception{
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
    public void setTimeOnTimePicker_InterruptedException() throws InterruptedException{
        int hour = 10;
        int minute = 55;

        onView(withId(R.id.alarmTimePicker)).perform(click());
        onView(withClassName(Matchers.equalTo(TimePicker.class.getName()))).perform(PickerActions.setTime(hour,minute));
        Thread.sleep(2000);
    }
}
