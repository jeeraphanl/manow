package com.jeeraphan.manow.screen

import androidx.test.espresso.Espresso.onView
import androidx.test.espresso.assertion.ViewAssertions.matches
import androidx.test.espresso.matcher.ViewMatchers
import androidx.test.espresso.matcher.ViewMatchers.isDisplayed
import com.jeeraphan.manow.R
import org.hamcrest.CoreMatchers.not

class FeedScreen {
    fun checkUiSuccessState() {
        onView(ViewMatchers.withId(R.id.titleTextView))
                .check(matches(isDisplayed()))
    }

    fun checkUiErrorState() {
        onView(ViewMatchers.withId(R.id.titleTextView))
                .check(matches(not(isDisplayed())))
    }
}