package com.jeeraphan.manow.presentation.mvp

import com.jeeraphan.manow.data.entity.response.Article

interface FeedContract {

    interface View {
        fun showArticleList(articleList: List<Article>)
        fun showError(errorMessage: String)
    }

    interface Presenter {
        fun getFeed()
        fun clear()
    }
}