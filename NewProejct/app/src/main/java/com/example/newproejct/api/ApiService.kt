package com.example.newproejct.api

import com.example.newproejct.data.BannerResponse
import com.example.newproejct.data.CategoryResponse
import com.example.newproejct.data.ShopRepository
import com.example.newproejct.data.ShopResponse
import io.reactivex.Observable
import io.reactivex.Single
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface ApiService {
    @POST("/elements")
     fun getCategories(): Observable<CategoryResponse>

    @POST("/inventories")
    fun getBanners(): Observable<BannerResponse>

    @POST("/curations")
    fun getFastDeliveryList(): Observable<ShopResponse>
//
//    @POST("/shopdetail")
//    suspend fun getShopDetail():


    companion object{
         const val BASE_URL =  "http://192.168.0.38:3000"
    }
}