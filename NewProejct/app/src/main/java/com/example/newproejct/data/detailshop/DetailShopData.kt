package com.example.newproejct.data.detailshop

import com.google.gson.annotations.SerializedName
import io.reactivex.Observable

data class DetailShopData(
    @field:SerializedName("shop_info")val shopInfo: DetailShopInfo,
    @field:SerializedName("shop_menu")val shop_menu: ShopMenu
)
