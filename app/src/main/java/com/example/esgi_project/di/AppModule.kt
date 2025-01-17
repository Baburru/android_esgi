package com.example.esgi_project.di

import MovieStorage
import com.example.esgi_project.pages.MovieStorageViewModel
import com.example.esgi_project.viewmodel.MovieViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import org.koin.core.module.dsl.viewModel


val appModule = module {
    single { MovieStorage(androidContext()) }

    viewModel{
        MovieStorageViewModel(get())
    }
    viewModel{
        MovieViewModel()
    }
}