package com.jeeraphan.manow.data.datasource

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

class OkHttpBuilder() {

    fun build(): OkHttpClient {
        return OkHttpClient.Builder()
                .connectTimeout(60L, TimeUnit.SECONDS)
                .readTimeout(60L, TimeUnit.SECONDS)
                .build()
    }
}