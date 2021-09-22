package com.example.newproejct.data.detailshop

import com.google.gson.annotations.SerializedName

data class DetailShopInfo(
    @field:SerializedName("expectedCookTime") val expectedCookTime: String,
    @field:SerializedName("contents") val contents: List<Content>,
    @field:SerializedName("Shop_Nm") val Shop_Nm: String,
    @field:SerializedName("Shop_Intro") val Shop_Intro: String,
    @field:SerializedName("Favorite_Cnt") val Favorite_Cnt: Int,
    @field:SerializedName("Ct_Cd_Nm") val Ct_Cd_Nm: String)
