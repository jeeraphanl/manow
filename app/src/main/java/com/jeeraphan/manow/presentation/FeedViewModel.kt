package com.jeeraphan.manow.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jeeraphan.manow.data.entity.response.Article
import com.jeeraphan.manow.domain.GetFeedUseCase
import com.jeeraphan.manow.domain.UseCaseResult
import kotlinx.coroutines.launch

class FeedViewModel(
        private val getFeedUseCase: GetFeedUseCase
) : ViewModel() {

    var articleList = MutableLiveData<List<Article>>()
    var resultMessage = MutableLiveData<String>()
    var errorMessage = MutableLiveData<String>()

    fun getFeed() {
        viewModelScope.launch {
            when (val resutl = getFeedUseCase.execute()) {
                is UseCaseResult.Success -> {
                    articleList.value = resutl.data
                }
                is UseCaseResult.Error -> {
                    errorMessage.value = resutl.exception.localizedMessage
                }
            }
        }
    }
}