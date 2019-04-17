package com.jeeraphan.manow.data.repository

import com.jeeraphan.manow.data.datasource.Api
import com.jeeraphan.manow.data.entity.response.NewsDataModel
import io.reactivex.Observable

interface NewsRepository {
    fun getFeed(): Observable<NewsDataModel>
}

class NewsRepositoryImpl(private val api: Api) : NewsRepository {

    override fun getFeed(): Observable<NewsDataModel> {
        return api.getFeed()
    }
}