package com.example.newproejct.di

import com.example.newproejct.data.*
import com.example.newproejct.utils.FireBaseAuthProvider
import com.google.firebase.auth.FirebaseAuth
import org.koin.dsl.module
import java.util.Collections.singleton

var modelPart = module {
    factory<CategoryRepository>{
        CategoryRepositoryImpl(get())
    }

    factory<BannerRepository>{
        BannerRepositoryImp(get())
    }

    factory<ShopRepository>{
        ShopRepositoryImp(get())
    }
}