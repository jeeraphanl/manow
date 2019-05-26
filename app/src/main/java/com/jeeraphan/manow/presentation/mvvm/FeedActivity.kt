package com.jeeraphan.manow.presentation.mvvm

import android.arch.lifecycle.Observer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.jeeraphan.manow.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.viewmodel.ext.android.viewModel

class FeedActivity : AppCompatActivity() {

    private val viewModel: FeedViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel.articleList().observe(this, Observer { articles ->
            titleTextView.text = "The total of articles is ${articles?.size}"
        })

        viewModel.resultMessage().observe(this, Observer { resultMessage ->
            fullNameTextView.text = resultMessage
        })

        viewModel.errorMessage().observe(this, Observer { errorMessage ->
            titleTextView.text = errorMessage
        })

        viewModel.getFeed()

        //TODO 3 get full name
    }
}
