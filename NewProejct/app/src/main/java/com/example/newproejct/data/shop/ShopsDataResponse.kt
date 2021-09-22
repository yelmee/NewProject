package com.example.newproejct.data.shop

import com.example.newproejct.data.MainRecyclerviewItem
import com.google.gson.annotations.SerializedName

data class ShopsDataResponse(
    @field:SerializedName("shops") val shops: ArrayList<MainRecyclerviewItem.Shop>
)
