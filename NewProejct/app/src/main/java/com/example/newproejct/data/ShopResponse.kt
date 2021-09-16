package com.example.newproejct.data

import com.google.gson.annotations.SerializedName

data class ShopResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val msg: String,
    @field:SerializedName("data") val data: ShopsDataResponse
)