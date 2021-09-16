package com.example.newproejct.data

import com.google.gson.annotations.SerializedName

data class BannerResponse(
    @field:SerializedName("apiVersion") val version: String,
    @field:SerializedName("data") val data: BannerListResponse
)