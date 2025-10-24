package com.example.androiduitesting;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.CoreMatchers.is;

import androidx.test.espresso.action.ViewActions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ShowActivityTest {
    @Rule
    public ActivityScenarioRule<MainActivity> scenario = new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void testNavigateToShowActivity() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Calgary"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is("Calgary"))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        onView(withId(R.id.text_city_name)).check(matches(isDisplayed()));
    }

    @Test
    public void testCityNameDisplayed() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Toronto"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is("Toronto"))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        onView(withId(R.id.text_city_name)).check(matches(withText("Toronto")));
    }

    @Test
    public void testBackButtonReturnsToMain() {
        onView(withId(R.id.button_add)).perform(click());
        onView(withId(R.id.editText_name)).perform(ViewActions.typeText("Montreal"), ViewActions.closeSoftKeyboard());
        onView(withId(R.id.button_confirm)).perform(click());

        onData(is("Montreal"))
                .inAdapterView(withId(R.id.city_list))
                .perform(click());

        onView(withId(R.id.button_back)).perform(click());
        onView(withId(R.id.city_list)).check(matches(isDisplayed()));
        onView(withId(R.id.text_city_name)).check(doesNotExist());
    }
}
