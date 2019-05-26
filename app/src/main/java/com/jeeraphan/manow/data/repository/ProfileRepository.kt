package com.jeeraphan.manow.data.repository

interface ProfileRepository {
    fun getFirstName(): String
    fun getLastName(): String
    fun getAge(): Int
}

class ProfileRepositoryImpl : ProfileRepository {

    override fun getFirstName(): String {
        return "John"
    }

    override fun getLastName(): String {
        return "wick"
    }

    override fun getAge(): Int {
        return 25
    }
}