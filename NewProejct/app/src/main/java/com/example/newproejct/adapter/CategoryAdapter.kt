package com.example.newproejct.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.databinding.ItemRcCateBinding
import com.example.newproejct.data.MainRecyclerviewItem

class CategoryAdapter :
    ListAdapter<MainRecyclerviewItem.Category, RecyclerView.ViewHolder>(CategoryDiffCallback()) {
    var itemClickListener: ((view: View, item: MainRecyclerviewItem, position: Int) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CategoryViewHolder(
            ItemRcCateBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val category = getItem(position)
        (holder as CategoryViewHolder).bind(category)

        holder.itemClickListener = itemClickListener
    }

    inner class CategoryViewHolder(
        private val binding: ItemRcCateBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        var itemClickListener: ((view: View, item: MainRecyclerviewItem, position: Int) -> Unit)? = null

        fun bind(item: MainRecyclerviewItem.Category) {
            binding.imageUrl = item.urls
            binding.executePendingBindings()

            binding.itemCat.setOnClickListener {
                itemClickListener?.invoke(it, item, absoluteAdapterPosition)
            }
        }
    }

    private class CategoryDiffCallback : DiffUtil.ItemCallback<MainRecyclerviewItem.Category>() {
        override fun areItemsTheSame(oldItem: MainRecyclerviewItem.Category, newItem: MainRecyclerviewItem.Category): Boolean {
            return oldItem.title == newItem.title
        }

        override fun areContentsTheSame(oldItem: MainRecyclerviewItem.Category, newItem: MainRecyclerviewItem.Category): Boolean {
            return oldItem == newItem
        }
    }
}
