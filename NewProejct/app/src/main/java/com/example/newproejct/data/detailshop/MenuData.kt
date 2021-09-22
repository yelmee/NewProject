package com.example.newproejct.data.detailshop

import com.google.gson.annotations.SerializedName

sealed class MenuData {
    data class RepresentationMenu(
        @field:SerializedName("name") val name: String,
        @field:SerializedName("images") val images: List<Images>,
        @field:SerializedName("description") val description: String,
        @field:SerializedName("menuPrices") val menuPrices: List<MenuPrices>
    ): MenuData()

    data class GroupMenus(
        @field:SerializedName("name") val name: String,
        @field:SerializedName("menus") val menus: List<Menus>
    ): MenuData()

}