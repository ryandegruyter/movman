package com.ryandg.movieman;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.runner.AndroidJUnit4;
import android.test.ViewAsserts;

import com.jakewharton.test.ActivityRule;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import ryandg.ryandg.movieman.R;

import static android.support.test.espresso.Espresso.*;
import static android.support.test.espresso.assertion.ViewAssertions.*;
import static android.support.test.espresso.matcher.ViewMatchers.*;

/**
 * Created by Ryan on 25/05/2015.
 */
@RunWith(AndroidJUnit4.class)
public class LoginActivityTest {

    @Rule
    public final ActivityRule<LoginActivity> loginActivity = new ActivityRule<>(LoginActivity.class);

    @Test
    public void hasSignInButton() throws Exception {
//        onView(withId(android.R.id.content)).check(ViewAssertions.selectedDescendantsMatch(withId(R.id.btn_sign_in)));
    }
}
