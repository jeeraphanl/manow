package com.jeeraphan.manow.usecase

import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.data.entity.response.NewsDataModel
import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.domain.UseCaseResult
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class GetFeedUseCaseTest {

    private val repository: NewsRepository = mock()
    private lateinit var useCase: GetFeedUseCase

    @Before
    fun setup() {
        useCase = GetFeedUseCaseImpl(repository)
    }

    @Test
    fun testGetFeed_hasData_shouldReturnList() = runBlockingTest {
        val response = NewsDataModel().apply {
            status = "200"
            articles = listOf(
                    Article(),
                    Article()
            )
        }
        whenever(repository.getFeed()).thenReturn(response)

        val result = useCase.execute()
        Assert.assertEquals(result::class.java, UseCaseResult.Success::class.java)
        Assert.assertEquals(response, (result as UseCaseResult.Success).data)
    }

    @Test
    fun testGetFeed_noInternet_shouldError() = runBlockingTest {

        val errorMessage = "404 Data Not found"

//        whenever(repository.getFeed()).thenReturn(Throwable(errorMessage))
        //doReturn(Throwable(errorMessage)).whenever(repository).getFeed()

        val result = useCase.execute()
        Assert.assertEquals(result::class.java, UseCaseResult.Error::class.java)
        Assert.assertEquals(errorMessage, (result as UseCaseResult.Error).exception.localizedMessage)
    }
}
