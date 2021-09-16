package com.example.newproejct.data

import io.reactivex.Observable

interface ShopRepository {
    fun getShop(): Observable<ShopResponse>
}