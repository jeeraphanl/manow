package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.data.repository.NewsRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.presentation.FeedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedModule = module {

    factory<NewsRepository> { NewsRepositoryImpl(get()) }

    factory<GetFeedUseCase> { GetFeedUseCaseImpl(get()) }

    viewModel { FeedViewModel(get()) }
}