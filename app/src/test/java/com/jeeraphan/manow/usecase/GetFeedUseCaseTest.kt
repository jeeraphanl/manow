package com.jeeraphan.manow.usecase

import com.jeeraphan.manow.data.entity.response.NewsResponse
import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import io.reactivex.Observable
import org.junit.Test

class GetFeedUseCaseTest {

    private val repository: NewsRepository = mock()
    private lateinit var useCase: GetFeedUseCase

    @Test
    fun testGetFeed_hasData_shouldReturnList() {

        useCase = GetFeedUseCaseImpl(repository)
        val response = NewsResponse().apply {
            status = "200"
            articles = listOf(
                    NewsResponse.Article(),
                    NewsResponse.Article()
            )
        }

        doReturn(Observable.just(response)).whenever(repository).getFeed()

        val test = useCase.execute().test()

        test.assertNoErrors()
        test.assertValue { articles ->
            articles.isNotEmpty()
        }
    }

    @Test
    fun testGetFeed_noInternet_shouldError() {

        useCase = GetFeedUseCaseImpl(repository)

        val errorMessage = "404 Data Not found"

        doReturn(Observable.error<NewsResponse>(Throwable(errorMessage))).whenever(repository).getFeed()

        val test = useCase.execute().test()

        test.assertError { error ->
            error.localizedMessage == errorMessage
        }
    }
}
