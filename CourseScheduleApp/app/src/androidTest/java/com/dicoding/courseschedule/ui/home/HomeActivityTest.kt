package com.dicoding.courseschedule.ui.home


import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import com.dicoding.courseschedule.R
import org.junit.Rule
import org.junit.Test

class HomeActivityTest {


    @get:Rule
    var activityRule = ActivityScenarioRule(HomeActivity::class.java)

    @Test
    fun pressaAddButton() {
        onView(ViewMatchers.withId(R.id.action_add)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.action_add)).perform(click())
        onView(ViewMatchers.withText("Add Course")).check(matches(isDisplayed()))
    }
}