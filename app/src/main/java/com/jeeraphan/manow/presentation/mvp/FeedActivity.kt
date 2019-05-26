package com.jeeraphan.manow.presentation.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.R
import com.jeeraphan.manow.data.datasource.Api
import com.jeeraphan.manow.data.datasource.OkHttpBuilder
import com.jeeraphan.manow.data.datasource.RetrofitBuilder
import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.data.repository.NewsRepositoryImpl
import com.jeeraphan.manow.domain.GetFeedUseCaseImpl
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class FeedActivity : AppCompatActivity(), FeedContract.View {

    private lateinit var presenter: FeedContract.Presenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val retrofit = RetrofitBuilder(OkHttpBuilder().build(),
                GsonConverterFactory.create(),
                RxJava2CallAdapterFactory.create())
        val api: Api = retrofit.build("https://newsapi.org/v2/")
        val repository = NewsRepositoryImpl(api)
        val useCase = GetFeedUseCaseImpl(repository)
        presenter = FeedPresenter(this, useCase)

        presenter.getFeed()
    }

    override fun showArticleList(articleList: List<Article>) {
        titleTextView.text = "The total of articles is ${articleList?.size}"
    }

    override fun showError(errorMessage: String) {
        titleTextView.text = errorMessage
    }
}