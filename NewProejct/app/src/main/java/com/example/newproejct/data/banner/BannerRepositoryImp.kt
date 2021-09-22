package com.example.newproejct.data.banner

import com.example.newproejct.api.ApiService
import io.reactivex.Observable

class BannerRepositoryImp(val service: ApiService): BannerRepository {
    override fun getBanner(): Observable<BannerResponse> {
        return service.getBanners()
    }
}