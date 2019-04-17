package com.jeeraphan.manow.di

import android.app.Application
import android.content.Context
import com.nhaarman.mockito_kotlin.mock
import org.junit.Test
import org.koin.dsl.module.module
import org.koin.test.KoinTest
import org.koin.test.checkModules

class KoinModulesTest : KoinTest {

    @Test
    fun testKoinModules() {

        val mockedAndroid = module {
            single { mock<Context>() }
            single { mock<Application>() }
        }

        val koinModulesList = listOf(networkModule, feedModule, mockedAndroid)

        checkModules(koinModulesList)
    }
}