package com.example.artapp

import android.app.Application
import com.example.artapp.di.databaseModule
import com.example.artapp.di.networkModule
import com.example.artapp.di.repositoryModule
import com.example.artapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class ArtApp : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@ArtApp)
            modules(listOf(networkModule, databaseModule, repositoryModule, viewModelModule))
        }
    }
}
