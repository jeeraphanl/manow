package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.repository.FeedRepository
import com.jeeraphan.manow.data.repository.FeedRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.presentation.FeedViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val feedModule = module {

    factory<FeedRepository> { FeedRepositoryImpl(api = get()) }

    factory<GetFeedUseCase> { GetFeedUseCaseImpl(repository = get()) }

    viewModel { FeedViewModel(getFeedUseCase = get()) }
}