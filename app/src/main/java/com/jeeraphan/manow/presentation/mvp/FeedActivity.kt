package com.jeeraphan.manow.presentation.mvp

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.R
import com.jeeraphan.manow.data.entity.response.Article
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.ext.android.inject
import org.koin.core.parameter.parametersOf

class FeedActivity : AppCompatActivity(), FeedContract.View {

    private val presenter: FeedContract.Presenter by inject { parametersOf(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getFeed()
    }

    override fun showArticleList(articleList: List<Article>) {
        titleTextView.text = "The total of articles is ${articleList?.size}"
    }

    override fun showError(errorMessage: String) {
        titleTextView.text = errorMessage
    }
}