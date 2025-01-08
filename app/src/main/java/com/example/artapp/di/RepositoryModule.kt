package com.example.artapp.di

import com.example.artapp.repository.ArtRepository
import org.koin.dsl.module

val repositoryModule = module {
    single { ArtRepository(get(), get()) }
}
