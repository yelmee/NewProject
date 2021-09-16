package com.example.newproejct.data

import com.google.gson.annotations.SerializedName

sealed class MainRecyclerviewItem {
    data class Banners(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("imageUrl") val urls: String,
        @field:SerializedName("link") val link: String
    ): MainRecyclerviewItem()

    data class Categories(
        @field:SerializedName("id") val id: Int,
        @field:SerializedName("title") val title: String,
        @field:SerializedName("imageUrl") val urls: String,
        @field:SerializedName("link") val link: String
    ): MainRecyclerviewItem()

    data class Shop(
        @field:SerializedName("name") val name: String,
        @field:SerializedName("imageUrl") val url: String,
        @field:SerializedName("deliveryTime") val time: String,
        @field:SerializedName("categories") val cat: ArrayList<Name>,
        @field:SerializedName("tag") val tag: ArrayList<Title>
    ): MainRecyclerviewItem()
}