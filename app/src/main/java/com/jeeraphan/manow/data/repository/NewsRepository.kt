package com.jeeraphan.manow.data.repository

import com.jeeraphan.manow.data.datasource.Api
import com.jeeraphan.manow.data.entity.response.NewsDataModel

interface NewsRepository {
    suspend fun getFeed(): NewsDataModel
}

class NewsRepositoryImpl(private val api: Api) : NewsRepository {

    override suspend fun getFeed(): NewsDataModel {
        return api.getFeed()
    }
}