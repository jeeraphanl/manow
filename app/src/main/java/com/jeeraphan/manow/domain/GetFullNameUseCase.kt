package com.jeeraphan.manow.domain

import com.jeeraphan.manow.data.repository.ProfileRepository
import io.reactivex.Observable

interface GetFullNameUseCase {
    fun execute(): Observable<String>
}

class GetFullNameUseCaseImpl(
        private val repository: ProfileRepository
) : GetFullNameUseCase {

    override fun execute(): Observable<String> {
        val fullName = "${repository.getFirstName()} ${repository.getLastName()}"
        return Observable.just(fullName)
    }
}
