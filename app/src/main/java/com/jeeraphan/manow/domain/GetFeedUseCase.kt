package com.jeeraphan.manow.domain

import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.data.repository.NewsRepository

interface GetFeedUseCase {
    suspend fun execute(): UseCaseResult<List<Article>>
}

class GetFeedUseCaseImpl(private val repository: NewsRepository) : GetFeedUseCase {

    override suspend fun execute(): UseCaseResult<List<Article>> {
        return try {
            val result = repository.getFeed()
            UseCaseResult.Success(result.articles)
        } catch (e: Exception) {
            UseCaseResult.Error(e)
        }
    }
}