package com.example.zozo07.mobile;

import android.view.View;

import org.hamcrest.Description;
import org.hamcrest.TypeSafeMatcher;

/**
 * Created by Zozo07 on 2017.11.18..
 */

class HorizontalViewAssertion extends TypeSafeMatcher<View> {
    private final View view;

    private HorizontalViewAssertion (View view) {
        this.view = view;
    }


    public static HorizontalViewAssertion alignHorizontalWith(View view) {
        return new HorizontalViewAssertion(view);
    }

    @Override
    protected boolean matchesSafely(View item) {

        float centerX1 = item.getX() + (((float)item.getWidth())/2.0f);
        float centerX2 = this.view.getX() + (((float)this.view.getWidth())/2.0f);
        if ( (Math.abs(centerX1 - centerX2)) <= 1)
            return true;
        return false;
    }

    @Override
    public void describeTo(Description description) {

    }
}