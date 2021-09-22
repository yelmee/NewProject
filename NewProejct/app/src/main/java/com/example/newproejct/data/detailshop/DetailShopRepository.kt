package com.example.newproejct.data.detailshop

import io.reactivex.Observable

interface DetailShopRepository {
    fun getDetailShop(): Observable<DetailShopResponse>
}