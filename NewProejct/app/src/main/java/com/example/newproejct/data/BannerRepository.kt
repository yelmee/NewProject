package com.example.newproejct.data

import io.reactivex.Observable


interface BannerRepository {
    fun getBanner(): Observable<BannerResponse>
}