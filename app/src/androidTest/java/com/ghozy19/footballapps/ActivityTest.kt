package com.ghozy19.footballapps

import android.support.test.espresso.Espresso.onView
import android.support.test.espresso.Espresso.pressBack
import android.support.test.espresso.action.ViewActions
import android.support.test.espresso.action.ViewActions.click
import android.support.test.espresso.assertion.ViewAssertions.matches
import android.support.test.espresso.contrib.RecyclerViewActions
import android.support.test.espresso.matcher.ViewMatchers
import android.support.test.espresso.matcher.ViewMatchers.isDisplayed
import android.support.test.espresso.matcher.ViewMatchers.withId
import android.support.test.rule.ActivityTestRule
import android.support.test.runner.AndroidJUnit4
import android.support.v7.widget.RecyclerView
import com.ghozy19.footballapps.R.id.*
import com.ghozy19.footballapps.utils.MainActivity
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith


@RunWith(AndroidJUnit4::class)
class ActivityTest {
    @Rule
    @JvmField
    var activityRule = ActivityTestRule(MainActivity::class.java)

    @Test
    fun testBotNav() {
        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
        onView(withId(favorite))
                .check(matches(isDisplayed()))

        onView(withId(clubs)).perform(click())
        onView(withId(clubs))
                .check(matches(isDisplayed()))

        onView(withId(match)).perform(click())
        onView(withId(match))
                .check(matches(isDisplayed()))


    }

    @Test
    fun testMatch(){
        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(match))
                .check(matches(isDisplayed()))
        onView(ViewMatchers.withText(R.string.last_match)).perform(click())
        Thread.sleep(5000)

        onView(withId(rvViewMatchLast)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(10))
        onView(withId(rvViewMatchLast)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(10, click()))
        Thread.sleep(5000)

        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
    }

    @Test
    fun testFav() {
        onView(withId(bottom_navigation))
                .check(matches(isDisplayed()))
        onView(withId(favorite)).perform(click())
        onView(withId(rvFavorite)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(5000)
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(swipeRefreshFav)).perform(ViewActions.swipeDown())

        onView(ViewMatchers.withText(R.string.match_fragment)).perform(click())
        onView(withId(rvFavoriteMatch)).perform(RecyclerViewActions.scrollToPosition<RecyclerView.ViewHolder>(0))
        onView(withId(rvFavoriteMatch)).perform(RecyclerViewActions.actionOnItemAtPosition<RecyclerView.ViewHolder>(0, click()))
        Thread.sleep(5000)
        onView(withId(add_to_favorite))
                .check(matches(isDisplayed()))
        onView(withId(add_to_favorite)).perform(click())
        pressBack()
        onView(withId(swipeRefreshFavMatch)).perform(ViewActions.swipeDown())
    }

@Test
fun testSpinner(){
    onView(withId(bottom_navigation))
            .check(matches(isDisplayed()))
    onView(withId(match)).perform(click())
    onView(ViewMatchers.withText(R.string.last_match)).perform(click())
    Thread.sleep(5000)

    onView(withId(listspinner))
            .check(matches(isDisplayed()))
    onView(withId(listspinner)).perform(click())
    onView(ViewMatchers.withText("Spanish La Liga")).perform(click())
    Thread.sleep(5000)

}


}
