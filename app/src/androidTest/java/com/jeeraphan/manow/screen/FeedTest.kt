package com.jeeraphan.manow.screen

import androidx.test.rule.ActivityTestRule
import com.jeeraphan.manow.data.datasource.Api
import com.jeeraphan.manow.data.datasource.RetrofitBuilder
import com.jeeraphan.manow.presentation.mvvm.FeedActivity
import com.jeeraphan.manow.utils.RequestDispatcher
import okhttp3.mockwebserver.MockWebServer
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.koin.core.context.loadKoinModules
import org.koin.core.context.unloadKoinModules
import org.koin.dsl.module

const val TEST_PORT = 8080
const val TEST_BASE_URL = "http://localhost:8080/"

class FeedTest {

    @get:Rule
    var activityRule = ActivityTestRule(FeedActivity::class.java, false, false)

    private val mockWebServer = MockWebServer()
    private lateinit var dispatcher: RequestDispatcher
    private val feedScreen = FeedScreen()

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
        activityRule.launchActivity(null)
        feedScreen.checkUiSuccessState()
    }
}