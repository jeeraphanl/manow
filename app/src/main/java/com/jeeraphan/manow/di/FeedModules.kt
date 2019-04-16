package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.data.repository.NewsRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.presentation.FeedViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val feedModule = module {

    factory<NewsRepository> { NewsRepositoryImpl(api = get()) }

    factory<GetFeedUseCase> { GetFeedUseCaseImpl(repository = get()) }

    viewModel { FeedViewModel(getFeedUseCase = get()) }
}