package com.example.newproejct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.R
import com.example.newproejct.databinding.*
import com.example.newproejct.data.MainRecyclerviewItem

class MainRecyclerViewAdapter: RecyclerView.Adapter<MainRecyclerViewHolder>() {
    var shopItems = listOf<MainRecyclerviewItem.Shop>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var bannerItems = listOf<MainRecyclerviewItem.Banners>()
        set(value) {
            field =  value
            notifyDataSetChanged()
        }

    var categoryItems = listOf<MainRecyclerviewItem.Categories>()
    set(value) {
        field = value
        notifyDataSetChanged()
    }

    var itemClickListener: ((view: View, item: MainRecyclerviewItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainRecyclerViewHolder {
        return when (viewType) {
            R.layout.header_main -> MainRecyclerViewHolder.BannerAndCategoryViewHolder(
                HeaderMainBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            R.layout.item_rc_shop -> MainRecyclerViewHolder.ShopViewHolder(
                ItemRcShopBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException()
        }

    }

    override fun onBindViewHolder(holder: MainRecyclerViewHolder, position: Int) {

        holder.itemClickListener = itemClickListener
        when (holder) {
            is MainRecyclerViewHolder.BannerAndCategoryViewHolder -> holder.bind(bannerItems, categoryItems)
            is MainRecyclerViewHolder.ShopViewHolder -> holder.bind(shopItems[position])
        }
    }

    override fun getItemCount(): Int = shopItems.size

    override fun getItemViewType(position: Int): Int {
        var returnValue: Int = 0
         if(position == 0) {
             returnValue =  R.layout.header_main
         }else{
             returnValue = R.layout.item_rc_shop
         }

        return returnValue
    }
}