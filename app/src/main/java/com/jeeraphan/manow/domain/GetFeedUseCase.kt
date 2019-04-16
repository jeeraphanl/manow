package com.jeeraphan.manow.domain

import com.jeeraphan.manow.data.entity.response.NewsResponse
import com.jeeraphan.manow.data.repository.NewsRepository
import io.reactivex.Observable

interface GetFeedUseCase {
    fun execute(): Observable<List<NewsResponse.Article>>
}

class GetFeedUseCaseImpl(private val repository: NewsRepository) : GetFeedUseCase {

    override fun execute(): Observable<List<NewsResponse.Article>> {
        return repository.getFeed()
                .map { it.articles }
    }
}