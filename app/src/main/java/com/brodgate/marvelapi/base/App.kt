package com.brodgate.marvelapi.base

import android.app.Application
import com.brodgate.marvelapi.di.appModule
import com.brodgate.marvelapi.di.repositoriesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {


    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(appModule, repositoriesModule)
        }
    }
}