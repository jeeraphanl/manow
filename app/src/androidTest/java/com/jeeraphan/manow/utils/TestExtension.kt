package com.jeeraphan.manow.utils

import androidx.test.espresso.PerformException
import androidx.test.espresso.ViewInteraction
import java.util.concurrent.TimeoutException

// Default at 100 milliseconds
const val DEFAULT_PULLING_INTERVAL_IN_MS: Long = 100

// Default at 10 seconds
const val DEFAULT_TIMEOUT_IN_MS: Long = 10 * 1000

fun waitForAssertion(pullingIntervalInMS: Long = DEFAULT_PULLING_INTERVAL_IN_MS,
                     timeoutInMS: Long = DEFAULT_TIMEOUT_IN_MS,
                     assertion: () -> ViewInteraction): ViewInteraction {
    val startTime = System.currentTimeMillis()
    val endTime = startTime + timeoutInMS

    var lastThrowable: Throwable?
    do {
        try {
            return assertion()
        } catch (e: Throwable) {
            // do nothing
            lastThrowable = e
        }
        Thread.sleep(pullingIntervalInMS)
    } while (System.currentTimeMillis() < endTime)

    if (lastThrowable != null) {
        throw lastThrowable
    }
    throw PerformException.Builder().withCause(TimeoutException("Timeout occurred")).build()
}