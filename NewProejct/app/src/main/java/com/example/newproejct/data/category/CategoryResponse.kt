package com.example.newproejct.data.category

import com.google.gson.annotations.SerializedName

data class CategoryResponse(
    @field:SerializedName("status") val status: String,
    @field:SerializedName("message") val message: String,
    @field:SerializedName("serverDatetime") val serverDatetime: String,
    @field:SerializedName("data") val data: CategoryListResponse
)