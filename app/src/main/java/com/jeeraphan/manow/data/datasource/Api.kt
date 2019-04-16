package com.jeeraphan.manow.data.datasource

import com.jeeraphan.manow.data.entity.response.NewsResponse
import io.reactivex.Observable
import retrofit2.http.GET

interface Api {
    @GET("top-headlines?sources=abc-news&apiKey=41d2725710ae481795ff89337da615fe")
    fun getFeed(): Observable<NewsResponse>
}