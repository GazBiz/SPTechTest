package com.example.garethbizley.sptechtest

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import com.example.garethbizley.sptechtest.main.MainActivity
import org.hamcrest.Matchers.not
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

/**
 * Created by Gaz Biz on 22/9/18.
 */
@RunWith(AndroidJUnit4::class)

class MainActivityTest {

    @get:Rule
    var activityActivityTestRule = ActivityTestRule<MainActivity>(MainActivity::class.java)

    /**
     * This test passes, as the state of the UI is not 'tested' until Espresso detects that the app
     * is not running any processes - i.e. after network response is returned and both views are 'GONE'
     * What I was hoping to test is that once the get_albums_btn is clicked, get_albums_btn is GONE
     * and my loading layout is VISIBLE - i.e. the state of the view before network response is returned.
     * This being my first espresso test and time being a factor I'm leaving it as is but will seek
     * out a solution in my own time :D
     */
    @Test
    fun testGetAlbumsButtonUpdatesUI() {
        onView(withId(R.id.get_albums_btn))
                .perform(click())
                .check(matches(not(isDisplayed())))

        onView(withId(R.id.loading_display_ll))
                    .check(matches(not(isDisplayed())))
    }
}