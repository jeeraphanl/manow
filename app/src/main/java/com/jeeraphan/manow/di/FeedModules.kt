package com.jeeraphan.manow.di

import com.jeeraphan.manow.data.repository.NewsRepository
import com.jeeraphan.manow.data.repository.NewsRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import com.jeeraphan.manow.presentation.mvp.FeedContract
import com.jeeraphan.manow.presentation.mvp.FeedPresenter
import com.jeeraphan.manow.presentation.mvvm.FeedViewModel
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val feedModule = module {

    factory<NewsRepository> { NewsRepositoryImpl(get()) }

    factory<GetFeedUseCase> { GetFeedUseCaseImpl(get()) }

    //TODO 4 Resolve dependency

    factory<FeedContract.Presenter> { (view: FeedContract.View) -> FeedPresenter(view, get()) }

    viewModel { FeedViewModel(get()) }
}