package com.jeeraphan.manow.screen

import androidx.test.core.app.ActivityScenario
import androidx.test.espresso.Espresso
import androidx.test.espresso.assertion.ViewAssertions
import androidx.test.espresso.matcher.ViewMatchers
import com.jeeraphan.manow.R
import com.jeeraphan.manow.data.datasource.Api
import com.jeeraphan.manow.data.datasource.RetrofitBuilder
import com.jeeraphan.manow.presentation.FeedActivity
import com.jeeraphan.manow.utils.RequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

const val TEST_PORT = 8080
const val TEST_BASE_URL = "http://localhost:8080/"

class FeedTest {

    private val mockWebServer = MockWebServer()
    private lateinit var dispatcher: RequestDispatcher

    private val mockedModule = module {
        single<Api>(override = true) { get<RetrofitBuilder>().build(TEST_BASE_URL) }
    }

    @Before
    fun setup() {
        mockWebServer.start(TEST_PORT)
        dispatcher = RequestDispatcher()
        mockWebServer.dispatcher = dispatcher
        loadKoinModules(mockedModule)
    }

    @After
    fun teardown() {
        mockWebServer.shutdown()
        unloadKoinModules(mockedModule)
    }

    @Test
    fun testFeedSuccessState() {
        ActivityScenario.launch(FeedActivity::class.java)
        Thread.sleep(500)
        Espresso.onView(ViewMatchers.withId(R.id.titleTextView))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()))
    }
}