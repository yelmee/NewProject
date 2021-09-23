package com.example.newproejct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.data.MainRecyclerviewItem
import com.example.newproejct.data.detailshop.MenuData
import com.example.newproejct.data.detailshop.MenuOrder
import com.example.newproejct.databinding.ItemRepresentDetailShopBinding
import java.util.zip.Inflater

class RepresentationAdapter: ListAdapter<MenuData.RepresentationMenu, RecyclerView.ViewHolder>(RepresentDiffCallback()) {
    var itemClickListener: ((view: View, item: MenuData.RepresentationMenu, position: Int) -> Unit)? = null

    inner class representationViewHolder(val binding: ItemRepresentDetailShopBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemClickListener: ((view: View, item: MenuData.RepresentationMenu, position: Int) -> Unit)? = null

        fun bind(item: MenuData.RepresentationMenu) {
            binding.representMenu = item

            binding.menuImageRepresentItemIv.setOnClickListener {
                itemClickListener?.invoke(it, item, absoluteAdapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return representationViewHolder(
            ItemRepresentDetailShopBinding.inflate(LayoutInflater.from(parent.context),
            parent,
            false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as representationViewHolder).itemClickListener = itemClickListener
        (holder as representationViewHolder).bind(getItem(position))
    }

    private class RepresentDiffCallback : DiffUtil.ItemCallback<MenuData.RepresentationMenu>() {
        override fun areItemsTheSame(oldItem: MenuData.RepresentationMenu, newItem: MenuData.RepresentationMenu): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: MenuData.RepresentationMenu, newItem: MenuData.RepresentationMenu): Boolean {
            return oldItem == newItem
        }
    }
}