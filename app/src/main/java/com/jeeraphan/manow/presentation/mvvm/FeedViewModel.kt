package com.jeeraphan.manow.presentation.mvvm

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.tdcm.trueidapp.extensions.addTo
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

class FeedViewModel(
        private val getFeedUseCase: GetFeedUseCase/*,
        private val getFullNameUseCase: getFullNameUseCase*/
) : ViewModel() {

    var articleList = MutableLiveData<List<Article>>()
    var resultMessage = MutableLiveData<String>()
    var errorMessage = MutableLiveData<String>()
    val compositeDisposable = CompositeDisposable()

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
        //TODO 2 get full name assign to result message and error
    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.clear()
    }
}