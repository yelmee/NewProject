package com.example.newproejct.adapter

import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.example.newproejct.MyApplication
import com.example.newproejct.data.bag.Bag
import com.example.newproejct.data.bag.BagDataBase
import com.example.newproejct.data.detailshop.MenuData
import com.example.newproejct.data.detailshop.Menus
import com.example.newproejct.databinding.FragmentGroupMenuBinding
import com.example.newproejct.databinding.FragmentRepresentMenuBinding

sealed class MenuViewHolder(
    binding: ViewBinding
) : RecyclerView.ViewHolder(binding.root) {
    val representationAdapter = RepresentationAdapter()
    val groupMenuAdapter = GroupMenuAdapter()

    var toastListener: ((item: MenuData.RepresentationMenu) -> Unit)? = null

    class RepresentMenuViewHolder(
        private val binding: FragmentRepresentMenuBinding) :
        MenuViewHolder(binding) {

        fun bind(item: List<MenuData.RepresentationMenu>) {

            //장바구니에 담기
            representationAdapter.itemClickListener = { view, item, position ->

                toastListener?.invoke(item)
            }

            //대표메뉴 어댑터 연결
            binding.shopMenuRcDetailShop.apply {
                adapter = representationAdapter
            }
            representationAdapter.submitList(item)
        }
    }

    class AllMenuViewHolder(
        private val bindingMenu: FragmentGroupMenuBinding) :
        MenuViewHolder(bindingMenu) {
        fun bind(item: List<Menus>, name: String) {
            bindingMenu.groupNameMenuTv.text = name
            bindingMenu.menuRecyclerview.apply{
                adapter  =  groupMenuAdapter
            }
            groupMenuAdapter.submitList(item)
        }
    }
}