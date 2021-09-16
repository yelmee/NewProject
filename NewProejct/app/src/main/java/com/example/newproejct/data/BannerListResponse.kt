package com.example.newproejct.data

import com.google.gson.annotations.SerializedName

data class BannerListResponse(
    @field:SerializedName("mainBanners") val mainBanners: ArrayList<MainRecyclerviewItem.Banners>
)