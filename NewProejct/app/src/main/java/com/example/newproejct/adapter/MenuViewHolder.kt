package com.example.newproejct.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.newproejct.data.detailshop.MenuData
import com.example.newproejct.data.detailshop.Menus
import com.example.newproejct.databinding.FragmentMenuBinding
import com.example.newproejct.databinding.FragmentRecyclerviewBinding

sealed class MenuViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    val representationAdapter = RepresentationAdapter()
    val groupMenuAdapter = GroupMenuAdapter()

    class RepresentMenuViewHolder(
        private val binding: FragmentRecyclerviewBinding) :
        MenuViewHolder(binding) {

        fun bind(item: List<MenuData.RepresentationMenu>) {
            binding.shopMenuRcDetailShop.apply {
                adapter = representationAdapter
            }
            representationAdapter.submitList(item)
        }

    }

    class AllMenuViewHolder(
        private val bindingMenu: FragmentMenuBinding) :
        MenuViewHolder(bindingMenu) {
        fun bind(item: List<Menus>) {
            bindingMenu.menuRecyclerview.apply{
                adapter  =  groupMenuAdapter
            }
        groupMenuAdapter.submitList(item)
        }
    }
}