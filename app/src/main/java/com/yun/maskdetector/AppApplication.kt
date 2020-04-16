package com.yun.maskdetector

import android.app.Application
import com.yun.maskdetector.di.networkModule
import com.yun.maskdetector.di.repositoryModule
import com.yun.maskdetector.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@AppApplication)
            modules(networkModule, repositoryModule, viewModelModule)
        }
    }
}