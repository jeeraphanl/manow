package com.jeeraphan.manow.usecase

import com.jeeraphan.manow.data.entity.response.NewsResponse
import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.di.feedModule
import com.jeeraphan.manow.di.networkModule
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext.startKoin
import org.koin.standalone.StandAloneContext.stopKoin
import org.koin.test.KoinTest

class GetFeedUseCaseTest : KoinTest {

    private val repository: NewsRepository = mock()
    private lateinit var useCase: GetFeedUseCase

    @Before
    fun before() {
        startKoin(listOf(networkModule, feedModule))
    }

    @After
    fun after() {
        stopKoin()
    }

    @Test
    fun testGetFeed_hasData_shouldReturnList() {

        val response = NewsResponse().apply {
            status = "200"
            articles = listOf(
                    NewsResponse.Article(),
                    NewsResponse.Article()
            )
        }

        doReturn(Observable.just(response)).whenever(repository).getFeed()

        useCase = GetFeedUseCaseImpl(repository)
        val test = useCase.execute().test()

        test.assertNoErrors()
        test.assertValue { articles ->
            articles.isNotEmpty()
        }
    }

    @Test
    fun testGetFeed_noInternet_shouldError() {

        val errorMessage = "404 Data Not found"

        doReturn(Observable.error<NewsResponse>(Throwable(errorMessage))).whenever(repository).getFeed()

        useCase = GetFeedUseCaseImpl(repository)
        val test = useCase.execute().test()

        test.assertError { error ->
            error.localizedMessage == errorMessage
        }
    }
}