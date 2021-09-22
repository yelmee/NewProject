package com.example.newproejct.data.banner

import com.example.newproejct.data.MainRecyclerviewItem
import com.google.gson.annotations.SerializedName

data class BannerListResponse(
    @field:SerializedName("mainBanners") val mainBanners: ArrayList<MainRecyclerviewItem.Banner>
)