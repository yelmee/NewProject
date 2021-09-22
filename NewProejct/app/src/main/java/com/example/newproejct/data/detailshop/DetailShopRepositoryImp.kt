package com.example.newproejct.data.detailshop

import com.example.newproejct.api.ApiService
import io.reactivex.Observable

class DetailShopRepositoryImp(val service: ApiService): DetailShopRepository {
    override fun getDetailShop(): Observable<DetailShopResponse> {
        return service.getShopDetail()
    }
}