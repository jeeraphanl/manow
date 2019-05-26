package com.jeeraphan.manow.presentation.mvp

import com.jeeraphan.manow.domain.GetFeedUseCase
import com.tdcm.trueidapp.extensions.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedPresenter(
        private var view: FeedContract.View?,
        private val getFeedUseCase: GetFeedUseCase
) : FeedContract.Presenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getFeed() {
        getFeedUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ articles ->
                    view?.showArticleList(articles)
                }, { error ->
                    view?.showError(error.localizedMessage)
                })
                .addTo(compositeDisposable)
    }

    override fun clear() {
        view = null
        compositeDisposable.clear()
    }
}