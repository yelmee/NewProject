package com.example.newproejct.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.databinding.ItemVpBannerBinding
import com.example.newproejct.data.MainRecyclerviewItem

class BannerViewPagerAdapter(private val item: List<MainRecyclerviewItem.Banners>) :
    RecyclerView.Adapter<BannerViewPagerAdapter.PagerViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PagerViewHolder {
        return PagerViewHolder(
            ItemVpBannerBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: PagerViewHolder, position: Int) {
        holder.bind(item[position].urls)
    }

    override fun getItemCount(): Int = item.size


    inner class PagerViewHolder(private val binding : ItemVpBannerBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(url: String) {
            binding.imageFromUrl = url
        }
    }
}