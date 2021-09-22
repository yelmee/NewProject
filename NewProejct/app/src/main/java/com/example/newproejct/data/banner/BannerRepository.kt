package com.example.newproejct.data.banner

import io.reactivex.Observable


interface BannerRepository {
    fun getBanner(): Observable<BannerResponse>
}