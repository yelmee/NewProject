package com.example.newproejct.data.category

import com.example.newproejct.data.MainRecyclerviewItem
import com.google.gson.annotations.SerializedName

data class CategoryListResponse(
    @field:SerializedName("icons") val icons: ArrayList<MainRecyclerviewItem.Category>
)
