package com.example.newproejct.di

import com.example.newproejct.viewmodel.AuthViewModel
import com.example.newproejct.viewmodel.HomeViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { HomeViewModel(get(),get(), get(),get()) }
    viewModel { AuthViewModel(get()) }
}