package com.example.zozo07.mobile;

import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.filters.MediumTest;
import android.support.test.internal.util.Checks;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
import android.test.ViewAsserts;
import android.view.View;

import com.concretepage.android.R;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static com.example.zozo07.mobile.HorizontalViewAssertion.alignHorizontalWith;
import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Zozo07 on 2017.11.16..
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static int BACKGROUND_COLOR = -16720385;
    private static int DIFFERENT_COLOR = -17570412;
    //Design tests

    @Rule
    public ActivityTestRule<MainActivity> mainActivity = new ActivityTestRule<MainActivity>(MainActivity.class);

    @MediumTest
    public void testSetupConditions() throws Exception{
        assertNotNull(mainActivity);
    }


    @Test
    public void Check_IfBackgroundIsHoloBlueBright() {
        onView(withId(R.id.drawer_layout)).check(matches(drawerLayoutColor(BACKGROUND_COLOR)));
    }

    @Test
    public void Should_FailToMatch_IfColorIsDifferenct() {
        onView(withId(R.id.drawer_layout)).check(matches(drawerLayoutColor(DIFFERENT_COLOR)));
    }

    @Test
    public void Check_IfButtonIsAlignedCenter() {
//        onView(withId(R.id.linearLayout)).check(matches(alignHorizontalWith((mainActivity.getActivity().findViewById(R.id.btnSetDate)))));

        /*
        onView(withId(R.id.btnSetDate)).check(matches(ViewAsserts.assertHorizontalCenterAligned(mainActivity.getActivity().findViewById(R.id.btnSetDate),
                mainActivity.getActivity().findViewById(R.id.btnSetDate))));
                */
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
}
