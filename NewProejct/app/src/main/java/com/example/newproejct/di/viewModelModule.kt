package com.example.newproejct.di

import com.example.newproejct.viewmodel.BagViewModel
import com.example.newproejct.viewmodel.HomeViewModel
import com.example.newproejct.viewmodel.PhoneAuthViewModel
import com.example.newproejct.viewmodel.ShopViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.module.Module
import org.koin.dsl.module

val viewModelModule: Module = module {
    viewModel { HomeViewModel(get(),get(), get(),get()) }
    viewModel { PhoneAuthViewModel() }
    viewModel { ShopViewModel(get()) }
    viewModel { BagViewModel(get()) }

}