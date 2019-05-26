package com.jeeraphan.manow.presentation.mvvm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.ViewModel
import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.GetFullNameUseCase
import com.tdcm.trueidapp.extensions.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedViewModel(
        private val getFeedUseCase: GetFeedUseCase,
        private val getFullNameUseCase: GetFullNameUseCase
) : ViewModel() {

    private var articleList = MutableLiveData<List<Article>>()
    private var resultMessage = MutableLiveData<String>()
    private var errorMessage = MutableLiveData<String>()
    private val compositeDisposable = CompositeDisposable()

    fun articleList(): LiveData<List<Article>> = articleList

    fun resultMessage(): LiveData<String> = resultMessage

    fun errorMessage(): LiveData<String> = errorMessage

    fun getFeed() {
        getFeedUseCase.execute()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ articles ->
                    articleList.value = articles
                }, { error ->
                    errorMessage.value = error.localizedMessage
                })
                .addTo(compositeDisposable)

    }

    fun getFullName() {
        getFullNameUseCase.execute()
                .subscribe({ fullName ->
                    resultMessage.value = fullName
                }, { error ->
                    errorMessage.value = error.localizedMessage
                })
                .addTo(compositeDisposable)
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}