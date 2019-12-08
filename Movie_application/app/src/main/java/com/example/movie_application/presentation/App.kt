package com.example.movie_application.presentation

import android.app.Application
import com.example.movie_application.presentation.di.appModule
//import com.example.movieapp.presentation.di.appModule
import com.facebook.stetho.Stetho
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        Stetho.initializeWithDefaults(this)
        startKoin {
            androidContext(this@App)
            modules(appModule)
        }
    }

}