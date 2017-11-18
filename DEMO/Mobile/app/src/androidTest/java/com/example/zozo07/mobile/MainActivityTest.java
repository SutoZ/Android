package com.example.zozo07.mobile;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.DrawableContainer;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.internal.util.Checks;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.support.v4.widget.DrawerLayout;
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

/**
 * Created by Zozo07 on 2017.11.16..
 */


@RunWith(AndroidJUnit4.class)
public class MainActivityTest {

    private static int HOLO_BLUE_BRIGHT = -17170459;
    //Design tests

    @Rule
    public ActivityTestRule<MainActivity> maintActivity = new ActivityTestRule<MainActivity>(MainActivity.class);


    @Test
    public void checkIfBackgroundIsHoloBlueBright() {
        onView(withId(R.id.drawer_layout)).check(matches(drawerLayoutColor(HOLO_BLUE_BRIGHT)));
    }

    public static Matcher<View> drawerLayoutColor(final int color) {
        Checks.checkNotNull(color);
        return new BoundedMatcher<View, DrawerLayout>(DrawerLayout.class) {
            @Override
            protected boolean matchesSafely(DrawerLayout drawerLayout) {
                boolean result = false;

                Drawable background = drawerLayout.getBackground();
                    result = color != ((ColorDrawable) background).getColor();
                return result;
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with text color: HOLO_BLUE_BRIGHT");
            }
        };
    }
}
