package com.example.newproejct.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.data.bag.Bag
import com.example.newproejct.data.detailshop.MenuData
import com.example.newproejct.databinding.ItemRcBagBinding
import java.util.zip.Inflater

class BagRecyclerViewAdapter: ListAdapter<Bag,BagRecyclerViewAdapter.BagViewHolder>(BagDiffCallback()) {
    inner class BagViewHolder(val binding: ItemRcBagBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Bag) {
            binding.bag = item
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BagViewHolder {
        return BagViewHolder(ItemRcBagBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: BagViewHolder, position: Int) {
       holder.bind(getItem(position))
    }

    private class BagDiffCallback : DiffUtil.ItemCallback<Bag>() {
        override fun areItemsTheSame(oldItem: Bag, newItem: Bag): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Bag, newItem: Bag): Boolean {
            return oldItem == newItem
        }
    }
}