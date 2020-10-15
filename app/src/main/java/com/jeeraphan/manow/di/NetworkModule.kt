package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.datasource.OkHttpBuilder
import com.jeeraphan.manow.data.datasource.RetrofitBuilder
import com.jeeraphan.manow.data.datasource.Api
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "https://newsapi.org/v2/"

val networkModule = module {

    single { OkHttpBuilder().build() }

    single<Converter.Factory> { GsonConverterFactory.create() }

    single { RetrofitBuilder(get(), get()) }

    single<Api> { get<RetrofitBuilder>().build(BASE_URL) }
}