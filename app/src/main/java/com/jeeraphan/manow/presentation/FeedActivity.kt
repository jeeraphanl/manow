package com.jeeraphan.manow.presentation

import android.arch.lifecycle.Observer
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.jeeraphan.manow.R
import org.koin.android.viewmodel.ext.android.viewModel

class FeedActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.articleList.observe(this, Observer { articleList ->
            articleList?.forEach { article ->
                Log.d("app-koin", article.title)
            }
        })

        viewModel.getFeed()
    }
}
