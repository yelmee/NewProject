package com.example.newproejct.data.shop

import io.reactivex.Observable

interface ShopRepository {
    fun getShop(): Observable<ShopResponse>
}