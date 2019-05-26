package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.data.repository.NewsRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.presentation.mvp.FeedContract
import com.jeeraphan.manow.presentation.mvp.FeedPresenter
import com.jeeraphan.manow.presentation.mvvm.FeedViewModel
import org.koin.android.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val feedModule = module {

    factory<NewsRepository> { NewsRepositoryImpl(get()) }

    factory<GetFeedUseCase> { GetFeedUseCaseImpl(get()) }

    factory<FeedContract.Presenter> { (view: FeedContract.View) -> FeedPresenter(view, get()) }

    viewModel { FeedViewModel(get()) }
}