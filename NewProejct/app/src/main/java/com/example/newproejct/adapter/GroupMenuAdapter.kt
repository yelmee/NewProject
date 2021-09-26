package com.example.newproejct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.data.detailshop.MenuData
import com.example.newproejct.data.detailshop.Menus
import com.example.newproejct.databinding.ItemMenuBinding


class GroupMenuAdapter: ListAdapter<Menus, RecyclerView.ViewHolder>(GroupMenuDiffCallback()) {

    var itemClickListener: ((view: View, item: MenuData, position: Int) -> Unit)? = null

    inner class AllMenuViewHolder(val binding: ItemMenuBinding) :
        RecyclerView.ViewHolder(binding.root) {
        var itemClickListener: ((view: View, item: MenuData, position: Int) -> Unit)? = null

        fun bind(item: Menus) {
            binding.menu = item
            binding.executePendingBindings()

            itemClickListener = {it, item, position ->
                when (item) {

                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return AllMenuViewHolder(
            ItemMenuBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false)
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AllMenuViewHolder).itemClickListener = itemClickListener
        (holder as AllMenuViewHolder).bind(getItem(position))
    }

    private class GroupMenuDiffCallback : DiffUtil.ItemCallback<Menus>() {
        override fun areItemsTheSame(oldItem: Menus, newItem: Menus): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: Menus, newItem: Menus): Boolean {
            return oldItem == newItem
        }
    }
}