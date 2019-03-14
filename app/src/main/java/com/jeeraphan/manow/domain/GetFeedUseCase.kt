package com.jeeraphan.manow.domain

import com.jeeraphan.manow.data.entity.response.FeedResponse
import com.jeeraphan.manow.data.repository.FeedRepository
import io.reactivex.Observable

interface GetFeedUseCase {
    fun execute(): Observable<FeedResponse>
}

class GetFeedUseCaseImpl(
        private val repository: FeedRepository
) : GetFeedUseCase {

    override fun execute(): Observable<FeedResponse> {
        return repository.getFeed()
    }
}