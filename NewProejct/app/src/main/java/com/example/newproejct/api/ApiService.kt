package com.example.newproejct.api

import com.example.newproejct.data.banner.BannerResponse
import com.example.newproejct.data.category.CategoryResponse
import com.example.newproejct.data.detailshop.DetailShopResponse
import com.example.newproejct.data.shop.ShopResponse
import io.reactivex.Observable
import retrofit2.http.POST

interface ApiService {
    @POST("/elements")
     fun getCategories(): Observable<CategoryResponse>

    @POST("/inventories")
    fun getBanners(): Observable<BannerResponse>

    @POST("/curations")
    fun getFastDeliveryList(): Observable<ShopResponse>

    @POST("/shopdetail")
     fun getShopDetail(): Observable<DetailShopResponse>


    companion object{
         const val BASE_URL =  "http://172.20.10.8:3000"
    }
}