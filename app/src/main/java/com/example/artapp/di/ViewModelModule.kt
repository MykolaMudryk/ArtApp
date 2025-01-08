package com.example.artapp.di

import com.example.artapp.ui.screens.currentScreen.ArtsDetailsViewModel
import com.example.artapp.ui.screens.artsByCategoryScreen.ArtsByCategoryViewModel
import com.example.artapp.ui.screens.categoriesScreen.CategoriesScreenViewModel
import com.example.artapp.ui.screens.mainScreen.MainViewModel
import com.example.artapp.ui.screens.searchScreen.SearchViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel { MainViewModel(get()) }
    viewModel { (categoryId: Int) -> ArtsByCategoryViewModel(get(), categoryId) }
    viewModel { (objectId: Int) -> ArtsDetailsViewModel(get(), objectId) }
    viewModel { SearchViewModel(get()) }
    viewModel { CategoriesScreenViewModel(get()) }
}
