package com.example.newproejct.di

import com.example.newproejct.data.banner.BannerRepository
import com.example.newproejct.data.banner.BannerRepositoryImp
import com.example.newproejct.data.category.CategoryRepository
import com.example.newproejct.data.category.CategoryRepositoryImpl
import com.example.newproejct.data.detailshop.DetailShopRepository
import com.example.newproejct.data.detailshop.DetailShopRepositoryImp
import com.example.newproejct.data.shop.ShopRepository
import com.example.newproejct.data.shop.ShopRepositoryImp
import org.koin.dsl.module

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

    factory<DetailShopRepository>{
        DetailShopRepositoryImp(get())
    }
}