package com.jeeraphan.manow.data.repository

import com.jeeraphan.manow.data.entity.response.FeedResponse
import io.reactivex.Observable

interface FeedRepository {
    fun getFeed(): Observable<FeedResponse>
}

class FeedRepositoryImpl(
        private val api: Api
): FeedRepository {

    override fun getFeed(): Observable<FeedResponse> {
        return api.getFeed()
    }
}