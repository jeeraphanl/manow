package com.jeeraphan.manow.presentation

import android.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeeraphan.manow.data.entity.response.NewsResponse
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test

class FeedViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val useCase: GetFeedUseCase = mock()
    private lateinit var viewModel: FeedViewModel

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        viewModel = FeedViewModel(useCase)
    }

    @After
    fun clean() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testLoadData_hasData_ShouldReturnList() {

        val response = listOf(
                NewsResponse.Article(),
                NewsResponse.Article()
        )
        doReturn(Observable.just(response)).whenever(useCase).execute()

        viewModel.getFeed()

        assert(viewModel.errorMessage().value == null)
        assert(viewModel.articleList().value != null)
    }

    @Test
    fun testLoadData_error_ShouldError() {

        val errorMessage = "404 Data not found"
        doReturn(Observable.error<NewsResponse>(Throwable(errorMessage))).whenever(useCase).execute()

        viewModel.getFeed()

        assert(viewModel.errorMessage().value != null)
        assert(viewModel.articleList().value == null)
    }
}