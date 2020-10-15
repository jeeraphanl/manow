package com.jeeraphan.manow

import android.app.Application
import com.jeeraphan.manow.di.feedModule
import com.jeeraphan.manow.di.networkModule
import org.koin.core.context.startKoin

class BaseApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            modules(networkModule + feedModule)
        }
    }
}