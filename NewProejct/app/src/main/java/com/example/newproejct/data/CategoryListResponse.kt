package com.example.newproejct.data

import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @field:SerializedName("icons") val icons: ArrayList<MainRecyclerviewItem.Categories>
)
