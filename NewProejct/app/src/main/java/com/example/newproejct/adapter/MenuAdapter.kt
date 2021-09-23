package com.example.newproejct.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.newproejct.R
import com.example.newproejct.data.detailshop.MenuData
import com.example.newproejct.data.detailshop.Menus
import com.example.newproejct.databinding.FragmentMenuBinding
import com.example.newproejct.databinding.FragmentRecyclerviewBinding
import java.lang.IllegalArgumentException

class MenuAdapter : RecyclerView.Adapter<MenuViewHolder>() {

    var toastListener: ((item: MenuData.RepresentationMenu) -> Unit)? = null
    var groupMenuItem = listOf<MenuData.GroupMenus>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    var representItem = listOf<MenuData.RepresentationMenu>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        return when (viewType) {
            R.layout.fragment_menu -> MenuViewHolder.AllMenuViewHolder(
                FragmentMenuBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )
            R.layout.fragment_recyclerview -> MenuViewHolder.RepresentMenuViewHolder(
                FragmentRecyclerviewBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )
            )

            else -> throw IllegalArgumentException()
        }
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        holder.toastListener = toastListener
        when (holder) {
            is MenuViewHolder.RepresentMenuViewHolder -> holder.bind(representItem as List<MenuData.RepresentationMenu>)
            is MenuViewHolder.AllMenuViewHolder -> holder.bind(groupMenuItem[position].menus as List<Menus>)
        }

    }

    override fun getItemViewType(position: Int): Int {
        Log.d("jyl", "menu position" + position.toString())
        var viewType = 0
        if (position == 0) {
            viewType = R.layout.fragment_recyclerview
        } else {
            viewType = R.layout.fragment_menu
        }
        return viewType
    }

    override fun getItemCount(): Int = groupMenuItem.size
}
