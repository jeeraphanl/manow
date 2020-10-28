package com.jeeraphan.manow.presentation

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.nhaarman.mockito_kotlin.doReturn
import com.nhaarman.mockito_kotlin.mock
import com.nhaarman.mockito_kotlin.whenever
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.runBlockingTest
import kotlinx.coroutines.test.setMain
import org.junit.*

@ExperimentalCoroutinesApi
class FeedViewModelTest {

    @Rule
    @JvmField
    val rule = InstantTaskExecutorRule()

    private val testDispatcher = TestCoroutineDispatcher()

    private val useCase: GetFeedUseCase = mock()
    private lateinit var viewModel: FeedViewModel

    @Before
    fun setup() {
        Dispatchers.setMain(testDispatcher)
        viewModel = FeedViewModel(useCase)
    }

    @After
    fun clean() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Test
    fun testLoadData_hasData_ShouldReturnList() = runBlockingTest {

        val response = listOf(
                Article(),
                Article()
        )
        doReturn(response).whenever(useCase).execute()

        viewModel.getFeed()

        Assert.assertNotNull(viewModel.articleList.value)
        Assert.assertNull(viewModel.errorMessage.value)
    }

    @Test
    fun testLoadData_error_ShouldError() = runBlockingTest {

        val errorMessage = "404 Data not found"
        doReturn(Throwable(errorMessage)).whenever(useCase).execute()

        viewModel.getFeed()

        Assert.assertNull(viewModel.articleList.value)
        Assert.assertNotNull(viewModel.errorMessage.value)
    }
}