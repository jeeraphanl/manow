package com.jeeraphan.manow.data.repository

import com.jeeraphan.manow.data.datasource.Api
import com.jeeraphan.manow.data.entity.response.NewsResponse
import io.reactivex.Observable

interface NewsRepository {
    fun getFeed(): Observable<NewsResponse>
}

class NewsRepositoryImpl(
        private val api: Api
): NewsRepository {

    override fun getFeed(): Observable<NewsResponse> {
        return api.getFeed()
    }
}