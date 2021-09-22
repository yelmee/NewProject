package com.example.newproejct.data.detailshop

import com.google.gson.annotations.SerializedName

data class Menus(
    @field:SerializedName("name") val name: String,
    @field:SerializedName("description") val description: String,
    @field:SerializedName("menuPrices") val menuPrices: List<MenuPrices>
    )
