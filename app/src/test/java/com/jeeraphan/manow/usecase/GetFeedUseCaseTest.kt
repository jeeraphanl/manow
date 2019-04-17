package com.jeeraphan.manow.usecase

import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.data.entity.response.NewsDataModel
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
        val response = NewsDataModel().apply {
            status = "200"
            articles = listOf(
                    Article(),
                    Article()
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

        doReturn(Observable.error<NewsDataModel>(Throwable(errorMessage))).whenever(repository).getFeed()

        val test = useCase.execute().test()

        test.assertError { error ->
            error.localizedMessage == errorMessage
        }
    }
}
