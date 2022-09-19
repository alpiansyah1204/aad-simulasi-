package com.dicoding.todoapp.ui.list

import android.app.Activity
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.platform.app.InstrumentationRegistry
import androidx.test.runner.lifecycle.ActivityLifecycleMonitorRegistry
import androidx.test.runner.lifecycle.Stage
import com.dicoding.todoapp.R
import com.dicoding.todoapp.ui.add.AddTaskActivity
import junit.framework.TestCase
import org.junit.Rule
import org.junit.Test

//TODO 16 : Write UI test to validate when user tap Add Task (+), the AddTaskActivity displayed
class TaskActivityTest {
    @get:Rule
    var activityRule = ActivityScenarioRule(TaskActivity::class.java)

    @Test
    fun loadAddTask() {
        onView(ViewMatchers.withId(R.id.fab)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.fab)).perform(click())

        val addTask = AddTaskActivity()
        TestCase.assertTrue(addTask?.javaClass == AddTaskActivity::class.java)
        onView(ViewMatchers.withText("Add Task")).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_ed_title)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_ed_description)).check(matches(isDisplayed()))
        onView(ViewMatchers.withId(R.id.add_tv_due_date)).check(matches(isDisplayed()))

    }

    private fun AddTaskActivity(): Activity? {
        var activity: Activity? = null
        InstrumentationRegistry.getInstrumentation().runOnMainSync {
            run {
                activity = ActivityLifecycleMonitorRegistry.getInstance()
                    .getActivitiesInStage(Stage.RESUMED).elementAtOrNull(0)
            }
        }
        return activity
    }
}