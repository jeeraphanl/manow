package com.jeeraphan.manow

import android.app.Application
import com.jeeraphan.manow.di.feedModule
import org.koin.android.ext.android.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(feedModule))
    }
}