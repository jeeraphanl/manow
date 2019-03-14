package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.repository.Api
import com.jeeraphan.manow.data.repository.FeedRepository
import com.jeeraphan.manow.data.repository.FeedRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.presentation.FeedViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

const val BASE_URL_NAME = "newsApi"
const val BASE_URL = "https://newsapi.org/v2/"

val feedModule = module {

    single(BASE_URL_NAME) { BASE_URL }

    single { createOkHttpClient() }

    single<Api> { createWebService(OkHttpClient(), get(BASE_URL_NAME)) }

    factory<FeedRepository> { FeedRepositoryImpl(api = get()) }

    factory<GetFeedUseCase> { GetFeedUseCaseImpl(repository = get()) }

    viewModel { FeedViewModel(getFeedUseCase = get()) }
}

fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
            .connectTimeout(60L, TimeUnit.SECONDS)
            .readTimeout(60L, TimeUnit.SECONDS)
            .addInterceptor(httpLoggingInterceptor)
            .build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
            .baseUrl(url)
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}