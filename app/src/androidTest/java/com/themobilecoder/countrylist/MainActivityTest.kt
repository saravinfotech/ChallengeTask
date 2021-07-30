package com.themobilecoder.countrylist

import androidx.test.espresso.Espresso
import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.action.ViewActions.click
import androidx.test.espresso.action.ViewActions.typeText
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import androidx.test.espresso.matcher.ViewMatchers.withId
import androidx.test.espresso.matcher.ViewMatchers.withText
import androidx.test.ext.junit.rules.ActivityScenarioRule
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class MainActivityTest {

    @get:Rule
    var mainActivityScenario = ActivityScenarioRule(MainActivity::class.java)


    @Test
    fun testIsActivityInView() {
        onView(withId(R.id.countryName)).check(matches(isDisplayed()))
        onView(withId(R.id.submitButton)).check(matches(isDisplayed()))
    }

    @Test
    fun testValidUserInput() {
        onView(withId(R.id.countryName)).perform(typeText("Ireland"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.submitButton)).perform(click())
        Thread.sleep(10000)
        onView(withId(R.id.countryName)).check(matches(withText("Dublin")))
    }

    @Test
    fun testInvalidUserInput() {
        onView(withId(R.id.countryName)).perform(typeText("India"))
        Espresso.closeSoftKeyboard()
        onView(withId(R.id.submitButton)).perform(click())
        Thread.sleep(10000)
        onView(withId(R.id.countryName)).check(matches(withText("Unknown code")))
    }

}