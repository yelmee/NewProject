package com.example.newproejct.data

import com.google.gson.annotations.SerializedName

data class ShopsDataResponse(
    @field:SerializedName("shops") val shops: ArrayList<MainRecyclerviewItem.Shop>
)
