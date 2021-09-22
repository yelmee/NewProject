package com.example.newproejct.data.detailshop

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable

data class DetailShopResponse(
    @field:SerializedName("data") val data: DetailShopData
)
