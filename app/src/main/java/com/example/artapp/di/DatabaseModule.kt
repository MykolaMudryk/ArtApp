package com.example.artapp.di

import androidx.room.Room
import com.example.artapp.data.local.ArtDao
import com.example.artapp.data.local.ArtDatabase
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val databaseModule = module {

    single<ArtDatabase> {
        Room.databaseBuilder(
            androidApplication(),
            ArtDatabase::class.java,
            "art_db"
        ).build()
    }

    single<ArtDao> {
        get<ArtDatabase>().artDao()
    }
}
