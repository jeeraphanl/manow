package com.jeeraphan.manow.presentation

import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.data.entity.response.NewsDataModel
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.presentation.mvp.FeedContract
import com.jeeraphan.manow.presentation.mvp.FeedPresenter
import com.nhaarman.mockito_kotlin.*
import io.reactivex.Observable
import io.reactivex.android.plugins.RxAndroidPlugins
import io.reactivex.plugins.RxJavaPlugins
import io.reactivex.schedulers.Schedulers
import org.junit.After
import org.junit.Before
import org.junit.Test

class FeedPresenterTest {

    private val view: FeedContract.View = mock()
    private val useCase: GetFeedUseCase = mock()
    private lateinit var presenter: FeedPresenter

    @Before
    fun setup() {
        RxAndroidPlugins.setInitMainThreadSchedulerHandler { Schedulers.trampoline() }
        RxJavaPlugins.setIoSchedulerHandler { Schedulers.trampoline() }

        presenter = FeedPresenter(view, useCase)
    }

    @After
    fun clean() {
        RxJavaPlugins.reset()
        RxAndroidPlugins.reset()
    }

    @Test
    fun testLoadData_hasData_ShouldReturnList() {

        val response = listOf(
                Article(),
                Article()
        )
        doReturn(Observable.just(response)).whenever(useCase).execute()

        presenter.getFeed()

        verify(view).showArticleList(response)
        verify(view, never()).showError("")
    }

    @Test
    fun testLoadData_error_ShouldError() {

        val errorMessage = "404 Data not found"
        doReturn(Observable.error<NewsDataModel>(Throwable(errorMessage))).whenever(useCase).execute()

        presenter.getFeed()

        verify(view).showError(errorMessage)
        verify(view, never()).showArticleList(any())
    }
}