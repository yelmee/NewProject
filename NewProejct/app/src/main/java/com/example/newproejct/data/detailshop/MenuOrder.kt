package com.example.newproejct.data.detailshop

import com.google.gson.annotations.SerializedName

data class MenuOrder(
    @field:SerializedName("representationMenus") val representationMenu: ArrayList<MenuData.RepresentationMenu>,
    @field:SerializedName("groupMenus") val groupMenus: ArrayList<MenuData.GroupMenus>
)
