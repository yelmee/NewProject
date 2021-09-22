package com.example.newproejct.data.shop

import com.example.newproejct.api.ApiService
import io.reactivex.Observable

class ShopRepositoryImp(val service: ApiService): ShopRepository {
    override fun getShop(): Observable<ShopResponse> {
        return service.getFastDeliveryList()
    }
}